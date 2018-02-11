package com.yyt.example.repository.sys;

import com.yyt.example.entity.sys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{
}
