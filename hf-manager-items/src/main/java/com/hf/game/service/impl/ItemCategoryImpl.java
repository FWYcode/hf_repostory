package com.hf.game.service.impl;

import com.alibaba.fastjson.JSON;
import com.hf.game.dao.mappers.ItemCategoryMapper;
import com.hf.game.module.items.CateGoryResult;
import com.hf.game.module.items.ext.ItemCategoryNode;
import com.hf.game.service.ItemCategoryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2019-6-20.
 */
@Service
public class ItemCategoryImpl implements ItemCategoryServer {
    @Autowired
    ItemCategoryMapper categoryMapper;
    @Autowired
    StringRedisTemplate template;

    @Override
    public List<CateGoryResult> findAll() {
        List<ItemCategoryNode> all = this.findAllRources();
        List<CateGoryResult> results = this.copyPorpties(all);
        return results;
    }
    @Override
    public List<ItemCategoryNode> findAllByServer(){

        return this.findAllRources();
    }

    private List<ItemCategoryNode> findAllRources() {
        List<ItemCategoryNode> all;
        ValueOperations<String, String> value = template.opsForValue();
        String item_category = value.get("item_category");
        all = JSON.parseArray(item_category, ItemCategoryNode.class);
        if (StringUtils.isEmpty(all)){
            all= categoryMapper.findAll();
            String s = JSON.toJSONString(all);
            value.set("item_category",s);
        }
        return all;
    }

    private List<CateGoryResult> copyPorpties(List<ItemCategoryNode> all) {
        List<CateGoryResult> results = new ArrayList<>();
        for (ItemCategoryNode itemCategoryNode : all) {
            CateGoryResult cateGoryResult = new CateGoryResult();
            cateGoryResult.setId(itemCategoryNode.getId());
            cateGoryResult.setLabel(itemCategoryNode.getCategoryName());
            if (itemCategoryNode.getChildren() == null)
                break;
            List<ItemCategoryNode> children = itemCategoryNode.getChildren();
            List<CateGoryResult> list1=new ArrayList<>();
            for (ItemCategoryNode child : children) {
                CateGoryResult cateGoryResult1 = new CateGoryResult();
                cateGoryResult1.setId(child.getId());
                cateGoryResult1.setLabel(child.getCategoryName());
                if (child.getChildren() == null)
                    break;
                List<ItemCategoryNode> children2 = child.getChildren();
                List<CateGoryResult> list2=new ArrayList<>();
                for (ItemCategoryNode categoryNode : children2) {
                    CateGoryResult cateGoryResult2 = new CateGoryResult();
                    cateGoryResult2.setId(categoryNode.getId());
                    cateGoryResult2.setLabel(categoryNode.getCategoryName());
                    list2.add(cateGoryResult2);
                }
                cateGoryResult1.setChildren(list2);
                list1.add(cateGoryResult1);
            }
            cateGoryResult.setChildren(list1);
            results.add(cateGoryResult);
        }
        return results;
    }
}
