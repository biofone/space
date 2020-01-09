package com.chao.dbmanage.sys.service;

import com.chao.dbmanage.common.vo.Node;
import com.chao.dbmanage.sys.entity.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuService {
    int updateObject(SysMenu entity);
    int saveObject(SysMenu entity);
    List<Node> findZtreeMenuNodes();
    List<Map<String,Object>> findObjects();
    int deleteObject(Integer id);
}
