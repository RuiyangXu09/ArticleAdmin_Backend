package example.admin_backend.validation;

import example.admin_backend.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 校验实现类
 * ConstraintValidator<State, String>该方法传递的参数，第一个代表给哪个注解提供校验规则，第二个为需要校验的数据类型
 */
public class StateValidation implements ConstraintValidator<State, String> {
    /**
     *
     * @param s 将来要校验的数据
     * @param constraintValidatorContext
     * @return 如果返回false，则校验不通过，true为通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if (s == null){
            return false;
        }
        //如果s等于这两个值，通过校验
        if (s.equals("Published") || s.equals("Unpublished")){
            return true;
        } else {
            return false;
        }
    }
}
