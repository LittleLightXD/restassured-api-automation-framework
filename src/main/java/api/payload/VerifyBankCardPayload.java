package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifyBankCardPayload {

    @JsonProperty("merchantId")
    private String merchantId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("approvalStatus")
    private String approvalStatus;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("rejectionReason")
    private String rejectionReason;

    @JsonProperty("blockReason")
    private String blockReason;

    @JsonProperty("comments")
    private String comments;

    @JsonProperty("approvedBy")
    private String approvedBy;

    @JsonProperty("approvalDate")
    private String approvalDate;

    @JsonProperty("isActive")
    private Boolean isActive;


    public VerifyBankCardPayload() {}

    public VerifyBankCardPayload(String merchantId, String approvalStatus, String reason) {
        this.merchantId = merchantId;
        this.approvalStatus = approvalStatus;
        this.reason = reason;
    }


    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}

