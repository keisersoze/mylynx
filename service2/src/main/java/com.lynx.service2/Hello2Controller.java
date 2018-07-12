package com.lynx.service2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello2Controller {

    @RequestMapping("/Hello")
    public String index() {
        return "Greetings!";
    }

}