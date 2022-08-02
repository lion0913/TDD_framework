package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.controller.HomeController;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Con {
    public static final ArticleController articleController;
    public static final HomeController homeController;

    static {
//
        Class<?> clazz = null;
        try {
            articleController = ArticleController.class.getDeclaredConstructor().newInstance();
            homeController = HomeController.class.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ArticleController getArticleController() {
        return articleController;
    }

    public static List<String> getAllControllers() {
        //controller들을 스캔해서 수집
        List<String> names = new ArrayList<>();

        Reflections ref = new Reflections("com.ll.exam");
        for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) {
            String name = cls.getSimpleName(); // HomeController
            name = name.replace("Controller", ""); // Home
            name = Util.str.decapitalize(name); // home

            names.add(name.replace("Controller", name));
        }

        return names;
    }


    public static HomeController getHomeController() {
        return homeController;
    }

}
