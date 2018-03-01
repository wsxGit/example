package com.yyt.example.config;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wangsixian on 2017/11/7.
 */
@NoRepositoryBean
@Transactional(readOnly = true)
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    List<Object[]> listBySQL(String sql);

    @Transactional
    void updateBySql(String sql, Object... args);

    @Transactional
    void updateByHql(String hql, Object... args);

    List<T> findAll(Map<String, Object> param);

    Page<T> findPage(Map<String, Object> filters);
}
