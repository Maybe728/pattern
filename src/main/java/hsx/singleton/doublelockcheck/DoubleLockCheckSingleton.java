package java.hsx.singleton.doublelockcheck;

/**双重检查锁机制   懒汉式**/
public class DoubleLockCheckSingleton {

    public static DoubleLockCheckSingleton doubleLockCheckSingleton = null;

    private DoubleLockCheckSingleton(){}

    public static DoubleLockCheckSingleton getInstance(){
        if (doubleLockCheckSingleton == null) {
            synchronized (DoubleLockCheckSingleton.class){
                if (doubleLockCheckSingleton == null) {
                    doubleLockCheckSingleton = new DoubleLockCheckSingleton();
                }
            }
        }
        return doubleLockCheckSingleton;
    }
}
