package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileCardPayload {

    @JsonProperty("cardId")
    private String cardId;

    @JsonProperty("cardNumber")
    private String cardNumber;

    @JsonProperty("cardholderName")
    private String cardholderName;

    @JsonProperty("expiryMonth")
    private String expiryMonth;

    @JsonProperty("expiryYear")
    private String expiryYear;

    @JsonProperty("cvv")
    private String cvv;

    @JsonProperty("cardType")
    private String cardType;

    @JsonProperty("issuerBank")
    private String issuerBank;

    @JsonProperty("isDefault")
    private Boolean isDefault;

    @JsonProperty("isSaved")
    private Boolean isSaved;

    @JsonProperty("createdDateTime")
    private String createdDateTime;

    @JsonProperty("updatedDateTime")
    private String updatedDateTime;


    public ProfileCardPayload() {}

    public ProfileCardPayload(String cardNumber, String cardholderName, String expiryMonth, String expiryYear, String cvv, String cardType) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
        this.cardType = cardType;
    }


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getIssuerBank() {
        return issuerBank;
    }

    public void setIssuerBank(String issuerBank) {
        this.issuerBank = issuerBank;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Boolean isSaved) {
        this.isSaved = isSaved;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}

