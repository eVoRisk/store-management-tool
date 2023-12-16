package com.store.management.tool.repository;

import com.store.management.tool.model.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer> {
    List<ProductComment> findAllByProductId(Integer productId);

    List<ProductComment> findAllByCustomerId(Integer customerId);
}
