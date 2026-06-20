package com.myweb.ecommerce.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "bill_product")
public class BillProduct {

    @Id
    private String id;

    @NotBlank(message = "Bill ID is required")
    @Field("bill_id")
    private String billId;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be 2-100 characters")
    @Field("name_product")
    private String nameProduct;

    @Field("price")
    private Double price;

    @Field("qty")
    private Integer qty;

    @Field("total")
    private Integer total;
}
