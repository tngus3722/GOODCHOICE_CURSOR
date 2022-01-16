package kr.goodchoice.mapper;

import kr.goodchoice.domain.response.product.ProductOrderItemResponse;
import kr.goodchoice.entity.product.ProductOrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductOrderItemMapper extends ProductMapper {
    ProductOrderItemMapper INSTANCE = Mappers.getMapper(ProductOrderItemMapper.class);

    @Mapping(source = "productEntity", target = "productResponse")
    ProductOrderItemResponse toProductOrderItem(ProductOrderItemEntity productOrderItemEntity);
}
