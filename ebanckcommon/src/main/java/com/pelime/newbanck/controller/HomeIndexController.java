package com.pelime.newbanck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeIndexController {



    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String defaultLogin() {
        return "login";
    }

    @RequestMapping(value = "/wae", method = RequestMethod.GET)
    public String worryAboutEBank(){
        return "ebank-spend";
    }

}
