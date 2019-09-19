package java.hsx.singleton.innerclass;

/**内部类   线程安全  懒汉式**/
public class InnerClassSingleton {
    private InnerClassSingleton(){
        //防止被反射破坏
        if (InnerClassHolder.innerClassSingleton == null) {
            throw new RuntimeException("不允许创建多实例");
        }
    }
    public static final InnerClassSingleton getInstance(){
        return InnerClassHolder.innerClassSingleton;
    }
    private static class InnerClassHolder{
        private static final InnerClassSingleton innerClassSingleton = new InnerClassSingleton();
    }
}
