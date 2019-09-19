package java.hsx.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**注册式单例之容器式写法   非线程安全  懒汉式**/
public class ContainerSingleton {

    private ContainerSingleton() {
    }

    public static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getBean(String className) {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object obj = null;
                try {
                    obj = Class.forName(className).newInstance();
                    ioc.put(className, obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                return ioc.get(className);
            }
        }
    }
}
