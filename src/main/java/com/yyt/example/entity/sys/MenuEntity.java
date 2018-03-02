package com.yyt.example.entity.sys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_menu", schema = "wsx", catalog = "")
public class MenuEntity {
    private int menuId;
    private String menuName;
    private String menuUrl;
    private Integer parentId;
    private String menuIcon;
    private Integer menuSort;

    @Id
    @Column(name = "menu_id")
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "menu_url")
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    @Basic
    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "menu_icon")
    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    @Basic
    @Column(name = "menu_sort")
    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuEntity that = (MenuEntity) o;
        return menuId == that.menuId &&
                Objects.equals(menuName, that.menuName) &&
                Objects.equals(menuUrl, that.menuUrl) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(menuIcon, that.menuIcon) &&
                Objects.equals(menuSort, that.menuSort);
    }

    @Override
    public int hashCode() {

        return Objects.hash(menuId, menuName, menuUrl, parentId, menuIcon, menuSort);
    }
}
