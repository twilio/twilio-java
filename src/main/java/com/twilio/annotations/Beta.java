package com.twilio.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Beta {
    String value() default "This class/method is under beta and is subjected to change. Use with caution.";
}