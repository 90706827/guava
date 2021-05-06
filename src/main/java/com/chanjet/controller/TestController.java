package com.chanjet.controller;

import com.chanjet.executor.pool.FixedThreadPool;
import com.chanjet.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jimmy
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;


    @RequestMapping(path = "/test",method = RequestMethod.GET)
    public String test(){
        testService.test(1);
        return "test";
    }
}
