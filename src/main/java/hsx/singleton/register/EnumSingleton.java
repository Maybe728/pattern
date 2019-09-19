package java.hsx.singleton.register;


/**注册式单例   枚举写法  线程安全  饿汉式**/
public enum EnumSingleton {
    INSTANCE;
    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
