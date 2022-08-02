package com.ll.exam.article.controller;

import com.ll.exam.Con;
import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.article.service.ArticleService;

@Controller //Controller의 의미 : ArticleController가 controller임을 의미
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/usr/article/list") // /usr/article/list/free와 관련된 요청을 처리하는 함수이다.
    public void showList() {
    }

}
