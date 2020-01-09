package com.chao.dbmanage.common.vo;
import java.io.Serializable;
import lombok.Data;

/**VO(Value Object)
 * 定义Node对象的目的，是封装树节点信息*/
@Data
public class Node implements Serializable{
	private static final long serialVersionUID = 2048083156365694892L;
	private Integer id;
	private String name;
	private Integer parentId;
	
}
