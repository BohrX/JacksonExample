package cc.bohrx.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

public class CustomJsonMismatchedInputException extends MismatchedInputException {

    protected CustomJsonMismatchedInputException(JsonParser p, String msg) {
        super(p, msg);
    }

    protected CustomJsonMismatchedInputException(JsonParser p, String msg, JsonLocation loc) {
        super(p, msg, loc);
    }

    protected CustomJsonMismatchedInputException(JsonParser p, String msg, Class<?> targetType) {
        super(p, msg, targetType);
    }

    protected CustomJsonMismatchedInputException(JsonParser p, String msg, JavaType targetType) {
        super(p, msg, targetType);
    }

    public static CustomJsonMismatchedInputException from(JsonParser p, JavaType targetType, String msg) {
        return new CustomJsonMismatchedInputException(p, msg, targetType);
    }

    public static CustomJsonMismatchedInputException from(JsonParser p, Class<?> targetType, String msg) {
        return new CustomJsonMismatchedInputException(p, msg, targetType);
    }
}
