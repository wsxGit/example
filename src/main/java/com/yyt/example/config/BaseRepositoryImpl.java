package com.yyt.example.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;

/**
 * Created by wangsixian on 2017/11/7.
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> listBySQL(String sql) {
        return entityManager.createNativeQuery(sql).getResultList();
    }

    @Override
    public void updateBySql(String sql, Object... args) {
        Query query = entityManager.createNativeQuery(sql);
        int i = 0;
        for (Object arg : args) {
            query.setParameter(++i, arg);
        }
        query.executeUpdate();
    }

    @Override
    public void updateByHql(String hql, Object... args) {
        Query query = entityManager.createQuery(hql);
        int i = 0;
        for (Object arg : args) {
            System.out.println(arg);
            query.setParameter(++i, arg);
        }
        query.executeUpdate();
    }

    @Override
    public List<T> findAll(Map<String, Object> param) {
        TypedQuery<T> query = getQuery(param);
        return query.getResultList();
    }

    @Override
    public Page<T> findPage(Map<String, Object> filters) {
        Integer page = Integer.parseInt(filters.get("curPage").toString()) - 1;
        Integer size = Integer.parseInt(filters.get("pageSize").toString());
        PageRequest pageRequest = new PageRequest(page, size);
        TypedQuery<T> query = getQuery(filters);
        query.setFirstResult(pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());
        return new PageImpl<T>(query.getResultList());
    }

    protected TypedQuery<T> getQuery(Map filters) {
        return getQuery(filters, getDomainClass());
    }

    protected <S extends T> TypedQuery<S> getQuery(Map filters, Class<S> domainClass) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<S> query = builder.createQuery(domainClass);
        Root<S> root = applyFiltersToCriteria(filters, domainClass, query);
        query.select(root);
        return entityManager.createQuery(query);
    }

    private <S, U extends T> Root<U> applyFiltersToCriteria(Map filters, Class<U> domainClass, CriteriaQuery<S> query) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Root<U> root = query.from(domainClass);
        List<Predicate> predicateList = new ArrayList<>();
        if (!filters.isEmpty()) {
            filters.remove("curPage");
            filters.remove("pageSize");
            Set keySet = filters.keySet();

            for (Object o : keySet) {
                String key = o.toString();
                Object value = filters.get(key);

                if (value instanceof Collection) {
                    Collection valueCollection = (Collection) value;
                    if (StringUtils.containsIgnoreCase(key, "-in")) {
                        int pos = key.lastIndexOf("-in");
                        key = key.substring(0, pos);
                        Iterator iterator = valueCollection.iterator();
                        CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get(key));
                        while (iterator.hasNext()) {
                            in.value(iterator.next());
                        }
                        predicateList.add(criteriaBuilder.in(in));
                    } else if (StringUtils.containsIgnoreCase(key, "-notin")) {
                        int pos = key.lastIndexOf("-notin");
                        key = key.substring(0, pos);
                        Iterator iterator = valueCollection.iterator();
                        CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get(key));
                        while (iterator.hasNext()) {
                            in.value(iterator.next());
                        }
                        predicateList.add(criteriaBuilder.not(in));
                    }
                    continue;
                }

                String valueStr = value.toString();
                if (StringUtils.isEmpty(valueStr) && !StringUtils.containsIgnoreCase(key, "-asc")
                        && !StringUtils.containsIgnoreCase(key, "-desc"))
                    continue;

                if (StringUtils.containsIgnoreCase(key, "-ignore"))
                    continue;

                if (StringUtils.containsIgnoreCase(key, "-like")) {
                    int pos = key.lastIndexOf("-like");
                    key = key.substring(0, pos);
                    predicateList.add(criteriaBuilder.like(root.get(key), "%" + valueStr + "%"));
                } else if (StringUtils.containsIgnoreCase(key, "-gteq")) {
                    int pos = key.lastIndexOf("-gteq");
                    key = key.substring(0, pos);
                    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get(key), valueStr));
                } else if (StringUtils.containsIgnoreCase(key, "-lteq")) {
                    int pos = key.lastIndexOf("-lteq");
                    key = key.substring(0, pos);
                    predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get(key), valueStr));
                } else if (StringUtils.containsIgnoreCase(key, "-gt")) {
                    int pos = key.lastIndexOf("-gt");
                    key = key.substring(0, pos);
                    predicateList.add(criteriaBuilder.greaterThan(root.get(key), valueStr));
                } else if (StringUtils.containsIgnoreCase(key, "-lt")) {
                    int pos = key.lastIndexOf("-lt");
                    key = key.substring(0, pos);
                    predicateList.add(criteriaBuilder.lessThan(root.get(key), valueStr));
                } else if (StringUtils.containsIgnoreCase(key, "-asc")) {
                    int pos = key.lastIndexOf("-asc");
                    key = key.substring(0, pos);
                    query.orderBy(criteriaBuilder.asc(root.get(key)));
                } else if (StringUtils.containsIgnoreCase(key, "-desc")) {
                    int pos = key.lastIndexOf("-desc");
                    key = key.substring(0, pos);
                    query.orderBy(criteriaBuilder.desc(root.get(key)));
                } else if (StringUtils.containsIgnoreCase(key, "-not")) {
                    int pos = key.lastIndexOf("-not");
                    key = key.substring(0, pos);
                    predicateList.add(criteriaBuilder.notEqual(root.get(key), valueStr));
                } else {
                    predicateList.add(criteriaBuilder.equal(root.get(key), valueStr));
                }
            }
        }
        Predicate[] predicates = predicateList.toArray(new Predicate[0]);
        query.where(predicates);
        return root;
    }
}
