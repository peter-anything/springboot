package com.galaxy.mecury.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = -2862375031151784165L;
    /**
     * 状态码：0-成功，1-失败
     */
    private int code;

    /**
     * 错误消息，如果成功可为空或SUCCESS
     */
    private String msg;

    /**
     * 返回结果数据
     */
    private T data;

    public static ResponseData success() {
        return success(null);
    }

    public static ResponseData success(Object data) {
        ResponseData result = new ResponseData();
        result.setCode(ResponseCode.SUCCESS.getCode());
        result.setMsg("SUCCESS");
        result.setData(data);
        return result;
    }

    public static ResponseData fail(String msg) {
        return fail(msg, null);
    }

    public static ResponseData fail(String msg, Object data) {
        ResponseData result = new ResponseData();
        result.setCode(ResponseCode.FAIL.getCode());
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
