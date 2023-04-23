package FitMate.FitMateBackend.chanhaleWorking.argumentresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 파라미터에 들어가는 에노테이션
@Retention(RetentionPolicy.RUNTIME) // 동작할때까지 에노테이션으로 남아있어야함
public @interface Login {

}
