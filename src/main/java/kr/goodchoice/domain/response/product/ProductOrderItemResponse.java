package kr.goodchoice.domain.response.product;

import kr.goodchoice.domain.response.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ProductOrderItemResponse extends BaseResponse {
    private ProductResponse productResponse;
    private Integer orderCount;
    private Integer orderPrice;
}
