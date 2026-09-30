package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderPayload {

    @JsonProperty("address")
    private Address address;

    @JsonProperty("orderedItems")
    private List<OrderedItem> orderedItems;

    @JsonProperty("totalPrice")
    private Double totalPrice;

    @JsonProperty("actualPrice")
    private Double actualPrice;

    @JsonProperty("discountPrice")
    private Double discountPrice;

    @JsonProperty("paymentMode")
    private String paymentMode;


    // ==================== Address ====================

    public static class Address {

        @JsonProperty("addressId")
        private Integer addressId;

        @JsonProperty("name")
        private String name;

        @JsonProperty("type")
        private String type;

        @JsonProperty("buildingInfo")
        private String buildingInfo;

        @JsonProperty("streetInfo")
        private String streetInfo;

        @JsonProperty("landmark")
        private String landmark;

        @JsonProperty("city")
        private String city;

        @JsonProperty("state")
        private String state;

        @JsonProperty("country")
        private String country;

        @JsonProperty("pincode")
        private String pincode;

        @JsonProperty("phone")
        private String phone;


        public Address() {
        }


        public Integer getAddressId() {
            return addressId;
        }

        public void setAddressId(Integer addressId) {
            this.addressId = addressId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBuildingInfo() {
            return buildingInfo;
        }

        public void setBuildingInfo(String buildingInfo) {
            this.buildingInfo = buildingInfo;
        }

        public String getStreetInfo() {
            return streetInfo;
        }

        public void setStreetInfo(String streetInfo) {
            this.streetInfo = streetInfo;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }


    // ==================== Ordered Item ====================

    public static class OrderedItem {

        @JsonProperty("itemId")
        private Integer itemId;

        @JsonProperty("productId")
        private Integer productId;

        @JsonProperty("quantity")
        private Integer quantity;

        @JsonProperty("productName")
        private String productName;

        @JsonProperty("imageLink")
        private String imageLink;

        @JsonProperty("price")
        private Double price;

        @JsonProperty("productLink")
        private String productLink;


        public OrderedItem() {
        }

        public Integer getitemId() {
            return itemId;
        }

        public void setitemId(Integer itemId) {
            this.itemId = itemId;
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getImageLink() {
            return imageLink;
        }

        public void setImageLink(String imageLink) {
            this.imageLink = imageLink;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getProductLink() {
            return productLink;
        }

        public void setProductLink(String productLink) {
            this.productLink = productLink;
        }
    }


    // ==================== Order Payload ====================

    public OrderPayload() {
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
}