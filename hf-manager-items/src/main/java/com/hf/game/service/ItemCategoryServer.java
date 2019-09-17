package com.hf.game.service;
import com.hf.game.module.items.CateGoryResult;
import com.hf.game.module.items.ext.ItemCategoryNode;

import java.util.List;

/**
 * Created by 123 on 2019-6-20.
 */
public interface ItemCategoryServer {
    List<CateGoryResult> findAll();

    List<ItemCategoryNode> findAllByServer();
}
