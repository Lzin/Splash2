package app.com.mediaplayer;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//使用元注解
@Retention(RUNTIME)
@Target(TYPE)
public @interface ViewInject {
        int mainlayoutid() default -1;
}
