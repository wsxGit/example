package com.yyt.example.service.sys;

import com.yyt.example.entity.sys.UserEntity;
import com.yyt.example.repository.sys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<UserEntity> findPage(Map<String, Object> param) {
        return userRepository.findPage(param);
    }

    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
