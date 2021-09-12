package com.example.store.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Receipt {

    private int id;
    private User user;
    private Product product;
    private int count;

    public Receipt(User user, Product product, int count) {
        this.user = user;
        this.product = product;
        this.count = count;
    }
}
