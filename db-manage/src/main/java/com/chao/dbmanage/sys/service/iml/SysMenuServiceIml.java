package com.chao.dbmanage.sys.service.iml;

import com.chao.dbmanage.common.exception.ServiceException;
import com.chao.dbmanage.common.vo.Node;
import com.chao.dbmanage.sys.dao.SysMenuDao;
import com.chao.dbmanage.sys.dao.SysRoleMenuDao;
import com.chao.dbmanage.sys.entity.SysMenu;
import com.chao.dbmanage.sys.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class SysMenuServiceIml implements SysMenuService {
    @Autowired(required = false)
    private SysMenuDao sysMenuDao;
    @Autowired(required = false)
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public int updateObject(SysMenu entity) {
        //1.参数校验
        if(entity == null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("菜单名不能为空");
        //2.保存菜单对象
        int rows=0;
        try {
            rows = sysMenuDao.updateObject(entity);
        }catch (Throwable e) {
            log.error(e.getMessage());
            throw new ServiceException("系统维护中");
        }
        return rows;
    }

    @Override
    public int saveObject(SysMenu entity) {
        //1.参数校验
        if(entity == null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("菜单名不能为空");
        //2.保存菜单对象
        int rows=0;
        try {
            rows = sysMenuDao.insertObject(entity);
        }catch (Throwable e) {
            log.error(e.getMessage());
            throw new ServiceException("系统维护中");
        }
        return rows;
    }

    @Override
    public List<Node> findZtreeMenuNodes() {
        return sysMenuDao.findZtreeMenuNodes();
    }

    @Override
    public List<Map<String, Object>> findObjects() {
        List<Map<String, Object>> list = sysMenuDao.findObjects();
        if(list.size() == 0)
            throw new ServiceException("没有对应的菜单");
        return list;
    }

    @Override
    public int deleteObject(Integer id) {
        //1.验证参数
        if(id == null || id<=0)
            throw new IllegalArgumentException("请先选择");
        //2.基于id进行查询
        int count = sysMenuDao.getChildCount(id);
        if(count>0)
            throw new ServiceException("请先删除子菜单");
        //3.删除菜单元素
        int rows = sysMenuDao.deleteObject(id);
        if(rows == 0)
            throw new ServiceException("此菜单可能已经不存在");
        //4.删除角色-菜单关系数据
        sysRoleMenuDao.deleteObjectsByMenuId(id);

        return rows;
    }
}
