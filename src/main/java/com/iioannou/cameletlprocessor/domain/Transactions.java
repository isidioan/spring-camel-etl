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
@Table(name = "TRANSACTIONS")
@CsvRecord(separator = ",", skipFirstLine = true, generateHeaderColumns = true)
public class Transactions {

    @Id
    @Column(name = "ID", nullable = false, length = 15)
    @DataField(pos = 1, columnName = "ID")
    private Long id;

    @DataField(pos = 2, columnName = "TRANSACTION_ID")
    @Column(name = "TRANSACTION_ID", length = 15)
    private Long transactionId;

    @DataField(pos = 3,  columnName = "TRANSACTION_DATE")
    @Column(name = "TRANSACTION_DATE", columnDefinition = "DATE")
    @BindyConverter(DateConverter.CustomConverter.class)
    private Date transDate;

    @DataField(pos = 4, columnName = "QUANTITY")
    @Column(name = "QUANTITY", length = 10)
    private Long quantity;

    @DataField(pos = 5, columnName = "CREATED_DATETIME")
    @BindyConverter(DateConverter.CustomConverter.class)
    @Column(name = "CREATED_DATETIME", columnDefinition = "DATE")
    private Date createdDateTime;

    public Transactions() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
