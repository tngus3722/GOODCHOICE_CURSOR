package kr.goodchoice.exception.staticException;

import kr.goodchoice.enums.ErrorMessage;
import kr.goodchoice.exception.BaseException;

public class RequestInputException extends BaseException {

    public RequestInputException(String className, ErrorMessage errorMessage, Boolean isCritical) {
        super(className, errorMessage, isCritical);
    }

    public RequestInputException(ErrorMessage errorMessage, Boolean isCritical) {
        super(errorMessage, isCritical);
    }
}
