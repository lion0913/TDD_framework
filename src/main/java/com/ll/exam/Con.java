package com.ll.exam;

import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.Repository;
import com.ll.exam.annotation.Service;
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
        scanComponents();
    }

    private static void scanComponents() {
        scanRepositories();
        scanServices();
        scanControllers();

        // controller랑 service 의존관계 조립
        resolveDependenciesComponent();
    }

    private static void resolveDependenciesComponent() {
        for(Class cls : objects.keySet()) {
            Object o = objects.get(cls);
            resolveDependencies(o);
        }
    }

    private static void resolveDependencies(Object o) {
        Arrays.asList(o.getClass().getDeclaredFields())
                .stream()
                .filter(f -> f.isAnnotationPresent(Autowired.class))
                .map(field -> {
                    field.setAccessible(true);
                    return field;
                })
                .forEach(field -> {
                    Class cls = field.getType();
                    Object dependency = objects.get(cls);

                    try {
                        field.set(o, dependency);
                    } catch (IllegalAccessException e) {

                    }
                });
    }

    private static void scanServices() {
        Reflections ref = new Reflections(App.BASE_PAGE_PATH);
        for (Class<?> cls : ref.getTypesAnnotatedWith(Service.class)) {
            objects.put(cls, Util.cls.newObj(cls, null));
        }
    }

    private static void scanControllers() {
        Reflections ref = new Reflections(App.BASE_PAGE_PATH);
        for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) {
            objects.put(cls, Util.cls.newObj(cls, null));
        }
    }

    private static void scanRepositories() {
        Reflections ref = new Reflections(App.BASE_PAGE_PATH);
        for (Class<?> cls : ref.getTypesAnnotatedWith(Repository.class)) {
            objects.put(cls, Util.cls.newObj(cls, null));
        }
    }

    public static <T> T getObj(Class<T> cls) {
        return (T) objects.get(cls);
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
