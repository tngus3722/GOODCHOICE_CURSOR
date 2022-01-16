package kr.goodchoice.util.exception;

import kr.goodchoice.enums.ErrorMessage;
import kr.goodchoice.exception.BaseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Component
public class ExceptionParser {

    public BaseException BaseExceptionParser(Throwable e){
        BaseException baseException = null;

        // handled Exception
        if (e instanceof BaseException) {
            ((BaseException) e).setErrorTrace(e.getStackTrace()[0].toString());
            baseException = (BaseException) e;
        }

        // validation exception
        if (e instanceof MethodArgumentNotValidException) {
            baseException = new BaseException(e.getClass().getSimpleName(), ErrorMessage.VALIDATION_FAIL_EXCEPTION, true);
            List<ObjectError> messageList = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            String message = "";
            for (int i = 0; i < messageList.size(); i++) {
                String validationMessage = messageList.get(i).getDefaultMessage();
                message += "[" + validationMessage + "]";
            }
            baseException.setErrorMessage(message);
            baseException.setErrorTrace(e.getStackTrace()[0].toString());
        }

        // unhandled Exception
        if (baseException == null) {
            baseException = new BaseException(e.getClass().getSimpleName(), ErrorMessage.UNDEFINED_EXCEPTION, true);
            baseException.setErrorMessage(e.getMessage());
            baseException.setErrorTrace(e.getStackTrace()[0].toString());
        }

        if (baseException.getIsCritical()) ; // TODO notify some messanger

        return baseException;
    }
}
