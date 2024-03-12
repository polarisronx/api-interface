package com.polaris.apiinterface.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.polaris.apiinterface.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * @Author polaris
 * @Create 2024-03-08 15:24
 * @Version 1.0
 * ClassName ApiClient
 * Package com.polaris.apiinterface.client
 * Description
 */
@Slf4j
public class ApiClient {

    private String accessKey;
    private String secretKey;

    public ApiClient(String accessKey, String secretKey){
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }
    // 构造请求头的私有方法
    private HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("accessKey", accessKey);
        headers.put("secretKey", secretKey);
        return headers;
    }


    public String getNameByGet(String name){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get("http://localhost:8123/api/name/a", paramMap);
        log.info("get请求结果：{}", result);
        return result;
    }

    public String getNameByPost1(String name){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post("http://localhost:8123/api/name/b", paramMap);
        log.info("post1请求结果：{}", result);
        return result;
    }

    public String getNameByPost2(User user){
        // 将User对象转换为JSON格式的字符串
        String jsonStr = JSONUtil.toJsonStr(user);
        // 使用HttpRequest库发送POST请求，并获取服务器的响应
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8123/api/name/c")
                .body(jsonStr)// 将json字符串设置为请求体
                .addHeaders(getHeaders())// 添加请求头，携带AK和SK
                .execute();// 执行请求
        int status = httpResponse.getStatus();
        String result = httpResponse.body();
        log.info("status:{}",status);
        log.info("post2请求结果：{}",result);
        return result;
    }
}
