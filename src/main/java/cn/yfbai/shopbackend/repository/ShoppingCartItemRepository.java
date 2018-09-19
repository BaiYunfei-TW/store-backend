package cn.yfbai.shopbackend.repository;

import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {
    List<ShoppingCartItem> findByUserId(Integer userId);
}
