package cn.yfbai.shopbackend.helpers;

import cn.yfbai.shopbackend.entity.Order;
import cn.yfbai.shopbackend.entity.OrderDetail;
import cn.yfbai.shopbackend.entity.Product;
import cn.yfbai.shopbackend.entity.ShoppingCartItem;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.List;

public class SyntaxSugar {

    public static final int USER_ID = 1;
    public static final int ORDER_ID = 1;
    public static final int QUANTITY = 1;

    public static Product createProduct() {
        return new Product()
                .setId("020c823b-0753-4107-8216-13d38dde724c")
                .setName("雪碧")
                .setPrice(BigDecimal.valueOf(4.5))
                .setTotalAmount(10)
                .setUnit("瓶")
                .setImgUrl("/api/img/2");
    }

    public static List<Product> createProductList() {
        return Lists.newArrayList(
            new Product()
                .setId("7b2d3294-a251-4958-91d0-1160f1cee1e8")
                .setName("可乐")
                .setPrice(BigDecimal.valueOf(4.5))
                .setTotalAmount(10)
                .setUnit("瓶")
                .setImgUrl("/api/img/1"),
            new Product()
                .setId("020c823b-0753-4107-8216-13d38dde724c")
                .setName("雪碧")
                .setPrice(BigDecimal.valueOf(4.5))
                .setTotalAmount(10)
                .setUnit("瓶")
                .setImgUrl("/api/img/2"));
    }

    public static ShoppingCartItem createShoppingCartItem() {
        return new ShoppingCartItem()
                .setProduct(createProduct())
                .setUserId(USER_ID)
                .setQuantity(QUANTITY);
    }

    public static List<ShoppingCartItem> createShoppingCartItemList() {
        return Lists.newArrayList(new ShoppingCartItem()
                .setProduct(createProduct())
                .setUserId(USER_ID)
                .setQuantity(QUANTITY));
    }

    public static BigDecimal getTotalPrice(List<ShoppingCartItem> shoppingCartItems) {
        return shoppingCartItems.stream().map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce((acc, num) -> acc.add(num))
                .orElse(BigDecimal.ZERO);
    }

    public static Order createOrder() {
        return new Order()
                .setId(ORDER_ID)
                .setTotalPrice(getTotalPrice(createShoppingCartItemList()))
                .setUserId(USER_ID)
                .setOrderDetails(Lists.newArrayList(
                        new OrderDetail()
                                .setProduct(createProduct())
                                .setQuantity(QUANTITY)
                ));
    }

}
