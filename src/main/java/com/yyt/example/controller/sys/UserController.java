package com.yyt.example.controller.sys;

import com.yyt.example.entity.sys.UserEntity;
import com.yyt.example.repository.sys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    public Object index(Model model) {
        List<UserEntity> all = userRepository.findAll();
        return "sys/index";
    }
}
