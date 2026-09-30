package api.utils;

import java.util.HashMap;
import java.util.Map;

public class TestData {

    // Admin
    public static String adminId;
    public static String adminToken;
    public static String adminEmail;
    public static String adminPassword;

    // Merchant
    public static String merchantEmail;
    public static String merchantPassword;
    public static String merchantToken;
    public static String zoneId;
    public static String merchantId;
    public static String merchantReject;

    // Shopper
    public static String shopperEmail;
    public static String shopperPassword;
    public static String shopperName;
    public static String shopperId;
    public static String shopperToken;
    public static String resetToken;

    // Shopper Address
    public static Map<String, String> addressIds = new HashMap<>();

    // Product
    public static Map<String, String> productIds = new HashMap<>();

    // Order
    public static String itemId;
    public static String orderId;

    // Review
    public static String reviewId;
}