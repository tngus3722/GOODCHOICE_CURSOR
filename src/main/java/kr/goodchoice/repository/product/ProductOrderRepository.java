package kr.goodchoice.repository.product;

import kr.goodchoice.entity.product.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrderEntity, Long> {
}
