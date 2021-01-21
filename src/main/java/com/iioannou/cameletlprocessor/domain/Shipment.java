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
@Table(name = "SHIPMENT")
@CsvRecord(separator = ";", skipFirstLine = true, generateHeaderColumns = true)
public class Shipment {

    @Id
    @Column(name = "ID", nullable = false, length = 15)
    @DataField(pos = 1, columnName = "ID")
    private Long id;

    @Column(name = "ORDER_ID", length = 15)
    @DataField(pos = 2, columnName = "ORDER_ID")
    private Long orderId;

    @DataField(pos = 3, columnName = "QUANTITY")
    @Column(name = "QUANTITY", length = 10)
    private Long quantity;

    @DataField(pos = 4, columnName = "STATUS")
    @Column(name = "STATUS", length = 10)
    private Long status;

    @DataField(pos = 5, columnName = "CREATED_DATETIME")
    @Column(name = "CREATED_DATETIME", columnDefinition = "DATE")
    @BindyConverter(DateConverter.CustomConverter.class)
    private Date createdDateTime;

    @DataField(pos = 6, columnName = "LAST_UPDATE_DATETIME")
    @Column(name = "LAST_UPDATE_DATETIME", columnDefinition = "DATE")
    @BindyConverter(DateConverter.CustomConverter.class)
    private Date updatedDateTime;

    @DataField(pos = 7, columnName = "ERROR_CODE")
    @Column(name = "ERROR_CODE", length = 10)
    private Long errorCode;

    @DataField(pos = 8, columnName = "ERROR_MESSAGE")
    @Column(name = "ERROR_MESSAGE", columnDefinition = "NVARCHAR2 (500)")
    private String errorMsg;

    public Shipment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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
}
