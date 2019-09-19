package java.hsx.singleton.hungry;

/**饿汉式**/
public class HungrySingleton {
//    public static final HungrySingleton hungrySingleton = new HungrySingleton();

    public static HungrySingleton hungrySingleton=null;

    static {
        hungrySingleton = new HungrySingleton();
    }

    private HungrySingleton(){}

    public  static HungrySingleton getInstance(){
        return hungrySingleton;
    }
}
