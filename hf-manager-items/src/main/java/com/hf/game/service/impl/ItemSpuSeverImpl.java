package com.hf.game.service.impl;

import com.hf.game.dao.jparepostory.ItemSpuRepostory;
import com.hf.game.module.items.ItemSPU;
import com.hf.game.service.ItemSpuSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 123 on 2019-9-1.
 */
@Service
public class ItemSpuSeverImpl implements ItemSpuSever {
    @Autowired
    ItemSpuRepostory repostory;
    @Override
    public List<ItemSPU> queryAll() {
        return repostory.findAll();
    }
}
