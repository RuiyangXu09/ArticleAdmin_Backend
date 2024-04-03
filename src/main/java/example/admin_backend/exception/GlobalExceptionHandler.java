package example.admin_backend.exception;

import example.admin_backend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获所有异常，以json格式抛出到前端返回，Exception.class捕获所有异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e){
        log.error(e.getMessage(), e);
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "Server Error.");
    }
}
