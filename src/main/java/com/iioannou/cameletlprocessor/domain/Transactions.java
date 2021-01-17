package com.iioannou.cameletlprocessor.domain;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "TRANSACTIONS")
@CsvRecord(separator = ";", skipFirstLine = true, generateHeaderColumns = true)
public class Transactions {

    @Id
    @Column(name = "ID", nullable = false, length = 15)
    @DataField(pos = 1, columnName = "ID")
    private Long id;

    @DataField(pos = 2, columnName = "WAREHOUSE_ID")
    @Column(name = "WAREHOUSE_ID", length = 10)
    private Long warehouseId;

    @DataField(pos = 3, columnName = "TRANSACTION_ID")
    @Column(name = "TRANSACTION_ID", length = 15)
    private Long transactionId;

    @DataField(pos = 4, pattern = "dd/MM/yyyy HH:mm", columnName = "TRANSACTION_DATE")
    @Column(name = "TRANSACTION_DATE", columnDefinition = "DATE")
    private Date transDate;

    @DataField(pos = 5, columnName = "VACCINE_ID")
    @Column(name = "VACCINE_ID", length = 10)
    private Long vacId;

    @DataField(pos = 6, columnName = "QUANTITY")
    @Column(name = "QUANTITY", length = 10)
    private Long quantity;

    @DataField(pos = 7, pattern = "dd/MM/yyyy HH:mm", columnName = "CREATED_DATETIME")
    @Column(name = "CREATED_DATETIME", columnDefinition = "DATE")
    private Date createdDateTime;

    @DataField(pos = 8, defaultValue = "", columnName = "COMMENTS")
    @Column(name = "COMMENTS", columnDefinition = "NVARCHAR2 (500)")
    private String comments;

    public Transactions() {
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
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

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
