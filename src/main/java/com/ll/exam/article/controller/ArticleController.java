package com.ll.exam.article.controller;

import com.ll.exam.Con;
import com.ll.exam.Request;
import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.article.service.ArticleService;

@Controller //Controller의 의미 : ArticleController가 controller임을 의미
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/usr/article/list/{boardCode}") // /usr/article/list/free 와 같이 관련된 요청을 처리하는 함수이다.
    // 아래 showList 는 Get /usr/article/list 으로 요청이 왔을 때 실행 되어야 하는 함수이다.
    public void showList(Request rq) {
        rq.println("게시물 리스트");
    }


    @GetMapping("/usr/article/detail/{boardCode}")
    public void showDetail(Request rq) {
        rq.println("게시물 상세페이지<br>");

        String boardCode = rq.getParam("boardCode", ""); // 곧 기능 구현
        long id = rq.getLongParam("id", -1); // 곧 기능 구현


        rq.println("%s 게시판, %d번 게시물".formatted(boardCode, id));
    }

    @GetMapping("/usr/article/modify/{boardCode}/{id}")
    public void showModify(Request rq) {
        rq.println("게시물 수정페이지<br>");

        String boardCode = rq.getParam("boardCode", ""); // 곧 기능 구현
        long id = rq.getLongParam("id", -1); // 곧 기능 구현

        rq.println("%s 게시판, %d번 게시물".formatted(boardCode, id));
    }
}
