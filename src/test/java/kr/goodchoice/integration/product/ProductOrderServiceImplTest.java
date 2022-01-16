package kr.goodchoice.integration.product;

import kr.goodchoice.domain.response.product.ProductOrderResponse;
import kr.goodchoice.service.product.ProductOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class ProductOrderServiceImplTest {

    @Resource
    private ProductOrderService productOrderService;

    private static int count = 0;

    @Transactional
    @Test
    public void postOrder() throws Exception {
        // given
        Long productId = 782858L;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(50);
        // when
        for (int i = 0; i < 53; i++) { // 53번 비동기 실행
            service.execute(() -> {
                try {
                    ProductOrderResponse productOrderResponse = productOrderService.postOrder(782858L);
                    count += 1;
                    latch.countDown();
                } catch (Exception e) {
                    latch.countDown();
                }
            });
        }
        latch.await();
        // then
        assertEquals(count, 50); // 정확히 50번만 성공해야함.
        assertEquals(53 - count, 3);
    }
}