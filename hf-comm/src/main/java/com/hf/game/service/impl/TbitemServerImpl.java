package com.hf.game.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.hf.game.dao.ItemRepository;
import com.hf.game.dao.MyMapper;
import com.hf.game.modul.dto.request.TbItemPageQuery;
import com.hf.game.modul.pojo.TbItem;
import com.hf.game.service.TbitemServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 123 on 2019-5-29.
 */
@Service
public class TbitemServerImpl implements TbitemServer {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    ItemRepository repository;
    @Autowired
    MyMapper myMapper;
    @Override
    public List<TbItem> findById(Long id) {
        List<TbItem> items = new ArrayList<>();
        TbItem tbItem = (TbItem) redisTemplate.opsForValue().get(id.toString());
//        items.add(tbItem);
        if (StringUtils.isEmpty(tbItem.getId().toString())) {
            tbItem = repository.findById(id);
            redisTemplate.opsForValue().set(id.toString(), tbItem);
        }
        items.add(tbItem);
        return items;
    }

    @Override
    public Page<TbItem> findAll(int pageNum, int pageSize, TbItemPageQuery pageQuery) {
        Example example = new Example(TbItem.class);
        Example.Criteria criteria = example.createCriteria();
        if (null == pageQuery) {
            pageQuery=new TbItemPageQuery();
        }
        if (StringUtils.isNoneEmpty(pageQuery.getId())) {
            criteria.andEqualTo("id",pageQuery.getId());
        }
        if (StringUtils.isNoneEmpty(pageQuery.getSellPoint())) {
            criteria.andLike("sellPoint", pageQuery.getSellPoint()+"%");
        }
        if (StringUtils.isNoneEmpty(pageQuery.getTitle())) {
            criteria.andLike("title", pageQuery.getTitle()+"%");
        }
        if (StringUtils.isNoneEmpty(pageQuery.getPrice())) {
            criteria.andEqualTo("price", pageQuery.getPrice());
        }
        Page<TbItem> tbItems = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> myMapper.selectByExample(example));
        return tbItems;
    }
}
