package com.yyt.example.service.sys;

import com.yyt.example.entity.sys.MenuEntity;
import com.yyt.example.repository.sys.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Page<MenuEntity> findPage(Map<String, Object> param) {
        return menuRepository.findPage(param);
    }

    public void save(MenuEntity menu) {
        menuRepository.save(menu);
    }

    public void delete(Integer primaryKey) {
        menuRepository.delete(primaryKey);
    }
}
