package api.testcases;

import api.endpoints.OrderEndpoints;
import api.payload.OrderPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTestcases {



    @Test(priority = 1, description = "Create order with valid data")
    public void createOrderWithValidDataTest() {

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setDiscountAmount(100.0);
        payload.setTaxAmount(80.0);
        payload.setShippingCost(50.0);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        Response response = OrderEndpoints.createOrder(shopperId, payload);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("orderId"));
    }

    @Test(priority = 2, description = "Get all orders for shopper")
    public void getShopperOrdersTest() {

        String shopperId = "shopper-123456";
        Response response = OrderEndpoints.getShopperOrders(shopperId);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, description = "Get order by ID")
    public void getOrderByIdTest() {

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, payload);
        Response response = OrderEndpoints.getOrderById(shopperId, orderId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("orderId"), orderId);

    }

    @Test(priority = 4, description = "Update order details")
    public void updateOrderTest() {

        String shopperId = "shopper-123456";
        OrderPayload createPayload = new OrderPayload();
        createPayload.setShopperId(shopperId);
        createPayload.setTotalAmount(999.99);
        createPayload.setPaymentMethod("CREDIT_CARD");
        createPayload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, createPayload);

        OrderPayload updatePayload = new OrderPayload();
        updatePayload.setDeliveryAddress("Updated Address");

        Response response = OrderEndpoints.updateOrder(shopperId, orderId, updatePayload);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 5, description = "Get order by status")
    public void getOrdersByStatusTest() {

        String shopperId = "shopper-123456";
        Response response = OrderEndpoints.getOrdersByStatus(shopperId, "PENDING");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 6, description = "Get order by payment status")
    public void getOrdersByPaymentStatusTest() {

        String shopperId = "shopper-123456";
        Response response = OrderEndpoints.getOrdersByPaymentStatus(shopperId, "PAID");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 7, description = "Get order invoice")
    public void getOrderInvoiceTest() {

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, payload);
        Response response = OrderEndpoints.getOrderInvoice(shopperId, orderId);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 8, description = "Track order")
    public void trackOrderTest() {

        String shopperId = "shopper-123456";
        Response response = OrderEndpoints.trackOrder(shopperId, "TRACK123456");

        Assert.assertEquals(response.getStatusCode(), 200);

    }



    @Test(priority = 10, dataProvider = "validOrderData",
            description = "Create order with data provider")
    public void createOrderDataDrivenTest(String shopperId, Double totalAmount, String paymentMethod) {

        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(totalAmount);
        payload.setPaymentMethod(paymentMethod);
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        Response response = OrderEndpoints.createOrder(shopperId, payload);

        Assert.assertEquals(response.getStatusCode(), 201);

    }



    @Test(priority = 20, description = "Create order with missing shopper ID")
    public void createOrderWithMissingShopperIdTest() {

        String shopperId = "";
        OrderPayload payload = new OrderPayload();
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");

        Response response = OrderEndpoints.createOrder(shopperId, payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 21, description = "Create order with invalid payment method")
    public void createOrderWithInvalidPaymentMethodTest() {

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("INVALID_PAYMENT");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        Response response = OrderEndpoints.createOrder(shopperId, payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 22, description = "Get order with invalid order ID")
    public void getOrderWithInvalidIdTest() {

        String shopperId = "shopper-123456";
        Response response = OrderEndpoints.getOrderById(shopperId, "invalid-order-id");

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(priority = 23, description = "Cancel order")
    public void cancelOrderTest() {

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, payload);
        Response response = OrderEndpoints.cancelOrder(shopperId, orderId);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 24, description = "Create order with negative amount")
    public void createOrderWithNegativeAmountTest() {

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(-999.99);
        payload.setPaymentMethod("CREDIT_CARD");

        Response response = OrderEndpoints.createOrder(shopperId, payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 25, description = "Delete order")
    public void deleteOrderTest() {

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, payload);
        Response response = OrderEndpoints.deleteOrder(shopperId, orderId);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 26, description = "Generate order invoice PDF")
    public void generateInvoicePDFTest() {

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, payload);
        Response response = OrderEndpoints.generateInvoicePDF(shopperId, orderId);

        Assert.assertEquals(response.getStatusCode(), 200);

    }
}

