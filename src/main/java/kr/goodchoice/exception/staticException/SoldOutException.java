package kr.goodchoice.exception.staticException;

import kr.goodchoice.enums.ErrorMessage;
import kr.goodchoice.exception.BaseException;

public class SoldOutException extends BaseException {

    public SoldOutException(String className, ErrorMessage errorMessage, Boolean isCritical) {
        super(className, errorMessage, isCritical);
    }

    public SoldOutException(ErrorMessage errorMessage, Boolean isCritical) {
        super(errorMessage, isCritical);
    }
}
