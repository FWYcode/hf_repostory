package com.hf.game.action;

import com.hf.game.items.ItemBrandActionApi;
import com.hf.game.module.items.ItemBrand;
import com.hf.game.service.ItemBrandServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 123 on 2019-6-19.
 */
@RestController
@RequestMapping("item/brand/")
public class ItemBrandAction implements ItemBrandActionApi {
    @Autowired
    ItemBrandServer brandServer;

    @Override
    @GetMapping("find/{id}")
    public ItemBrand findById(HttpServletRequest res, @PathVariable("id") Integer id) {
        String remoteAddr = res.getRemoteHost();
        System.out.println(remoteAddr);
        return brandServer.findById(id);
    }

    @Override
    @GetMapping("find/all")
    public List<ItemBrand> findAll() {
        return brandServer.findAll();
    }

    @Override
    @PostMapping("save")
    public ItemBrand insert(ItemBrand itemBrand) {
        return brandServer.insert(itemBrand);
    }
}
