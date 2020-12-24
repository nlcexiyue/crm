package com.qiangqiang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/24
 * \* Time: 9:03
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class LoginController {
    @GetMapping("/hello")
    public String hello(){
        return "哈哈";
    }
}