package com.chao.dbmanage.sys.service;

import com.chao.dbmanage.common.vo.Node;
import com.chao.dbmanage.sys.entity.SysDept;

import java.util.List;
import java.util.Map;


public interface SysDeptService {
	 List<Map<String,Object>> findObjects();
	 List<Node> findZTreeNodes();
	 int saveObject(SysDept entity);
	 int updateObject(SysDept entity);
	 int deleteObject(Integer id);
}
