package com.yyt.example.controller.sys;

import com.yyt.example.entity.sys.UserEntity;
import com.yyt.example.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public Object index() {
        return "sys/user/user";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam Map<String,Object> param) {
        Page<UserEntity> page = userService.findPage(param);
        return page;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(UserEntity userEntity) {
        userService.save(userEntity);
        return "";
    }
}
