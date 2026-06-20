package com.myweb.ecommerce.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "bills")
public class Bill {

    @Id
    private String id;

    @NotBlank(message = "Bill ID is required")
    @Field("bill_id")
    private String billId;

    @Field("firstname")
    public String firstname;

    @Field("lastname")
    public String lastname;

    @Field("email")
    public String email;

    @Field("phone")
    public String phone;

    @Field("address")
    public String address;
}
