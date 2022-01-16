package kr.goodchoice.repository.product;

import kr.goodchoice.entity.product.ProductOrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderItemRepository extends JpaRepository<ProductOrderItemEntity, Long> {
}

