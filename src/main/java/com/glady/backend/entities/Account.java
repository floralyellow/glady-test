package com.glady.backend.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "is_company")
    private boolean isCompany;

    @OneToMany(mappedBy = "emiter", cascade = CascadeType.ALL)
    private List<Transaction> emitedTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "reciever", cascade = CascadeType.ALL)
    private List<Transaction> recievedTransactions = new ArrayList<>();

    public Account( String name, boolean isCompany) {
        this.name = name;
        this.isCompany = isCompany;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean isCompany) {
        this.isCompany = isCompany;
    }

    // public List<Transaction> getTransactions() {
    //     return transactions;
    // }

    // public void setTransactions(List<Transaction> transactions) {
    //     this.transactions = transactions;
    // }
}