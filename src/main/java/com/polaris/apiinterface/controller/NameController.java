package com.polaris.apiinterface.controller;

import com.polaris.apiinterface.model.User;
import java.util.Arrays;
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
    public String getNameByPost2(@RequestBody User user){
        return  "Hello!We've post your name: "+user.getUsername();
    }
}
