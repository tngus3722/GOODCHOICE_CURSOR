package kr.goodchoice.service.product;

import kr.goodchoice.domain.page.CursorCriteria;
import kr.goodchoice.domain.request.ProductRequest;
import kr.goodchoice.domain.response.BaseCursorResponse;
import kr.goodchoice.domain.response.product.ProductResponse;
import kr.goodchoice.entity.product.ProductEntity;

public interface ProductService {

    BaseCursorResponse<ProductResponse> getProducts(CursorCriteria cursorCriteria);

    ProductEntity getLockedProductEntityById(Long productId);

    ProductResponse putProduct(Long productId, ProductRequest productRequest);

    ProductEntity getProductEntityById(Long productId);
}
