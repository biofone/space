package com.chao.dbmanage.sys.controller;

import com.chao.dbmanage.common.vo.JsonResult;
import com.chao.dbmanage.sys.entity.SysMenu;
import com.chao.dbmanage.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/menu")
@RestController
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;
    @RequestMapping("/doFindObjects")
    public JsonResult doFindObjects() {
        return new JsonResult(sysMenuService.findObjects());
    }
    @RequestMapping("/doDeleteObject")
    public JsonResult doDeleteObject(Integer id) {
        sysMenuService.deleteObject(id);
        return new JsonResult("delete OK");
    }
    @RequestMapping("/doSaveObject")
    public JsonResult doSaveObject(SysMenu entity) {
        sysMenuService.saveObject(entity);
        return new JsonResult("save OK");
    }
    @RequestMapping("/doUpdateObject")
    public JsonResult doUpdateObject(SysMenu entity) {
        sysMenuService.updateObject(entity);
        return new JsonResult("update OK");
    }
    @GetMapping("/doFindZtreeMenuNodes")
    public JsonResult doFindZtreeMenuNodes() {
        return new JsonResult(sysMenuService.findZtreeMenuNodes());
    }
}
