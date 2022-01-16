package kr.goodchoice.repository.product;

import kr.goodchoice.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10000")})
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT p FROM ProductEntity p where p.id = ?1")
    Optional<ProductEntity> findByIdLock(Long id);
}
