package com.hf.game.module.items.ext;

import com.hf.game.module.items.ItemBrand;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by 123 on 2019-6-19.
 */
@Data
@ToString
public class ItemBrandNode extends ItemBrand {
    private List<ItemBrandNode> children;
}
