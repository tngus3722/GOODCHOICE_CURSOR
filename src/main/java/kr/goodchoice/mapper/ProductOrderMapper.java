package kr.goodchoice.mapper;

import kr.goodchoice.domain.response.product.ProductOrderResponse;
import kr.goodchoice.entity.product.ProductOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductOrderMapper extends ProductOrderItemMapper {
    ProductOrderMapper INSTANCE = Mappers.getMapper(ProductOrderMapper.class);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "productOrderItemEntities", target = "productOrderItemResponses")
    ProductOrderResponse toProductOrderResponse(ProductOrderEntity productOrderEntity);
}

