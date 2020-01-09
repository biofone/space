package com.chao.dbmanage.sys.dao;

import com.chao.dbmanage.common.vo.CheckBox;
import com.chao.dbmanage.sys.entity.SysRole;
import com.chao.dbmanage.sys.vo.SysRoleMenuVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface SysRoleDao {
    /**
     * 查询所有角色
     * @return
     */
    @Select("select id,name from sys_roles")
    List<CheckBox> findObjects();
    /**
     * 基于角色id查询角色以及角色对象的菜单id
     * @param id
     * @return
     */
    SysRoleMenuVo findObjectById(Integer id);
    /**
     * 更新角色自身信息
     * @param entity
     * @return
     */
    int updateObject(SysRole entity);
    /**
     * 保存角色自身信息
     * @param entity
     * @return
     */
    int insertObject(SysRole entity);
    /**
     * 基于角色删除角色自身信息
     * @param id
     * @return
     */
    @Delete("delete from sys_roles where id=#{id}")
    int deleteObject(Integer id);
    /**
     * 分页查询角色信息
     * @param name
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<SysRole> findPageObjects(
            @Param("name")String name,
            @Param("startIndex")Integer startIndex,
            @Param("pageSize")Integer pageSize);
    /**
     * 查询记录总数
     * @param name
     * @return
     */
    int getRowCount(@Param("name") String name);
}
