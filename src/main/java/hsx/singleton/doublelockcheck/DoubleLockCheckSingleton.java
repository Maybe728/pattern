package java.hsx.singleton.doublelockcheck;

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
