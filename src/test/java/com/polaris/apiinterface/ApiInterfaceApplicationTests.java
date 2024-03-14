package com.polaris.apiinterface;

import com.polaris.papiclientsdk.client.PapiClient;
import com.polaris.papiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiInterfaceApplicationTests {

    @Resource
    private PapiClient papiClient;
    @Test
    void contextLoads (){
        // 测试 papiClient的getNameByGet方法，传入参数 polaris，并将返回的结果赋值给 result1 变量
        String result1 = papiClient.getNameByGet("polaris");

        // 创建一个User对象
        User user = new User();
        user.setUsername("polarisronx");

        // 调用papiClient的getNameByPost方法，传入参数 user，并将返回的结果赋值给 result2 变量
        String result2 = papiClient.getNameByPost2(user);

        // 打印 result1 和 result2
        System.out.println(result1);
        System.out.println(result2);

    }

}
