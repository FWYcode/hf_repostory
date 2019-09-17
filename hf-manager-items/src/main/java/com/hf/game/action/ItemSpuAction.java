package com.hf.game.action;

import com.hf.game.module.items.ItemSPU;
import com.hf.game.service.ItemSpuSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 123 on 2019-9-1.
 */
@RestController
@RequestMapping("item/spu/")
public class ItemSpuAction {
    @Autowired
    ItemSpuSever spuSever;

    @GetMapping("findall")
    public List<ItemSPU> findAll() {
        return spuSever.queryAll();
    }
}
