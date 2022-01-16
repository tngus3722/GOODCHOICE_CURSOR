package kr.goodchoice.domain.response.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class ProductOrderResponse {
    private Long orderId;
    private List<ProductOrderItemResponse> productOrderItemResponses;
    private Integer totalPrice;
    private Integer deliveryPrice;

}