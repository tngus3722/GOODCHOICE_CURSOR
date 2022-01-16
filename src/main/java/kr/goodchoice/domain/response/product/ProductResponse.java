package kr.goodchoice.domain.response.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kr.goodchoice.domain.page.CustomPageable;
import kr.goodchoice.domain.page.CursorCriteria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Setter
@Getter
public class ProductResponse implements CustomPageable {
    private Long productId;
    private String productName;
    private Integer productPrice;
    private Integer productRemains;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @JsonIgnore
    private String cursor;

    @Override
    public String getCursor(CursorCriteria cursorCriteria) {
        switch (cursorCriteria.getSortBy()) {
            case "productPrice":
                this.cursor = this.productPrice + " " + this.productId;
                break;
            default:
                this.cursor = String.valueOf(this.productId);
                break;
        }
        return Base64.encodeBase64String(this.cursor.getBytes(StandardCharsets.UTF_8));
    }
}
