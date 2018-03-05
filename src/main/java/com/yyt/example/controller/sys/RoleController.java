package com.yyt.example.controller.sys;

import com.yyt.example.common.Res;
import com.yyt.example.entity.sys.RoleEntity;
import com.yyt.example.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/index")
    public Object index() {
        return "sys/role";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Res list(@RequestParam Map<String,Object> param) {
        Page<RoleEntity> page = roleService.findPage(param);
        return Res.ok(page);
    }

    @RequestMapping("/save")
    @ResponseBody
    public Res save(RoleEntity role) {
        roleService.save(role);
        return Res.ok();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Res delete(Integer id) {
        roleService.delete(id);
        return Res.ok();
    }
}
