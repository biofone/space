package com.chao.dbmanage.sys.vo;
import java.io.Serializable;
import java.util.Date;

import com.chao.dbmanage.sys.entity.SysDept;
import lombok.Data;
@Data
public class SysUserDeptVo implements Serializable {
	private static final long serialVersionUID = 1138534420870847335L;
	private Integer id;
	private String username;
	private String password;//md5
	/**盐值(与密码一起加密，保证密码更加安全)*/
	private String salt;
	private String email;
	private String mobile;
    /**用户状态：禁用、启用*/
	private Integer valid=1;
	private SysDept sysDept; //private Integer deptId;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
