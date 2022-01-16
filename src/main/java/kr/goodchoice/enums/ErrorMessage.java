package kr.goodchoice.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {
    UNDEFINED_EXCEPTION(0, "정의되지 않은 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    REMAINS_EXCEPTION(1, "주문한 상품량이 재고량보다 큽니다.", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXIST_EXCEPTION(4, "존재하지 않는 상품번호입니다. 다시확인해주세요.", HttpStatus.BAD_REQUEST),
    VALIDATION_FAIL_EXCEPTION(5, "입력값검증에 실패했습니다.", HttpStatus.BAD_REQUEST),
    PRODUCT_ORDER_NOT_EXIST_EXCEPTION(6,"존재하지 않는 주문입니다.", HttpStatus.BAD_REQUEST);

    Integer code;
    String errorMessage;
    HttpStatus httpStatus;

    ErrorMessage(int code, String errorMessage, HttpStatus httpStatus) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}