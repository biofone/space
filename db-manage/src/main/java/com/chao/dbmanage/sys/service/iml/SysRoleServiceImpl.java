package com.chao.dbmanage.sys.service.iml;

import com.chao.dbmanage.common.annotation.RequiredLog;
import com.chao.dbmanage.common.exception.ServiceException;
import com.chao.dbmanage.common.vo.CheckBox;
import com.chao.dbmanage.common.vo.PageObject;
import com.chao.dbmanage.sys.dao.SysRoleDao;
import com.chao.dbmanage.sys.dao.SysRoleMenuDao;
import com.chao.dbmanage.sys.dao.SysUserRoleDao;
import com.chao.dbmanage.sys.entity.SysRole;
import com.chao.dbmanage.sys.service.SysRoleService;
import com.chao.dbmanage.sys.vo.SysRoleMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired(required = false)
    private SysRoleDao sysRoleDao;
    @Autowired(required = false)
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired(required = false)
    private SysUserRoleDao sysUserRoleDao;
    @RequiredLog
    @Cacheable("roleCache")
    @Override
    public List<CheckBox> findObjects() {
        return sysRoleDao.findObjects();
    }

    @Override
    public SysRoleMenuVo findObjectById(Integer id) {
        if(id==null||id<1)
            throw new IllegalArgumentException("id值无效");
        SysRoleMenuVo rm=sysRoleDao.findObjectById(id);
        if(rm==null)
            throw new ServiceException("记录可能已不存在");
        return rm;
    }

    @Override
    public int updateObject(SysRole entity, Integer[] menuIds) {
        //1.参数有效性校验
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("角色名不允许为空");
        if(menuIds==null||menuIds.length==0)
            throw new ServiceException("必须为角色分配权限");
        //2.保存角色自身信息
        int rows=sysRoleDao.updateObject(entity);
        //3.保存角色菜单关系数据
        //3.1)先删除原有关系数据
        sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
        //3.2)添加新的关系数据
        sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
        //4.返回业务结果
        return rows;
    }
    @Override
    public int saveObject(SysRole entity, Integer[] menuIds) {
        //1.参数有效性校验
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("角色名不允许为空");
        if(menuIds==null||menuIds.length==0)
            throw new ServiceException("必须为角色分配权限");
        //2.保存角色自身信息
        int rows=sysRoleDao.insertObject(entity);
        //3.保存角色菜单关系数据
        sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
        //4.返回业务结果
        return rows;
    }

    @Override
    public int deleteObject(Integer id) {
        //1.参数校验
        if(id==null||id<1)
            throw new IllegalArgumentException("id值无效");
        //2.删除关系数据
        sysRoleMenuDao.deleteObjectsByRoleId(id);
        sysUserRoleDao.deleteObjectsByRoleId(id);
        //3.删除自身信息
        int rows=sysRoleDao.deleteObject(id);
        //4.返回结果
        return rows;
    }
    @Override
    public PageObject<SysRole> findPageObjects(
            String name, Integer pageCurrent) {
        //1.参数验证
        if(pageCurrent == null || pageCurrent <1)
            throw new IllegalArgumentException("单前页码不正确");
        //2.基于条件查询总记录数
        int rowCount = sysRoleDao.getRowCount(name);
        if(rowCount == 0)
            throw new ServiceException("记录不存在");
        //3.基于条件查询当前页记录
        int pageSize = 2;
        int startIndex = (pageCurrent-1)*pageSize;
        List<SysRole> records = sysRoleDao.findPageObjects(name, startIndex, pageSize);
        //4.封装分页信息和当前页记录
        PageObject<SysRole> pageObject = new PageObject<>();
        pageObject.setRowCount(rowCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setRecords(records);
        pageObject.setPageCount((rowCount-1)/pageSize+1);
        return pageObject;
    }
}
