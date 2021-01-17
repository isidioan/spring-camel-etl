package com.iioannou.cameletlprocessor.domain;

import com.pwc.covid.logisticfileprocessing.util.DateConverter;
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

    @Column(name = "FROM_WAREHOUSE_ID", length = 10)
    @DataField(pos = 3, columnName = "FROM_WAREHOUSE_ID")
    private Long frWarehouseId;

    @Column(name = "TO_VACCINATION_CENTER_ID", length = 10)
    @DataField(pos = 4, columnName = "TO_VACCINATION_CENTER_ID")
    private Long toVacCenterId;

    @DataField(pos = 5, columnName = "LOGISTICS_REFERENCE")
    @Column(name = "LOGISTICS_REFERENCE", columnDefinition = "NVARCHAR2 (32)")
    private String logisticsRef;

    @DataField(pos = 6, columnName = "WAREHOUSE_REFERENCE")
    @Column(name = "WAREHOUSE_REFERENCE", columnDefinition = "NVARCHAR2 (32)")
    private String warehouseRef;

    @DataField(pos = 7, columnName = "VACCINE_ID")
    @Column(name = "VACCINE_ID", length = 10)
    private Long vacId;

    @DataField(pos = 8, columnName = "QUANTITY")
    @Column(name = "QUANTITY", length = 10)
    private Long quantity;

    @DataField(pos = 9, columnName = "STATUS")
    @Column(name = "STATUS", length = 10)
    private Long status;

    @DataField(pos = 10, columnName = "EXIT_DEEP_FREEZE_DATETIME")
    @Column(name = "EXIT_DEEP_FREEZE_DATETIME", columnDefinition = "DATE")
    @BindyConverter(DateConverter.CustomConverter.class)
    private Date exitedDeepFreezeTime;

    @DataField(pos = 11, columnName = "CREATED_DATETIME")
    @Column(name = "CREATED_DATETIME", columnDefinition = "DATE")
    @BindyConverter(DateConverter.CustomConverter.class)
    private Date createdDateTime;

    @DataField(pos = 12, columnName = "LAST_UPDATE_DATETIME")
    @Column(name = "LAST_UPDATE_DATETIME", columnDefinition = "DATE")
    @BindyConverter(DateConverter.CustomConverter.class)
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

    @DataField(pos = 16, columnName = "TRANSPORT_ERROR_CODE")
    @Column(name = "TRANSPORT_ERROR_CODE", length = 10)
    private Long transportErrorCode;

    @DataField(pos = 17, columnName = "TRANSPORT_ERROR_MESSAGE")
    @Column(name = "TRANSPORT_ERROR_MESSAGE", columnDefinition = "NVARCHAR2 (500)")
    private String transportErrorMsg;

    @DataField(pos = 18, defaultValue = "", columnName = "COMMENTS")
    @Column(name = "COMMENTS", columnDefinition = "NVARCHAR2 (500)")
    private String comments;

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

    public Long getFrWarehouseId() {
        return frWarehouseId;
    }

    public void setFrWarehouseId(Long frWarehouseId) {
        this.frWarehouseId = frWarehouseId;
    }

    public Long getToVacCenterId() {
        return toVacCenterId;
    }

    public void setToVacCenterId(Long toVacCenterId) {
        this.toVacCenterId = toVacCenterId;
    }

    public String getLogisticsRef() {
        return logisticsRef;
    }

    public void setLogisticsRef(String logisticsRef) {
        this.logisticsRef = logisticsRef;
    }

    public String getWarehouseRef() {
        return warehouseRef;
    }

    public void setWarehouseRef(String warehouseRef) {
        this.warehouseRef = warehouseRef;
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

    public Long getTransportErrorCode() {
        return transportErrorCode;
    }

    public void setTransportErrorCode(Long transportErrorCode) {
        this.transportErrorCode = transportErrorCode;
    }

    public String getTransportErrorMsg() {
        return transportErrorMsg;
    }

    public void setTransportErrorMsg(String transportErrorMsg) {
        this.transportErrorMsg = transportErrorMsg;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
