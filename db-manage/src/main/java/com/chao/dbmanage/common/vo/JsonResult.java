package com.chao.dbmanage.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Member;

@Data
@NoArgsConstructor
public class JsonResult {
    private int state = 1;
    private String message = "ok";
    private Object data;

    public JsonResult(String message) {
        this.message = message;
    }
    public JsonResult(Object data) {
        this.data = data;
    }
    public JsonResult(Throwable e) {
        this.message =e.getMessage();
        this.state = 0;
    }
}
