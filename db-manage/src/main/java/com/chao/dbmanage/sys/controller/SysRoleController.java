package com.chao.dbmanage.sys.controller;

import com.chao.dbmanage.common.vo.JsonResult;
import com.chao.dbmanage.common.vo.PageObject;
import com.chao.dbmanage.sys.entity.SysRole;
import com.chao.dbmanage.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @GetMapping("doFindRoles")
    public JsonResult doFindRoles() {
        return new JsonResult(sysRoleService.findObjects());
    }
    @GetMapping("doFindObjectById")
    public JsonResult doFindObjectById(Integer id) {
        return new JsonResult(sysRoleService.findObjectById(id));
    }
    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysRole entity,Integer[]menuIds) {
        sysRoleService.updateObject(entity,menuIds);
        return new JsonResult("update ok");
    }

    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysRole entity,Integer[]menuIds) {
        sysRoleService.saveObject(entity,menuIds);
        return new JsonResult("save ok");
    }

    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Integer id) {
        sysRoleService.deleteObject(id);
        return new JsonResult("delete ok");
    }
    @RequestMapping("/doFindPageObjects")
    public JsonResult doFindPageObjects(
            String name, Integer pageCurrent) {
        PageObject<SysRole> pageObjects = sysRoleService.findPageObjects(name, pageCurrent);
        return new JsonResult(pageObjects);
    }
}
