package com.coy.pay.route.adapter.support;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class工具类
 *
 * @author chenck
 * @date 2018/8/23 19:20
 */
public class ClassUtil {

    /**
     * 获取指定Class所有子类
     *
     * @param clazz
     * @return
     * @author chenck
     * @date 2018/8/23 19:16
     */
    public static List<Class> getAllSubClass(Class clazz) {
        String packageName = clazz.getPackage().getName();
        Reflections reflections = new Reflections(packageName);
        Set<Class> subClassSet = reflections.getSubTypesOf(clazz);

        List<Class> subClassList = new ArrayList<>();
        for (Class subClass : subClassSet) {
            subClassList.add(subClass);
        }
        return subClassList;
    }

}
