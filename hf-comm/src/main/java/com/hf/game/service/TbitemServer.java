package com.hf.game.service;

import com.github.pagehelper.Page;
import com.hf.game.modul.dto.request.TbItemPageQuery;
import com.hf.game.modul.pojo.TbItem;

import java.util.List;

/**
 * Created by 123 on 2019-5-29.
 */
public interface TbitemServer {
    List<TbItem> findById(Long id);
    Page<TbItem> findAll(int page , int pageSize, TbItemPageQuery pageQuery);
}
