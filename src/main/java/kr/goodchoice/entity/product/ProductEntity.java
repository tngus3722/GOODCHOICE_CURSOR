package kr.goodchoice.entity.product;

import kr.goodchoice.domain.request.ProductRequest;
import kr.goodchoice.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE product SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
@Table(name = "product")
public class ProductEntity extends BaseEntity {
    @Basic
    @Column(name = "product_name")
    private String productName;
    @Basic
    @Column(name = "product_price")
    private Integer productPrice;
    @Basic
    @Column(name = "product_remains")
    private Integer productRemains;

    public void update(ProductRequest productRequest) {
        if (!StringUtils.isEmpty(productRequest.getProductName()))
            this.productName = productRequest.getProductName();
        if (productRequest.getProductPrice() != null)
            this.productPrice = productRequest.getProductPrice();
    }

    public void diffRemains(Integer count) {
        this.productRemains -= count;
    }

    public void plusRemains(Integer count) {
        this.productRemains += count;
    }

    public Boolean isSoldOut() {
        if (this.productRemains >= 0)
            return false;
        return true;
    }
}
