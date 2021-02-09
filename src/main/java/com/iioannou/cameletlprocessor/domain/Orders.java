package com.iioannou.cameletlprocessor.domain;

import com.iioannou.cameletlprocessor.util.DateConverter;
import org.apache.camel.dataformat.bindy.annotation.BindyConverter;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
@CsvRecord(separator = ",", skipFirstLine = true, generateHeaderColumns = true)
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

    @DataField(pos = 4, columnName = "REQUESTED_DELIVERY_DATETIME")
    @Column(name = "REQUESTED_DELIVERY_DATETIME", columnDefinition = "DATE")
    @BindyConverter(DateConverter.CustomConverter.class)
    private Date reqDeliveryDateTime;

    @DataField(pos = 5, columnName = "QUANTITY_IN_BATCHES")
    @Column(name = "QUANTITY_IN_BATCHES", length = 10)
    private Long quantity;

    @DataField(pos = 6, columnName = "TYPE")
    @Column(name = "TYPE", length = 10)
    private Long type;

    @DataField(pos = 7, columnName = "STATUS")
    @Column(name = "STATUS", length = 10)
    private Long status;

    @DataField(pos = 8, columnName = "CREATED_DATETIME")
    @Column(name = "CREATED_DATETIME", columnDefinition = "DATE")
    @BindyConverter(DateConverter.CustomConverter.class)
    private Date createdDateTime;

    @DataField(pos = 9, columnName = "LAST_UPDATED_DATETIME")
    @Column(name = "LAST_UPDATED_DATETIME", columnDefinition = "DATE")
    @BindyConverter(DateConverter.CustomConverter.class)
    private Date updatedDateTime;

    @DataField(pos = 10, columnName = "ERROR_CODE")
    @Column(name = "ERROR_CODE", length = 10)
    private Long errorCode;

    @DataField(pos = 11, columnName = "ERROR_MESSAGE")
    @Column(name = "ERROR_MESSAGE", columnDefinition = "NVARCHAR2 (500)")
    private String errorMsg;


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

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "warehouseId=" + warehouseId +
                ", warehouseRef='" + warehouseRef + '\'' +
                ", reqDeliveryDateTime=" + reqDeliveryDateTime +
                ", quantity=" + quantity +
                ", type=" + type +
                ", status=" + status +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                '}';
    }
}
