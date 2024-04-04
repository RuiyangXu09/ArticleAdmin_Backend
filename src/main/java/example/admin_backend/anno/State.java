package example.admin_backend.anno;

import example.admin_backend.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 自定义一个state的校验规则，state只能是Published或Unpublished
 */
//元注解
@Documented
//可用在哪些方法，变量，类，属性，参数上面
@Target({ElementType.FIELD})
//元注解
@Retention(RetentionPolicy.RUNTIME)
//指定提供校验规则的类
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    //提供校验失败后的提示信息
    String message() default "State can only set by Published or Unpublished.";

    //指定分组
    Class<?>[] groups() default {};

    //负载，获取到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
