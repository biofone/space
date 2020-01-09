package com.chao.dbmanage.sys.vo;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
/**
 * VO：
 * 封装角色以及角色对应的菜单id,数据来自两张表。
 */
@Data
public class SysRoleMenuVo implements Serializable {
	private static final long serialVersionUID = -7213694248989299601L;
	/**角色id*/
	private Integer id;
	/**角色名称*/
	private String name;
	/**角色备注*/
	private String note;
	/**角色对应的菜单id*/
	private List<Integer> menuIds;

	
}
