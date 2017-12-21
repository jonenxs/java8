package com.nxs.annotation;


import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author 57014
 */
public class AnnotationTest {

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show(){

    }


    @Test
    public void test1() throws NoSuchMethodException {
        Class<AnnotationTest> clazz = AnnotationTest.class;
        Method method = clazz.getMethod("show");
        MyAnnotation[] annotationsByType = method.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : annotationsByType) {
            System.out.println(myAnnotation.value());
        }
    }
}
