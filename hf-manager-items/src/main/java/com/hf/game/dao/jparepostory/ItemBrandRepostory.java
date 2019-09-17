package com.hf.game.dao.jparepostory;

import com.hf.game.module.items.ItemBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 123 on 2019-6-19.
 */
@Repository
public interface ItemBrandRepostory extends JpaRepository<ItemBrand ,Integer> {
}
