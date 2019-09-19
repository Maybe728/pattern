package java.hsx.singleton.innerclass;

/**内部类   线程安全  懒汉式**/

/**完美的解决了饿汉式的内存浪费和synchronized的性能问题**/
public class InnerClassSingleton {
    private InnerClassSingleton(){
        //防止被反射破坏
        if (InnerClassHolder.innerClassSingleton == null) {
            throw new RuntimeException("不允许创建多实例");
        }
    }

    /**static 为了使单例内存共享  final为了让方法不被重写，重载 **/
    public static final InnerClassSingleton getInstance(){
        return InnerClassHolder.innerClassSingleton;
    }

    /**默认不加载，使用的时候才会加载**/
    private static class InnerClassHolder{
        private static final InnerClassSingleton innerClassSingleton = new InnerClassSingleton();
    }
}
