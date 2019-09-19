package hsx.singleton.doublelockcheck;

/**双重检查锁机制   懒汉式**/
public class DoubleLockCheckSingleton {

    public volatile static DoubleLockCheckSingleton doubleLockCheckSingleton = null;

    private DoubleLockCheckSingleton(){}

    public static DoubleLockCheckSingleton getInstance(){
        /**最外层if 为了过滤掉 doubleLockCheckSingleton不为null 提高效率 否则 锁就退化加到方法上了**/
        if (doubleLockCheckSingleton == null) {
            synchronized (DoubleLockCheckSingleton.class){
                if (doubleLockCheckSingleton == null) {
                    doubleLockCheckSingleton = new DoubleLockCheckSingleton();
                    //1.分配内存给这个对象
                    //2.初始化对象
                    //3.设置doubleLockCheckSingleton指向刚分配的内存地址
                    /**由于JVM会优化指令，导致指令重排序，所以可能2,3会重新排序**/
                }
            }
        }
        return doubleLockCheckSingleton;
    }
}
