package kr.goodchoice.config;

import kr.goodchoice.exception.BaseException;
import kr.goodchoice.util.exception.ExceptionParser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private final ExceptionParser exceptionParser;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<BaseException> defaultException(Throwable e) throws IOException {
        BaseException baseException = exceptionParser.BaseExceptionParser(e);
        return new ResponseEntity<>(baseException, baseException.getHttpStatus());
    }
}
