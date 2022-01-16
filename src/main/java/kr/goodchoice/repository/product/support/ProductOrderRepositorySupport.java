package kr.goodchoice.repository.product.support;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.goodchoice.domain.page.BaseCriteria;
import kr.goodchoice.domain.page.OffsetCriteria;
import kr.goodchoice.entity.product.ProductOrderEntity;
import kr.goodchoice.entity.product.QProductOrderEntity;
import kr.goodchoice.enums.SortBy;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductOrderRepositorySupport extends QuerydslRepositorySupport {
    private JPAQueryFactory jpaQueryFactory;

    public ProductOrderRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(ProductOrderEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<ProductOrderEntity> findByOffsetCriteria(OffsetCriteria offsetCriteria) {
        QProductOrderEntity qProductOrderEntity = QProductOrderEntity.productOrderEntity;

        OrderSpecifier orderSpecifier = this.getOrderSpecifier(qProductOrderEntity, offsetCriteria);

        return jpaQueryFactory.selectFrom(qProductOrderEntity)
                .orderBy(orderSpecifier)
                .orderBy(qProductOrderEntity.id.desc())
                .limit(offsetCriteria.getLimit() + 1)
                .offset(offsetCriteria.getOffset())
                .fetch();
    }

    private OrderSpecifier getOrderSpecifier(QProductOrderEntity qProductOrderEntity, BaseCriteria offsetCriteria) {
        OrderSpecifier orderSpecifier;
        String sort = offsetCriteria.getSortBy();
        String order = offsetCriteria.getOrder();

        if (StringUtils.equals(sort, SortBy.id.toString()) && StringUtils.equals(order, "asc"))
            orderSpecifier = qProductOrderEntity.id.asc().nullsLast();
        else
            orderSpecifier = qProductOrderEntity.id.desc(); // default

        return orderSpecifier;
    }
}
