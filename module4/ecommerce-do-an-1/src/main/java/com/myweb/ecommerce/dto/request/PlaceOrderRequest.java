package com.myweb.ecommerce.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderRequest {
    public String firstname;
    public String lastname;
    public String email;
    public String phone;
    public String address;

    public String cartData;
}
