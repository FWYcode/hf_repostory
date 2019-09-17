package com.hf.game.module.items.ext;

import com.hf.game.module.items.ItemCategory;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by 123 on 2019-6-20.
 */
@Data
@ToString
public class ItemCategoryNode extends ItemCategory {
    private List<ItemCategoryNode> children;
}
