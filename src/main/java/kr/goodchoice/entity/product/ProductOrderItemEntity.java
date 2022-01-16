package kr.goodchoice.entity.product;

import kr.goodchoice.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE product_order_item SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
@Table(name = "product_order_item")
public class ProductOrderItemEntity extends BaseEntity {
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity productEntity;
    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOrderEntity productOrderEntity;
    @Basic
    @Column(name = "order_count")
    private Integer orderCount;
    @Basic
    @Column(name = "order_price")
    private Integer orderPrice;

    public ProductOrderItemEntity(ProductEntity productEntity, ProductOrderEntity productOrderEntity) {
        this.orderCount = 1;
        this.productEntity = productEntity;
        this.productOrderEntity = productOrderEntity;
        productOrderEntity.getProductOrderItemEntities().add(this);
        this.orderPrice = (this.getOrderCount() * productEntity.getProductPrice());
    }
}
