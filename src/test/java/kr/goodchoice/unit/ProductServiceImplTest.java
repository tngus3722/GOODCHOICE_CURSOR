package kr.goodchoice.unit;

import kr.goodchoice.domain.page.CursorCriteria;
import kr.goodchoice.domain.response.BaseCursorResponse;
import kr.goodchoice.domain.response.product.ProductResponse;
import kr.goodchoice.entity.product.ProductEntity;
import kr.goodchoice.repository.product.support.ProductRepositorySupport;
import kr.goodchoice.serviceImpl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepositorySupport productRepositorySupport;

    @Test
    public void getProduct() {
        // given for target
        CursorCriteria cursorCriteria = new CursorCriteria();
        //given for mock
        List<ProductEntity> productEntities = new ArrayList<>();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(782858L);
        productEntity.setProductName("폴로 랄프로렌 남성 수영복반바지 컬렉션 (51color)");
        productEntity.setProductPrice(39500);
        productEntity.setProductRemains(50);
        productEntities.add(productEntity);
        //mocking
        given(productRepositorySupport.findByCursorCriteria(cursorCriteria)).willReturn(productEntities);
        //when
        BaseCursorResponse<ProductResponse> baseCursorResponse = productService.getProducts(cursorCriteria);
        //then
        assertEquals(baseCursorResponse.getResponse().get(0).getProductId().longValue(), 782858);
        assertEquals(baseCursorResponse.getNext(), null);
    }
}
