package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Util {
    public static class cls {
        public static <T> T newObj(Class<T> cls, T defaultVal) {
            try {
                return (T)cls.getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                return defaultVal;
            } catch (IllegalAccessException e) {
                return defaultVal;
            } catch (InvocationTargetException e) {
                return defaultVal;
            } catch (NoSuchMethodException e) {
                return defaultVal;
            }
        }
    }

    public static class reflection {
        public static <T> T getFieldValue(Object o, String fieldName, T defaultValue) {
            Field field = null;

            try {
                field = o.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                return defaultValue;
            }

            field.setAccessible(true);

            try {
                return (T)field.get(o);
            } catch (IllegalAccessException e) {
                return defaultValue;
            }
        }
    }
    public static class str {

        public static String decapitalize(String string) {
            if (string == null || string.length() == 0) {
                return string;
            }

            char c[] = string.toCharArray();
            c[0] = Character.toLowerCase(c[0]);

            return new String(c);
        }
    }
}
