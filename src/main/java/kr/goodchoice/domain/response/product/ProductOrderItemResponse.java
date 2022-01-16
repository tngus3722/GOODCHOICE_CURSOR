package kr.goodchoice.domain.response.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ProductOrderItemResponse {
    private ProductResponse productResponse;
    private Integer orderCount;
    private Integer orderPrice;
}
