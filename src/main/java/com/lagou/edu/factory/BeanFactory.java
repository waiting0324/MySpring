package com.lagou.edu.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 6550
 * @date 2020/1/6 上午 09:10
 * @description
 */
public class BeanFactory {

    private static Map<String, Object> map = new HashMap<>();

    static {
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();

            List<Element> nodeList = rootElement.selectNodes("//bean");

            for (Element element : nodeList) {
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                Class<?> aClass = Class.forName(clazz);
                Object o = aClass.newInstance();
                map.put(id, o);
            }

            List<Element> propertyList = rootElement.selectNodes("//property");
            for (Element element : propertyList) {
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");

                String parentId = element.getParent().attributeValue("id");
                Object parentObject = map.get(parentId);

                Method[] methods = parentObject.getClass().getMethods();
                for (Method method : methods) {
                    if(method.getName().equalsIgnoreCase("set" + name)) {
                        Object o = map.get(ref);
                        method.invoke(parentObject, o);
                    }
                }
                map.put(parentId, parentObject);
            }

        } catch (DocumentException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String id) {
        return map.get(id);
    }

}
