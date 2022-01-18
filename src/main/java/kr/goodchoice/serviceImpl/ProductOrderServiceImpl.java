package kr.goodchoice.serviceImpl;

import kr.goodchoice.domain.response.BaseOffsetResponse;
import kr.goodchoice.domain.page.OffsetCriteria;
import kr.goodchoice.domain.response.product.ProductOrderResponse;
import kr.goodchoice.entity.product.ProductEntity;
import kr.goodchoice.entity.product.ProductOrderEntity;
import kr.goodchoice.entity.product.ProductOrderItemEntity;
import kr.goodchoice.enums.ErrorMessage;
import kr.goodchoice.exception.staticException.RequestInputException;
import kr.goodchoice.exception.staticException.SoldOutException;
import kr.goodchoice.mapper.ProductOrderMapper;
import kr.goodchoice.repository.product.ProductOrderItemRepository;
import kr.goodchoice.repository.product.ProductOrderRepository;
import kr.goodchoice.repository.product.ProductRepository;
import kr.goodchoice.repository.product.support.ProductOrderRepositorySupport;
import kr.goodchoice.service.product.ProductOrderService;
import kr.goodchoice.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    private final ProductService productService;
    private final ProductOrderRepositorySupport productOrderRepositorySupport;
    private final ProductRepository productRepository;
    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderItemRepository productOrderItemRepository;
    @Value("${server.base.point}")
    private String basePoint;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public BaseOffsetResponse<ProductOrderResponse> getOrders(OffsetCriteria offsetCriteria) {
        List<ProductOrderResponse> productOrderResponses = productOrderRepositorySupport
                .findByOffsetCriteria(offsetCriteria)
                .stream().map(ProductOrderMapper.INSTANCE::toProductOrderResponse)
                .collect(Collectors.toList());

        return new BaseOffsetResponse<ProductOrderResponse>(productOrderResponses, offsetCriteria, basePoint + "product/order");
    }

    // 단일 주문
    @Transactional
    @Override
    public ProductOrderResponse postOrder(Long productId) {
        ProductEntity productEntity = productService.getLockedProductEntityById(productId);
        productEntity.diffRemains(1);
        if (productEntity.isSoldOut())
            throw new SoldOutException(ErrorMessage.REMAINS_EXCEPTION, false);

        ProductOrderEntity productOrderEntity = new ProductOrderEntity(productEntity.getProductPrice());
        ProductOrderItemEntity productOrderItem = new ProductOrderItemEntity(productEntity, productOrderEntity);

        productRepository.save(productEntity);
        productOrderRepository.save(productOrderEntity);
        productOrderItemRepository.save(productOrderItem);

        entityManager.flush();
        entityManager.clear();

        return ProductOrderMapper.INSTANCE.toProductOrderResponse(this.getProductOrderEntity(productOrderEntity.getId()));
    }

    @Transactional
    @Override
    public void deleteOrder(Long orderId) {
        ProductOrderEntity productOrderEntity = this.getProductOrderEntity(orderId);

        for (ProductOrderItemEntity productOrderItemEntity : productOrderEntity.getProductOrderItemEntities()) {
            ProductEntity product = productService.getLockedProductEntityById(productOrderItemEntity.getProductEntity().getId());
            product.plusRemains(productOrderItemEntity.getOrderCount());
        }
        // 사실 이건 삭제가아니라 취소가 되어야하는게 일반적으로 올바름
        productOrderRepository.delete(productOrderEntity); // soft cascade delete
    }

    private ProductOrderEntity getProductOrderEntity(Long orderId) {
        Optional<ProductOrderEntity> optionalProductOrderEntity = productOrderRepository.findById(orderId);
        if (optionalProductOrderEntity.isEmpty())
            throw new RequestInputException(ErrorMessage.PRODUCT_ORDER_NOT_EXIST_EXCEPTION, false);
        return optionalProductOrderEntity.get();
    }
}
