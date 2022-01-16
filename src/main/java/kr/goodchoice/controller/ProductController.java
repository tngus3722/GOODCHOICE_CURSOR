package kr.goodchoice.controller;

import kr.goodchoice.annotation.validation.ValidationGroup;
import kr.goodchoice.domain.page.CursorCriteria;
import kr.goodchoice.domain.page.OffsetCriteria;
import kr.goodchoice.domain.request.ProductRequest;
import kr.goodchoice.domain.response.BaseCursorResponse;
import kr.goodchoice.domain.response.BaseOffsetResponse;
import kr.goodchoice.domain.response.product.ProductOrderResponse;
import kr.goodchoice.domain.response.product.ProductResponse;
import kr.goodchoice.service.product.ProductOrderService;
import kr.goodchoice.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductOrderService productOrderService;

    @GetMapping("")
    public ResponseEntity<BaseCursorResponse> getProducts(@ModelAttribute CursorCriteria cursorCriteria) {
        return new ResponseEntity(productService.getProducts(cursorCriteria), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> putProducts(@PathVariable("productId") Long productId,
                                                       @RequestBody @Validated(ValidationGroup.Update.class) ProductRequest productRequest) {
        return new ResponseEntity(productService.putProduct(productId, productRequest), HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<BaseOffsetResponse> getOrders(@ModelAttribute OffsetCriteria offsetCriteria) {
        return new ResponseEntity(productOrderService.getOrders(offsetCriteria), HttpStatus.OK);
    }

    @PostMapping("/{productId}/order")
    public ResponseEntity<ProductOrderResponse> postOrder(@PathVariable("productId") Long productId) {
        return new ResponseEntity(productOrderService.postOrder(productId), HttpStatus.OK);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long productId) {
        productOrderService.deleteOrder(productId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
