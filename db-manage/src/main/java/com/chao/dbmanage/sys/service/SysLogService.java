package com.chao.dbmanage.sys.service;

import com.chao.dbmanage.common.vo.PageObject;
import com.chao.dbmanage.sys.entity.SysLog;

public interface SysLogService {
    /**
     * 保存用户行为日志
     * @param entity
     */
    void saveObject(SysLog entity);
    /**
     * 分页查询
     * @param username 基于条件查询
     * @param pageCurrent 当前的页码值
     * @return
     */
    PageObject<SysLog> findPageObjects(String username,Integer pageCurrent);

    /**
     * 基于id执行删除操作
     * @param ids
     * @return
     */
    int deleteObjects(Integer... ids);
}
