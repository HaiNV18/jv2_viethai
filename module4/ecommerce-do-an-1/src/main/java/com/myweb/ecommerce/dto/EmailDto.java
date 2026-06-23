package com.myweb.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {
    public String emailTo;
    public String subject;
    public String body;

    public String customerName;
}
