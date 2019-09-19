package java.hsx.singleton.threadlocal;

/**每个独立线程内 单例**/
public class ThreadLocalSingleton {

    private ThreadLocalSingleton(){}

    public static final ThreadLocal<ThreadLocalSingleton> threadLocalSingleton =
            ThreadLocal.withInitial(() -> new ThreadLocalSingleton());

    public static ThreadLocalSingleton getInstance(){
        String str="";

        return threadLocalSingleton.get();
    }
}
