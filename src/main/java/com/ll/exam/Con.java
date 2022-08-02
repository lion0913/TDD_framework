package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;

public class Con {
   public static final ArticleController articleController;

    static {
        articleController = new ArticleController();
    }
    public static ArticleController getArticleController() {
        return articleController;
    }

}
