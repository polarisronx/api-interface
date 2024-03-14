package com.polaris.apiinterface.controller;

import com.polaris.apiinterface.model.User;
import java.util.Arrays;

import com.polaris.apiinterface.utils.SignUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Administrator
 * @Create 2024-03-07 21:33
 * @Version 1.0
 * ClassName NameController
 * Package com.polaris.apiinterface.controller
 * Description
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/a")
    public String getNameByGet(String name){
        return "Hello!We've get your name: "+ name;
    }
    @PostMapping("/b")
    public String getNameByPost1(@RequestParam String name){
        return "Hello!We've post your name: "+name;
    }
    @PostMapping("/c")
    public String getNameByPost2(@RequestBody User user,HttpServletRequest  request){
        // 从请求头中获取 签名认证的参数 的值
        String accessKey = request.getHeader("accessKey");
//        String secretKey = request.getHeader("secretKey"); 不能直接获取获取到密钥
        String body = request.getHeader("body");
        String timestamp = request.getHeader("timestamp");
        String nonce = request.getHeader("nonce");
        String sign = request.getHeader("sign");

        // 进行 权限校验 的验证逻辑
        // 校验 accessKey
        if (!accessKey.equals("polaris")){// 实际上后端的 accessKey 要从数据库中查，这里图方便就直接写死了
            // 抛出运行时异常，表示权限不足
            throw new RuntimeException("权限不足");
        }
        // 校验随机数 nonce
        if (Long.parseLong(nonce) > 10000){
            throw new RuntimeException("无权限");
        }
        // 校验时间戳 timestamp 与 当前时间的差距，超过5分钟说明过期
        if (Math.abs(System.currentTimeMillis()/1000 - Long.parseLong(timestamp)) > 5 * 60){
            throw new RuntimeException("请求过期");
        }
        // 校验签名 sign
        // 假设 secretKey 为 "polaris"， 这里只是简单示例，实际应用中需要从数据库中查
        String expectedSign = SignUtils.genSign(body,"abcdefgh");
        if (!sign.equals(expectedSign)){
            throw new RuntimeException("签名错误");
        }
        return  "Hello!We've post your name: "+user.getUsername();
    }
}
