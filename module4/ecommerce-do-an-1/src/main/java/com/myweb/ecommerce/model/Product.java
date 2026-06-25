package com.myweb.ecommerce.model;

import com.myweb.ecommerce.enums.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @NotNull(message = "Product ID is required")
    @Field("prd_id")
    private Integer productId;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be 2-100 characters")
    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("brand")
    private String brand;

    @Field("price")
    private Double price;

    @Field("thumbnail")
    private String thumbnail;

    @Field("status")
    private ProductStatus status = ProductStatus.ACTIVE;

    @Field("Create_Date")
    private String createDate;

    @Field("Update_Date")
    private String updateDate;
}
