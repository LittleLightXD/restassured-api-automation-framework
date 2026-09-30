package api.testcases;

import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.CartOrderEndpoints;
import api.payload.CartPayload;
import api.payload.OrderPayload;
import api.utils.TestData;
import io.restassured.response.Response;

public class CartOrderTestcases {
    

    @Test(priority = 1, description = "Add product to shopper cart")
    public void addToCartTest() {

            String productId = TestData.productIds.get("product10");

        CartPayload  payload = new CartPayload();
        payload.setProductId(Integer.parseInt(productId));
        payload.setQuantity(2);

        Response response = CartOrderEndpoints.addToCart(TestData.shopperId, payload);
        TestData.itemId = response.jsonPath().getString("data.itemId");

        Assert.assertEquals(response.getStatusCode(), 201, "Product should be added to cart successfully");
    }

    @Test(priority = 2, description = "Create shopper order")
    public void createOrderTest() {

        String productId = TestData.productIds.get("product10");
        String addressId = TestData.addressIds.get("432101");

        OrderPayload payload = new OrderPayload();

        OrderPayload.Address address = new OrderPayload.Address();
        address.setAddressId(Integer.parseInt(addressId));
        address.setName("GB");
        address.setType("Home");
        address.setBuildingInfo("iuytrew");
        address.setStreetInfo("Bangalores");
        address.setLandmark("lkjhgfdsa");
        address.setCity("Diamond Harbour");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setPincode("432101");
        address.setPhone("8351931127");

        OrderPayload.OrderedItem item = new OrderPayload.OrderedItem();
        item.setitemId(Integer.parseInt(TestData.itemId));
        item.setProductId(Integer.parseInt(productId));
        item.setQuantity(4);
        item.setProductName("Razer Viper Ultimate Wireless Mouse");
        item.setImageLink("https://assets2.razerzone.com/images/pnx.assets/91d6b6a2b9ebc7c8f4f5d38e1c7ac21a/razer-viper-ultimate-gallery-1.jpg");
        item.setPrice(0.0);
        item.setProductLink("/products/11180");

        payload.setAddress(address);
        payload.setOrderedItems(Collections.singletonList(item));
        payload.setTotalPrice(15311.88);
        payload.setActualPrice(17999.88);
        payload.setDiscountPrice(2688.0);
        payload.setPaymentMode("COD");

        Response response = CartOrderEndpoints.createOrder(TestData.shopperId, payload);
        TestData.orderId = response.jsonPath().getString("data.orderId");

        Assert.assertEquals(response.getStatusCode(), 201, "Shopper order should be created successfully");
    }

    @Test(priority = 3, description = "Update shopper order status")
    public void updateOrderStatusTest() {

        String shopperId = TestData.shopperId;
        String orderId = TestData.orderId;

        Response response = CartOrderEndpoints.updateOrderStatus(shopperId,orderId,"DELIVERED");

        Assert.assertEquals(response.getStatusCode(),200,"Order status should be updated");
    }
}