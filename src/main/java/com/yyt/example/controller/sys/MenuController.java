package com.yyt.example.controller.sys;

import com.yyt.example.common.Res;
import com.yyt.example.entity.sys.MenuEntity;
import com.yyt.example.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/index")
    public Object index() {
        return "sys/menu";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Res list(@RequestParam Map<String,Object> param) {
        Page<MenuEntity> page = menuService.findPage(param);
        return Res.ok(page);
    }

    @RequestMapping("/save")
    @ResponseBody
    public Res save(MenuEntity menu) {
        menuService.save(menu);
        return Res.ok();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Res delete(Integer id) {
        menuService.delete(id);
        return Res.ok();
    }
}
