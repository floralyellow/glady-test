package com.glady.backend.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "transaction_type")
    private TransactionTypeEnum transactionType;

    @Column(name = "transaction_amount")
    private int transactionAmount;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name ="emiter_id", nullable = false)
    private Account emiter;

    @ManyToOne
    @JoinColumn(name ="reciever_id", nullable = false)
    private Account reciever;
    Transaction(){
        
    }

    Transaction(TransactionTypeEnum transactionType, Account emiter, Account reciever,int transactionAmount){
        this.transactionType = transactionType;
        this.emiter = emiter;
        this.reciever = reciever;
        this.transactionAmount = transactionAmount;
        this.transactionDate = LocalDateTime.now();
        if(transactionType == TransactionTypeEnum.gift) {
            this.expirationDate = this.transactionDate.plusYears(1);
        }
        else if(transactionType == TransactionTypeEnum.meal) {
            this.expirationDate = LocalDateTime.now().withMonth(2).plusYears(1);
        }
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }
    public int getTransactionAmount() {
        return transactionAmount;
    }
    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
    public Account getEmiter() {
        return emiter;
    }
    public void setEmiter(Account emiter) {
        this.emiter = emiter;
    }
    public Account getReciever() {
        return reciever;
    }
    public void setReciever(Account reciever) {
        this.reciever = reciever;
    }
}