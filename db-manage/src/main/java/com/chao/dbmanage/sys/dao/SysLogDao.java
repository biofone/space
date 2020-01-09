package com.chao.dbmanage.sys.dao;

import com.chao.dbmanage.sys.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysLogDao {
    /**
     * 用于持久化用户行为日志信息
     * @param entity
     * @return
     */
    int insertObject(SysLog entity);
    /**
     * 基于条件分页查询日志信息
     * @param username 查询条件
     * @param startIndex 当前页的起始位置
     * @param pageSize 当前页的页面大小
     * @return 当前页的日志记录信息
     */
    List<SysLog> findPageObjects(
            @Param("username")String username,
            @Param("startIndex")Integer startIndex,
            @Param("pageSize")Integer pageSize);

    /**
     * 基于条件查询总记录数
     * @param username 查询条件
     * @return 总记录数
     */
    int getRowCount(String username);

    int deleteObjects(@Param("ids") Integer... ids);
}
