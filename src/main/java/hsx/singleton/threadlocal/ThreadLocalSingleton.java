package java.hsx.singleton.threadlocal;

public class ThreadLocalSingleton {

    private ThreadLocalSingleton(){}

    public static final ThreadLocal<ThreadLocalSingleton> threadLocalSingleton =
            ThreadLocal.withInitial(() -> new ThreadLocalSingleton());

    public static ThreadLocalSingleton getInstance(){
        return threadLocalSingleton.get();
    }
}
