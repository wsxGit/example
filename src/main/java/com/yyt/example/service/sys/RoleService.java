package com.yyt.example.service.sys;

import com.yyt.example.entity.sys.RoleEntity;
import com.yyt.example.repository.sys.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Page<RoleEntity> findPage(Map<String, Object> param) {
        return roleRepository.findPage(param);
    }

    public void save(RoleEntity role) {
        roleRepository.save(role);
    }

    public void delete(Integer primaryKey) {
        roleRepository.delete(primaryKey);
    }
}
