package org.demo.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.demo.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class utils {
    private static Logger logger = LoggerFactory.getLogger(utils.class);
    /**
     * @author mjx
     * @data 2019-10-11
     * @Description  实体类转map
     * @return map
     */
    public static Map<String, Object> convertBeanToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if (null == value) {
                        map.put(key, "");
                    } else {
                        map.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("convertBean2Map Error {}", e);
        }
        return map;
    }

    public static JSONObject getReturn(List<File> files, String msg, Integer code, Integer count) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("count", count);

        JSONArray dataArr = new JSONArray();
        for (File file : files) {
            Map<String, Object> map = utils.convertBeanToMap(file);
            map.put("createDate", getDateString((Date) map.get("createDate")));
            dataArr.add(map);
        }

        jsonObject.put("data", dataArr);

        return jsonObject;
    }

    public static JSONObject getMapReturn(List<Map<String,Object>> lists, String msg, Integer code, Integer count) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("count", count);
        JSONArray dataArr = new JSONArray();
        for (Map<String, Object> map : lists) {
            dataArr.add(map);
        }
        jsonObject.put("data", dataArr);
        return jsonObject;
    }


    public static String getDateString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        return format;
    }
}
