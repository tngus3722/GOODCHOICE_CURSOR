package kr.goodchoice.serviceImpl;

import kr.goodchoice.domain.response.BaseCursorResponse;
import kr.goodchoice.domain.page.CursorCriteria;
import kr.goodchoice.domain.response.product.ProductResponse;
import kr.goodchoice.entity.product.ProductEntity;
import kr.goodchoice.enums.ErrorMessage;
import kr.goodchoice.exception.staticException.RequestInputException;
import kr.goodchoice.mapper.ProductMapper;
import kr.goodchoice.repository.product.ProductRepository;
import kr.goodchoice.repository.product.support.ProductRepositorySupport;
import kr.goodchoice.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductRepositorySupport productRepositorySupport;
    @Value("${server.base.point}")
    private String basePoint;

    @Transactional(readOnly = true)
    @Override
    public BaseCursorResponse<ProductResponse> getProducts(CursorCriteria cursorCriteria) {
        List<ProductResponse> productResponses = productRepositorySupport.findByCursorCriteria(cursorCriteria)
                .stream().map(ProductMapper.INSTANCE::toProduct).collect(Collectors.toList());

        return new BaseCursorResponse<ProductResponse>(productResponses, cursorCriteria, basePoint + "product");
    }

    @Transactional(readOnly = true)
    @Override
    public ProductEntity getLockedProductEntityById(Long productId) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findByIdLock(productId);
        if (optionalProductEntity.isEmpty())
            throw new RequestInputException(ErrorMessage.PRODUCT_NOT_EXIST_EXCEPTION, false);
        return optionalProductEntity.get();
    }


}

