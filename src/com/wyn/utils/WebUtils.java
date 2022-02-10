
package com.wyn.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
@SuppressWarnings("all")
public class WebUtils {
    //<editor-fold desc="Description">

    /**
     * 将Map中的值注入到JavaBean属性去
     *
     * @param bean
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBean(T bean, Map value) {
        try {
            //注入前bean的对象
            System.out.println("租入前" + bean);
            //把所有的请求参数注入到user对象
            BeanUtils.populate(bean, value);
            //注入后bean的对象
            System.out.println("注入之后的对象" + bean);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转成int类型的数据
     *
     * @param id
     * @param defaultVlaue
     * @return
     */
    public static int parseInt(String id, int defaultVlaue) {
        try {
            return Integer.parseInt(id);
        } catch (Exception e) {
//            e.printStackTrace();
            //.out.println("null");
        }
        return defaultVlaue;
    }
    //</editor-fold>
}
