package kr.goodchoice.domain.request;

import kr.goodchoice.annotation.validation.ValidationGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class ProductRequest {
    @Size(min = 1, groups = ValidationGroup.Update.class, message = "상품명은 최소 한글자 이상입니다.")
    private String productName;
    @Min(value = 100, groups = ValidationGroup.Update.class, message = "상품명은 최소 백원 이상입니다.")
    @Max(value = 2000000000, groups = ValidationGroup.Update.class, message = "상품명은 최대 20억입니다.")
    private Integer productPrice;
}
