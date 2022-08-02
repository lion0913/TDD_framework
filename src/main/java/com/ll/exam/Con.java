package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.controller.HomeController;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Con {
    private static Map<Class, Object> objects;

    static {
        objects = new HashMap<>();

        objects.put(ArticleController.class, new ArticleController());
        objects.put(HomeController.class, new HomeController());

    }

    public static <T> T getObj(Class<T> cls) {
        return (T)objects.get(cls);
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

}
