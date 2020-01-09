package com.chao.dbmanage.common.util;

import com.chao.dbmanage.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;


public class ShiroUtils {
   public static String getUsername() {
	   return getUser().getUsername();
   }
   /**获取登录用户*/
   public static SysUser getUser() {
	   return (SysUser)SecurityUtils.getSubject().getPrincipal();
   }
}
