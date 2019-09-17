package com.hf.game.service;

import com.hf.game.module.items.ItemBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 123 on 2019-6-19.
 */

public interface ItemBrandServer {
    ItemBrand findById(Integer id);

    List<ItemBrand> findAll();

    ItemBrand insert(ItemBrand itemBrand);
}
