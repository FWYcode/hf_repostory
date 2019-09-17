package com.hf.game.dao;

import com.hf.game.modul.pojo.TbItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 123 on 2019-5-23.
 */
public interface ItemRepository extends JpaRepository<TbItem,String>{
     TbItem findById(Long id);
}
