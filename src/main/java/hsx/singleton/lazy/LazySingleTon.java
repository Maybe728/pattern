package hsx.singleton.lazy;

public class LazySingleTon {

    public static LazySingleTon lazySingleton = null;

    private LazySingleTon(){}

    /**
     * @Author HuangShiXing
     * @Description TODO(懒汉式单例，非线程安全)
     * @Date 8:50 2019/9/18
     * @Param []
     * @return hsx.singleton.lazy.LazySingleTon
     **/
    public static LazySingleTon getInstance(){
        if (lazySingleton == null) {
            lazySingleton = new LazySingleTon();
        }
        return lazySingleton;
    }
}
