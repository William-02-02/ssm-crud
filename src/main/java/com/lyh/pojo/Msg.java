package com.lyh.pojo;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    //返回码
    private int code;
    //返回信息
    private String msg;
    //其他信息
    private  Map<String,Object> extend = new HashMap<String, Object>();
    

    
    private static Msg result;
    
    public static Msg success(){
        result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }
    
    public static Msg fail(){
        result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }
    
    //向Msg中添加其他信息
    public Msg add(String key,Object value){
        
        this.getExtend().put(key, value);
        return this;
    }
    

    
    public Map<String, Object> getExtend() {
        return extend;
    }
    
    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
    
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
