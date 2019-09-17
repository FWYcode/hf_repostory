package com.hf.game.dao.jparepostory;

import com.hf.game.module.items.TestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 123 on 2019-9-3.
 */
@Repository
public interface TestItemRepostoey extends JpaRepository<TestItem,Long> {
    List<TestItem> findByItemName(String name);
}
