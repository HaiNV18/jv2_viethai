package com.micro.account_service.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "jwt_blacklist")
public class JwtBlacklist {

    @Id
    private String id;

    private String token;

    private Date expiredAt;

    public JwtBlacklist() {
    }

    public JwtBlacklist(String token, Date expiredAt) {
        this.token = token;
        this.expiredAt = expiredAt;
    }

}
