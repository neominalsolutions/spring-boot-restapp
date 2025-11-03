package com.mertalptekin.springbootrestapp.controller;


import com.mertalptekin.springbootrestapp.springContext.custom.WebRequestBasedBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    private final WebRequestBasedBean webRequestBasedBean;

    public DemoController(WebRequestBasedBean webRequestBasedBean) {
        this.webRequestBasedBean = webRequestBasedBean;
    }


    @GetMapping
    public String demo() {
        webRequestBasedBean.test();
        return "Demo Controller is working...";
    }


}
