package org.geektimes.infrastructure;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SPI 容器
 *
 * @author YuI
 * @date 2021/3/4 1:12 
 * @since v1-SNAPSHOT
 **/
public class SpiContainer {

    private static Logger logger = Logger.getLogger(SpiContainer.class.getName());

    private static final ConcurrentMap<String, ConcurrentMap<String, Object>> container;

    static {
        container = new ConcurrentHashMap<>();
    }

    public static <T> T getDefaultObject(Class<T> clazz) {
        ConcurrentMap<String, Object> interfaceMap = container.getOrDefault(clazz.getName(), new ConcurrentHashMap<>());

        if (!interfaceMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : interfaceMap.entrySet()) {
                return (T) entry.getValue();
            }
        }

        try {
            for (T controller : ServiceLoader.load(clazz)) {
                Class subClass = controller.getClass();
                Object subObject = subClass.getConstructor().newInstance();
                interfaceMap.putIfAbsent(subClass.getName(), subObject);
                container.putIfAbsent(clazz.getName(), interfaceMap);

                return (T) subObject;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        return null;
    }

}
