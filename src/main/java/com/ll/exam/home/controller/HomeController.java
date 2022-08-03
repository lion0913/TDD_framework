package com.ll.exam.home.controller;

import com.ll.exam.Request;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/usr/home/main")
    public void showMain(Request rq) {
        rq.println("메인 페이지");
    }
}
