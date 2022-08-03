package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.article.service.ArticleService;
import com.ll.exam.home.controller.HomeController;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
        ArticleController articleController = Con.getObj(ArticleController.class);

        assertThat(articleController).isNotNull();
    }

    @Test
    public void ioc_singleton() {
        ArticleController articleController1 = Con.getObj(ArticleController.class);
        ArticleController articleController2 = Con.getObj(ArticleController.class);

        assertThat(articleController1).isEqualTo(articleController2);
    }

    @Test
    public void ioc_dd() {
        List<String> names = Con.getAllControllers();

        assertThat(names).contains("home");
        assertThat(names).contains("article");
    }

    @Test
    public void ioc__homeController() {
        HomeController homeController = Con.getObj(HomeController.class);

        assertThat(homeController).isNotNull();
    }

    @Test
    public void ioc_homeController_singleton() {
        HomeController homeController1 = Con.getObj(HomeController.class);
        HomeController homeController2 = Con.getObj(HomeController.class);

        assertThat(homeController2).isEqualTo(homeController1);
    }

    @Test
    public void ioc_articleService() {
        ArticleService articleService = Con.getObj(ArticleService.class);

        assertThat(articleService).isNotNull();
    }

//    @Test
//    public void controller_service() {
//        ArticleController articleController = Con.getObj(ArticleController.class);
//
//        ArticleService articleService = Util.reflection.getFieldValue(articleController, "articleService", null);
//
//        assertThat(articleService).isNotNull();
//    }

    @Test
    public void ControllerManager__scanMappings() {
        ControllerManager.init(); // 클래스를 강제로 로딩되게 하려는 목적
    }

    @Test
    public void ControllerManager__라우트정보_개수() {
        Map<String, RouteInfo> routeInfos = ControllerManager.getRouteInfosForTest();

        assertThat(routeInfos.size()).isEqualTo(2);
    }
}
