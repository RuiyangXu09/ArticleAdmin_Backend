package example.admin_backend.utils;

import java.util.Map;

/**
 * 获取当前线程中userId的工具类
 */
public class GetUserInfoUtils {
    /**
     * 从线程中获取user id
     * @return 返回一个Integer类型的变量
     */
    public static Integer getCurrentThreadLocalUserId(){
        //从ThreadLocal中获取解析自token的数据，封装给map以键值对形式存储
        Map<String, Object> map = ThreadLocalUtils.get();
        //返回一个integer类型的变量，保存键为id的数据（也就是登录的用户的id）
        return (Integer) map.get("id");
    }
}
