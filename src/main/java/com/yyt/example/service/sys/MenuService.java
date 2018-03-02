package com.yyt.example.service.sys;

import com.yyt.example.entity.sys.MenuEntity;
import com.yyt.example.repository.sys.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Page<MenuEntity> findPage(Map<String, Object> param) {
        param.put("parentId", 0);
        Page<MenuEntity> page = menuRepository.findPage(param);
        getByParentId(page.getContent());
        return page;
    }

    public void save(MenuEntity menu) {
        menuRepository.save(menu);
    }

    public void delete(Integer primaryKey) {
        menuRepository.delete(primaryKey);
    }

    private void getByParentId(List<MenuEntity> list) {
        for (MenuEntity menuEntity : list) {
            List<MenuEntity> children = menuRepository.getByParentId(menuEntity.getMenuId());
            menuEntity.setChildren(children);
            getByParentId(children);
        }
    }
}
