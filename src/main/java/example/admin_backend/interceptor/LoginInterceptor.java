package example.admin_backend.interceptor;

import com.alibaba.fastjson.JSONObject;
import example.admin_backend.utils.Jwt;
import example.admin_backend.utils.Result;
import example.admin_backend.utils.ThreadLocalUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 注入jwt的bean
     */
    @Autowired
    private Jwt jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取token
        String token = request.getHeader("token");
        //验证token
        try {
            //解析token
            Map<String, Object> claims = jwt.parseJwt(token);
            //将业务数据token存入ThreadLocal中，存入的数据为map集合
            ThreadLocalUtils.set(claims);
            //验证通过为true，放行
            return true;
        }catch (Exception e){
            Result<Object> errorInfo = Result.error("Authorization Failed.");
            //将result类型的error信息转换为json字符串
            String error = JSONObject.toJSONString(errorInfo);
            //信息返回给前端，设置http响应码为401
            response.setStatus(401);
            response.getWriter().write(error);
            //未通过为false
            return false;
        }
    }

    /**
     * 完成拦截请求后，移除ThreadLocal中的数据
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除ThreadLocal中的数据，防止内存泄露
        ThreadLocalUtils.remove();
    }
}