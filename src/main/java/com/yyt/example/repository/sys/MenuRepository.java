package com.yyt.example.repository.sys;

import com.yyt.example.config.BaseRepository;
import com.yyt.example.entity.sys.MenuEntity;

import java.util.List;

public interface MenuRepository extends BaseRepository<MenuEntity, Integer> {

    List<MenuEntity> getByParentId(Integer parenId);
}
