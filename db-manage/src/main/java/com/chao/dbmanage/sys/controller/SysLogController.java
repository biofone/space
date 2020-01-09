package com.chao.dbmanage.sys.controller;

import com.chao.dbmanage.common.vo.JsonResult;
import com.chao.dbmanage.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/log")
@RestController
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @GetMapping("/doFindPageObjects")
    public JsonResult doFindPageObjects(
            String username,
            @RequestParam Integer pageCurrent) {
        return new JsonResult(sysLogService.findPageObjects(username,pageCurrent));
    }

    @RequestMapping("/doDeleteObjects")
    public JsonResult doDeleteObjects(Integer... ids) {
        sysLogService.deleteObjects(ids);
        return new JsonResult("delete OK");
    }
}
