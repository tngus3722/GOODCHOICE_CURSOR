package kr.goodchoice.mapper;

import kr.goodchoice.domain.response.product.ProductResponse;
import kr.goodchoice.entity.product.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "id", target = "productId")
    ProductResponse toProduct(ProductEntity product);
}
