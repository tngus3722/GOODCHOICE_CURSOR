package kr.goodchoice.service.product;

import kr.goodchoice.domain.response.BaseOffsetResponse;
import kr.goodchoice.domain.page.OffsetCriteria;
import kr.goodchoice.domain.response.product.ProductOrderResponse;

public interface ProductOrderService {

    public BaseOffsetResponse<ProductOrderResponse> getOrders(OffsetCriteria offsetCriteria);

    public ProductOrderResponse postOrder(Long productId);

    public void deleteOrder(Long orderId);
}
