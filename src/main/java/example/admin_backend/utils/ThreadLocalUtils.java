package example.admin_backend.utils;

/**
 * ThreadLocalUtils工具类
 * 为什么要启用ThreadLocal，因为在多个接口方法中，都可能需要调用同一个参数(e.g. id)
 * 传统方法就需要在每个controller中声明一个id值完成参数传递
 * 当请求到达拦截器后，调用本类中的set方法存储id（还有包含的可能的其他数据），可通过调用get方法，指定key值获取对应的value值
 * 还可以保证线程安全，因为不同user发送登录请求到登录拦截器后，都会携带不同的token并进入拦截器等待解析
 * 而当请求到达tomcat后，服务器就会为每一个用户开启一条新线程，在拦截器中的preHandle方法中，在执行ThreadLocal.set方法后就获得对应的包含不同用户的id的解析后的token值
 * 该id值就可以通过get方法获取并被携带到后续的controller，mapper和service层中进行执行，而该id就是本线程的局部变量，以此达到线程隔离
 * 在拦截器afterCompletion方法中执行remove方法，释放内存，防止内存泄露
 */
public class ThreadLocalUtils {
    /**
     * 存储全局的ThreadLocal对象
     */
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal<>();

    /**
     * THREAD_LOCAL对象获取值
     * @return
     * @param <T>
     */
    public static <T> T get(){
        //提供ThreadLocal的get方法获取值
        return (T) THREAD_LOCAL.get();
    }

    /**
     * 为THREAD_LOCAL存储键值对
     * @param value
     */
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }

    /**
     * 消除THREAD_LOCAL对象，防止内存泄漏
     */
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
