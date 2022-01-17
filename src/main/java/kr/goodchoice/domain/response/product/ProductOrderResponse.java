package kr.goodchoice.domain.response.product;

import kr.goodchoice.domain.response.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class ProductOrderResponse extends BaseResponse {
    private Long orderId;
    private List<ProductOrderItemResponse> productOrderItemResponses;
    private Integer totalPrice;
    private Integer deliveryPrice;
}