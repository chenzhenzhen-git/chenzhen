package com.bonc.dxbrgrmp.response;

/**
 * @program: attendance
 * @Description:
 * @Author: 周建波
 * @Date: 2019-01-18 15:45
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class ResultModel implements Serializable {

    public static final int RETURN_CODE_SUCCESS = 0;
    public static final int RETURN_CODE_FAIL = 1;

    private ResultModel(){
        code = RETURN_CODE_SUCCESS;
        body = null;
    }

    private ResultModel(int code, Object body) {
        this.code = code;
        this.body = body;
    }

    private int code = 0;
    private Object body;


    public int getCode() {
        return code;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * 构造返回模型
     *
     * @param flag
     * @return
     */
    public static ResultModel buildModel(boolean flag) {
        if (flag) {
            return successModel();
        }
        return failModel();
    }

    /**
     * 获取成功的 ResultModel
     *
     * @return
     */
    public static ResultModel successModel() {
        return successModel("");
    }

    /**
     * 获取成功的 ResultModel
     *
     * @return
     */
    public static ResultModel successModel(Object body) {
        return new ResultModel(RETURN_CODE_SUCCESS, body);
    }

    /**
     * 获取失败的 ResultModel
     *
     * @return
     */
    public static ResultModel failModel() {
        return failModel("");
    }

    /**
     * 获取失败的 ResultModel
     *
     * @return
     */
    public static ResultModel failModel(Object body) {
        return new ResultModel(RETURN_CODE_FAIL, body);
    }

    public static ResultModel successModel(String catalogoName, Object body) {
        Map jobj = new HashMap();
        jobj.put(catalogoName,body);
        return new ResultModel(RETURN_CODE_SUCCESS, jobj);
    }

}
