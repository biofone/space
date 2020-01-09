package com.chao.dbmanage.sys.dao;

import com.chao.dbmanage.common.vo.Node;
import com.chao.dbmanage.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface SysMenuDao {
    List<String> findPermissions(
            @Param("menuIds")
                    Integer[] menuIds);
    /**
     *  将对象持久化到数据库
     * @param entity
     * @return
     */
    int updateObject(SysMenu entity);
    /**
     *  将对象持久化到数据库
     * @param entity
     * @return
     */
    int insertObject(SysMenu entity);

    @Select("select id,name,parentId from sys_menus")
    List<Node> findZtreeMenuNodes();
    /**
     * 删除菜单信息
     * @param id
     * @return
     */
    @Delete("delete from sys_menus where id=#{id}")
    int deleteObject(Integer id);

    /**
     * 基于id统计子菜单数
     * @param id
     * @return
     */
    @Select("select count(*) from sys_menus where parentId=#{id}")
    int getChildCount(Integer id);
    /**
     * 查询所有菜单和上级菜单信息
     * @return
     */
    List<Map<String,Object>> findObjects();
}
