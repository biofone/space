package com.chao.dbmanage.sys.service;

import com.chao.dbmanage.common.vo.CheckBox;
import com.chao.dbmanage.common.vo.PageObject;
import com.chao.dbmanage.sys.entity.SysRole;
import com.chao.dbmanage.sys.vo.SysRoleMenuVo;

import java.util.List;

public interface SysRoleService {
    List<CheckBox> findObjects();
    /**
     * 更新角色以及角色对应的关系数据
     * @param entity
     * @param menuIds
     * @return
     */
    int updateObject(SysRole entity,
                     Integer[] menuIds);
    /**
     * 基于角色id查询角色以及角色对应的菜单id
     * @param id
     * @return
     */
    SysRoleMenuVo findObjectById(Integer id);
    /**
     *  保存角色以及角色和菜单的关系数据
     * @param entity
     * @param menuIds
     * @return
     */
    int saveObject(SysRole entity,Integer[]menuIds);
    /**
     * 基于角色id删除角色以及对应的关系数据
     * @param id
     * @return
     */
    int deleteObject(Integer id);
    /**
     * 分页查询角色信息
     * @param name
     * @param pageCurrent
     * @return
     */
    PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);
}
