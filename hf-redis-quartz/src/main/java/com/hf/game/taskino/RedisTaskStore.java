package com.hf.game.taskino;

import java.util.Map;
import java.util.StringJoiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.params.SetParams;

public class RedisTaskStore implements ITaskStore {
    private final static Logger LOG = LoggerFactory.getLogger(RedisTaskStore.class);

    private final static int DEFAULT_LOCK_AGE = 5;

    private RedisStore redis;
    // 组名（任务空间）
    private String group;
    // 多进程争抢任务时使用 Redis 锁（到点只有一个进程可以运行，每个任务一把锁） lockAge 为 锁保持的时间，持有时间内其它进程不得运行该任务
    private int lockAge = DEFAULT_LOCK_AGE;

    public RedisTaskStore(RedisStore redis, String group) {
        this(redis, group, DEFAULT_LOCK_AGE);
    }

    public RedisTaskStore(RedisStore redis, String group, int lockAge) {
        this.redis = redis;
        this.group = group;
        this.lockAge = lockAge;
    }

    @Override
    public long getRemoteVersion() {
        Holder<Long> holder = new Holder<>();
        this.redis.execute(jedis -> {
            String versionKey = keyFor("version");
            Long remoteVersion = jedis.incrBy(versionKey, 0);
            holder.value(remoteVersion);
        });
        return holder.value();
    }

    private String keyFor(Object... args) {
        StringJoiner sj = new StringJoiner("_");
        sj.add(group);
        for (Object arg : args) {
            sj.add(String.valueOf(arg));
        }
        return sj.toString();
    }

    @Override
    public Map<String, String> getAllTriggers() {
        Holder<Map<String, String>> holder = new Holder<>();
        this.redis.execute(jedis -> {
            String tasksKey = keyFor("triggers");
            holder.value(jedis.hgetAll(tasksKey));
        });
        return holder.value();
    }

    @Override
    public void saveAllTriggers(long version, Map<String, String> triggers) {
        this.redis.execute(jedis -> {
            String key = keyFor("triggers");
            jedis.hmset(key, triggers);
            jedis.hkeys(key).forEach(name -> {
                if (!triggers.containsKey(name)) {
                    LOG.warn("deleting unused task {} in redis", name);
                    jedis.hdel(key, name);
                }
            });
            jedis.set(keyFor("version"), "" + version);
        });
    }

    @Override
    public boolean grabTask(String name) {
        Holder<Boolean> holder = new Holder<>();
        redis.execute(jedis -> {
            String lockKey = keyFor("task_lock", name);
            String ok = jedis.set(lockKey, "true", SetParams.setParams().nx().ex(lockAge));
            holder.value(ok != null);
        });
        return holder.value();
    }

}
