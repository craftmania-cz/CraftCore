package cz.wake.craftcore.command;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@SuppressWarnings("unused")
@Retention(RUNTIME)
@Target(TYPE)
public @interface CommandClass {

    String name() default ""; // Default Command name. multiple names allowed.

    String permission() default ""; // Default permission. multiple permissions allowed.

    String permissionError() default "default"; // Default Insufficient Permission Error.

    String argumentsError() default "default"; // Default Invalid arguments Error.

    String playerError() default "default";  // Default Sender must be a player Error.

}
