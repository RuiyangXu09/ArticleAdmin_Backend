package example.admin_backend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Json格式的返回信息，前后端统一的响应结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    /**
     * 使用泛型接收响应数据data
     */
    private T data;

    public static <T> Result<T> success(T data){
        return new Result<>(0, "Success.", data);
    }

    /**
     *  重载方法，无data传入参数
     */
    public static <T> Result<T> success(){
        return new Result<>(0, "Success.", null);
    }

    /**
     * 失败响应，只传入msg作为参数，只返回msg和code状态码
     */
    public static <T> Result<T> error(String msg){
        return new Result<>(1, msg, null);
    }
}

