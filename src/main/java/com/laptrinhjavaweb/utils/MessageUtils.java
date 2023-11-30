package com.laptrinhjavaweb.utils;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageUtils {
    public Map<String,String> getMessage(String message){
        Map<String,String> result = new HashMap<>();
        if(message.equals("update_success")){
           result.put("message","Update Success");
           result.put("alert","success");
        } else if (message.equals("insert_success")) {
            result.put("message","Insert Success");
            result.put("alert","success");
        } else if (message.equals("delete_success")) {
            result.put("message","Delete Success");
            result.put("alert","success");
        } else if (message.equals("error_system")) {
            result.put("message","Error System");
            result.put("alert","danger");
        } else if (message.equals("register_success")) {
            result.put("message","Chúc mừng bạn đã đăng ký thành công");
            result.put("alert","success");
        } else if (message.equals("failed_password")) {
            result.put("message","Mật khẩu nhập lại không đúng");
            result.put("alert","danger");
        } else if (message.equals("account_exist")) {
            result.put("message","Tên người dùng đã tồn tại");
            result.put("alert","danger");
        }
        return result;
    }
}
