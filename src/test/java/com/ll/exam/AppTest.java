package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    public void junit_assertThat() {
        int rs = 10+20;
        assertThat(rs).isEqualTo(30);
    }

    @Test
    public void ioc_articleController() {
        //con을 통해 articleController를 받아옴
        ArticleController articleController = Con.getArticleController();

        assertThat(articleController).isNotNull();
    }

    @Test
    public void ioc_singleton() {
        ArticleController articleController1 = Con.getArticleController();
        ArticleController articleController2 = Con.getArticleController();

        assertThat(articleController1).isEqualTo(articleController2);
    }
}
