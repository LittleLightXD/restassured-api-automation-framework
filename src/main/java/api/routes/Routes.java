package api.routes;

public class Routes {

    private Routes() {
    }

    public static final String CREATE_ADMIN = "/admin";
    public static final String GET_ADMIN = CREATE_ADMIN + "/{adminId}";
    public static final String UPDATE_ADMIN = CREATE_ADMIN + "/{adminId}";


    public static final String CREATE_MERCHANT = "/merchants";
    public static final String GET_MERCHANT = CREATE_MERCHANT + "/{merchantId}";
    public static final String UPDATE_MERCHANT = CREATE_MERCHANT + "/{merchantId}";


    public static final String UPDATE_MERCHANT_STATUS = UPDATE_MERCHANT + "/status";
    public static final String GET_MERCHANT_ZONEID = CREATE_MERCHANT + "/status/zoneId";


    public static final String PRODUCT = "/products";
    public static final String SINGLE_PRODUCT = PRODUCT + "/{productId}";
    public static final String MERCHANT_PRODUCT = PRODUCT + "/merchant/{merchantId}";
    public static final String ALL_PRODUCT = PRODUCT + "/alpha";


    public static final String POST_SHOPPER = "/shoppers";
    public static final String GET_SHOPPER = POST_SHOPPER + "/{shopperId}";
    public static final String UPDATE_SHOPPER = POST_SHOPPER + "/{shopperId}";


    public static final String USERS = "/users";
    public static final String LOGIN = USERS + "/login";
    public static final String FORGOT_PASSWORD = USERS + "/forgot-password";
    public static final String RESET_PASSWORD = USERS + "/reset-password";
    public static final String VERIFY_ACCOUNT = USERS + "/verify-account";


    public static final String POST_SHOPPER_ADDRESS = GET_SHOPPER + "/address";
    public static final String GET_SHOPPER_ADDRESS = POST_SHOPPER_ADDRESS + "/{addressId}";
    public static final String GET_ALL_ADDRESSES = "/shoppers/{shopperId}/address";


    public static final String POST_SHOPPER_BANK_ACCOUNT = "/bankaccounts";
    public static final String LOGIN_SHOPPER_BANK_ACCOUNT = POST_SHOPPER_BANK_ACCOUNT + "/login";


    public static final String POST_SHOPPER_BANK_CARD = "/cards";
    public static final String SHOPPER_BANK_CARDS_TRANSACTION = POST_SHOPPER_BANK_CARD + "/transaction";
    public static final String VERIFY_SHOPPER_BANK_CARD = POST_SHOPPER_BANK_CARD + "/verify";


    public static final String POST_SHOPPER_PROFILE_CARD = "/cards";
    public static final String GET_SHOPPER_PROFILE_CARD = POST_SHOPPER_PROFILE_CARD + "/{shopperId}";
    public static final String DELETE_SHOPPER_PROFILE_CARD = POST_SHOPPER_PROFILE_CARD + "/{cardId}";
    public static final String GET_ALL_PROFILE_CARDS = "/shoppers/cards";


    public static final String POST_SHOPPER_WISHLIST = GET_SHOPPER + "/wishlist";
    public static final String GET_SHOPPER_WISHLIST = GET_SHOPPER + "/wishlist";
    public static final String DELETE_SHOPPER_WISHLIST = POST_SHOPPER_WISHLIST + "/{productId}";


    public static final String POST_SHOPPER_CART = GET_SHOPPER + "/carts";
    public static final String GET_SHOPPER_CART = GET_SHOPPER + "/carts";
    public static final String DELETE_SHOPPER_CART = GET_SHOPPER_CART + "/{productId}";
    public static final String UPDATE_SHOPPER_CART = GET_SHOPPER_CART + "/{itemId}";


    public static final String POST_ORDER = GET_SHOPPER + "/orders";
    public static final String GET_ORDER = GET_SHOPPER + "/orders";
    public static final String UPDATE_ORDER = GET_ORDER + "/{orderId}";
    public static final String GET_ORDER_INVOICE = UPDATE_ORDER + "/invoice";


    public static final String POST_REVIEW = "/reviews";
    public static final String GET_REVIEW = POST_REVIEW + "/{productId}";
    public static final String UPDATE_REVIEW = POST_REVIEW + "/{reviewId}";
    public static final String DELETE_REVIEW = POST_REVIEW + "/{reviewId}";
}
