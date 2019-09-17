package com.hf.game.dao.jparepostory;

import com.hf.game.module.items.ItemSPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 123 on 2019-9-1.
 */
@Repository
public interface ItemSpuRepostory extends JpaRepository<ItemSPU,Long> {
}
