package com.interview.product.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductRequestModel {

    private String name;

    private String type;

    private int price;

    private String imageUrl;

    private int groupId;
}
