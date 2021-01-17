package com.iioannou.cameletlprocessor.domain;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
@CsvRecord(separator = ";", skipFirstLine = true, generateHeaderColumns = true)
public class Orders {

    @Id
    @Column(name = "ID", nullable = false, length = 15)
    @DataField(pos = 1, columnName = "ID")
    private Long id;

    @DataField(pos = 2, columnName = "WAREHOUSE_ID")
    @Column(name = "WAREHOUSE_ID", length = 10)
    private Long warehouseId;

    @DataField(pos = 3, columnName = "WAREHOUSE_REFERENCE")
    @Column(name = "WAREHOUSE_REFERENCE", columnDefinition = "NVARCHAR2 (32)")
    private String warehouseRef;

    @DataField(pos = 4, columnName = "VACCINATION_CENTER_ID")
    @Column(name = "VACCINATION_CENTER_ID", length = 10)
    private Long vacCenterId;

    @DataField(pos = 7, pattern = "dd/MM/yyyy HH:mm", columnName = "EXIT_DEEP_FREEZE_DATETIME" )
    @Column(name = "EXIT_DEEP_FREEZE_DATETIME", columnDefinition = "DATE")
    private Date exitedDeepFreezeTime;

    @DataField(pos = 5, pattern = "dd/MM/yyyy HH:mm", columnName = "REQUESTED_DELIVERY_DATETIME")
    @Column(name = "REQUESTED_DELIVERY_DATETIME", columnDefinition = "DATE")
    private Date reqDeliveryDateTime;

    @DataField(pos = 6,columnName = "VACCINE_ID")
    @Column(name = "VACCINE_ID", length = 10)
    private Long vacId;

    @DataField(pos = 8, columnName = "QUANTITY_IN_BATCHES")
    @Column(name = "QUANTITY_IN_BATCHES", length = 10)
    private Long quantity;

    @DataField(pos = 9, columnName = "TYPE")
    @Column(name = "TYPE", length = 10)
    private Long type;

    @DataField(pos = 10, columnName = "STATUS")
    @Column(name = "STATUS", length = 10)
    private Long status;

    @DataField(pos = 11, pattern = "dd/MM/yyyy HH:mm", columnName = "CREATED_DATETIME")
    @Column(name = "CREATED_DATETIME", columnDefinition = "DATE")
    private Date createdDateTime;

    @DataField(pos = 12, pattern = "dd/MM/yyyy HH:mm", columnName = "LAST_UPDATED_DATETIME")
    @Column(name = "LAST_UPDATED_DATETIME", columnDefinition = "DATE")
    private Date updatedDateTime;

    @DataField(pos = 13, columnName = "RECORD_CREATOR")
    @Column(name = "RECORD_CREATOR", length = 10)
    private Long creator;

    @DataField(pos = 14, columnName = "APPOINTMENTS_ERROR_CODE")
    @Column(name = "APPOINTMENTS_ERROR_CODE", length = 10)
    private Long appointErrorCode;

    @DataField(pos = 15, columnName = "APPOINTMENTS_ERROR_MESSAGE")
    @Column(name = "APPOINTMENTS_ERROR_MESSAGE", columnDefinition = "NVARCHAR2 (500)")
    private String appointErrorMsg;

    @DataField(pos = 16, columnName = "WAREHOUSE_ERROR_CODE")
    @Column(name = "WAREHOUSE_ERROR_CODE", length = 10)
    private Long warehouseErrorCode;

    @DataField(pos = 17, columnName = "WAREHOUSE_ERROR_MESSAGE")
    @Column(name = "WAREHOUSE_ERROR_MESSAGE", columnDefinition = "NVARCHAR2 (500)")
    private String warehouseErrorMsg;

    @DataField(pos = 18, defaultValue = "", columnName = "COMMENTS")
    @Column(name = "COMMENTS", columnDefinition = "NVARCHAR2 (500)")
    private String comments;

    public Orders() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseRef() {
        return warehouseRef;
    }

    public void setWarehouseRef(String warehouseRef) {
        this.warehouseRef = warehouseRef;
    }

    public Long getVacCenterId() {
        return vacCenterId;
    }

    public void setVacCenterId(Long vacCenterId) {
        this.vacCenterId = vacCenterId;
    }



    public Long getVacId() {
        return vacId;
    }

    public void setVacId(Long vacId) {
        this.vacId = vacId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getExitedDeepFreezeTime() {
        return exitedDeepFreezeTime;
    }

    public void setExitedDeepFreezeTime(Date exitedDeepFreezeTime) {
        this.exitedDeepFreezeTime = exitedDeepFreezeTime;
    }

    public Date getReqDeliveryDateTime() {
        return reqDeliveryDateTime;
    }

    public void setReqDeliveryDateTime(Date reqDeliveryDateTime) {
        this.reqDeliveryDateTime = reqDeliveryDateTime;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Date updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getAppointErrorCode() {
        return appointErrorCode;
    }

    public void setAppointErrorCode(Long appointErrorCode) {
        this.appointErrorCode = appointErrorCode;
    }

    public String getAppointErrorMsg() {
        return appointErrorMsg;
    }

    public void setAppointErrorMsg(String appointErrorMsg) {
        this.appointErrorMsg = appointErrorMsg;
    }

    public Long getWarehouseErrorCode() {
        return warehouseErrorCode;
    }

    public void setWarehouseErrorCode(Long warehouseErrorCode) {
        this.warehouseErrorCode = warehouseErrorCode;
    }

    public String getWarehouseErrorMsg() {
        return warehouseErrorMsg;
    }

    public void setWarehouseErrorMsg(String warehouseErrorMsg) {
        this.warehouseErrorMsg = warehouseErrorMsg;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "warehouseId=" + warehouseId +
                ", warehouseRef='" + warehouseRef + '\'' +
                ", vacCenterId=" + vacCenterId +
                ", exitedDeepFreezeTime=" + exitedDeepFreezeTime +
                ", reqDeliveryDateTime=" + reqDeliveryDateTime +
                ", vacId=" + vacId +
                ", quantity=" + quantity +
                ", type=" + type +
                ", status=" + status +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", creator=" + creator +
                '}';
    }
}
