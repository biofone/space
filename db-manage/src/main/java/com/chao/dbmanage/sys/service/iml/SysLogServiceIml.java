package com.chao.dbmanage.sys.service.iml;

import com.chao.dbmanage.common.exception.ServiceException;
import com.chao.dbmanage.common.vo.PageObject;
import com.chao.dbmanage.sys.dao.SysLogDao;
import com.chao.dbmanage.sys.entity.SysLog;
import com.chao.dbmanage.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysLogServiceIml implements SysLogService {
    @Autowired(required = false)
    private SysLogDao sysLogDao;
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveObject(SysLog entity) {
        System.out.println("log.thread.name:"+Thread.currentThread().getName());
        sysLogDao.insertObject(entity);
    }

    @Override
    public PageObject<SysLog> findPageObjects(
            String username, Integer pageCurrent) {
        //1.验证参数合法性
        if(pageCurrent == null || pageCurrent < 1)
            throw new IllegalArgumentException("当前页码不正确");
        //2.基于条件查询总记录数
        int rowCount = sysLogDao.getRowCount(username);
        if(rowCount == 0)
            throw new ServiceException("系统没有查到对应记录");
        //3.基于条件查询当前页记录
        int pageSize = 2;
        int startIndex = (pageCurrent-1)*pageSize;
        List<SysLog> records = sysLogDao.findPageObjects(username, startIndex, pageSize);
        //4.对分页信息以及当前页记录进行封装
        PageObject<SysLog> pageObject = new PageObject<>();
        pageObject.setPageCount((rowCount-1)/pageSize+1);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(rowCount);
        pageObject.setRecords(records);
        //5.返回封装结果
        return pageObject;
    }

    @Override
    public int deleteObjects(Integer... ids) {
        //1.参数校验
        if(ids==null||ids.length==0)
            throw new IllegalArgumentException("参数值无效");
        //2.执行删除
        int rows;
        try {
            rows=sysLogDao.deleteObjects(ids);
        }catch (Throwable e) {
            e.printStackTrace();
            throw new ServiceException("系统故障，维护中");
        }
        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        //3.返回结果
        return rows;
    }
}
