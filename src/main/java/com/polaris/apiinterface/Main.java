package com.polaris.apiinterface;

import com.polaris.apiinterface.client.ApiClient;
import com.polaris.apiinterface.model.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Administrator
 * @Create 2024-03-08 15:47
 * @Version 1.0
 * ClassName Main
 * Package com.polaris.apiinterface
 * Description
 */
@Slf4j
public class Main {

    public static void main (String[] args){
        String accessKey = "polaris";
        String secretKey = "abcdefgh";
        ApiClient apiClient = new ApiClient(accessKey,secretKey);
        User user = new User();
        user.setUsername("开心果3");
        log.info("-----{}",apiClient.getNameByGet("开心果1"));
        log.info("-----{}",apiClient.getNameByPost1("开心果2"));
        log.info("-----{}",apiClient.getNameByPost2(user));
    }
}
