package com.hf.game.service.impl;

import com.hf.game.dao.jparepostory.ItemBrandRepostory;
import com.hf.game.module.items.ItemBrand;
import com.hf.game.service.ItemBrandServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 123 on 2019-6-19.
 */
@Service
public class ItemBrandServerImpl implements ItemBrandServer {
    @Autowired
    ItemBrandRepostory brandRepostory;

    @Override
    public ItemBrand findById(Integer id) {
        return brandRepostory.findById(id).get();
    }

    @Override
    public List<ItemBrand> findAll() {

        return brandRepostory.findAll();
    }

    @Override
    public ItemBrand insert(ItemBrand itemBrand) {
        itemBrand.setCreateTime(new Date());
        return brandRepostory.save(itemBrand);
    }
}
