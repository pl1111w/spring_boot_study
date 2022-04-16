package pl1111w.exceptionhandling.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/13 15:54
 */
@ResponseStatus(code = HttpStatus.PAYLOAD_TOO_LARGE,reason ="this Customization exceptions")
public class ExceptionResponseStatus extends RuntimeException {

    public ExceptionResponseStatus(String message) {
        super(message);
    }
}
