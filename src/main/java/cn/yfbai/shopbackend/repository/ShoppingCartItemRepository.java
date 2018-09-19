package cn.yfbai.shopbackend.repository;

import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {
    List<ShoppingCartItem> findByUserId(Integer userId);

    Optional<ShoppingCartItem> findByProductIdAndUserId(String productId, int userId);
}
