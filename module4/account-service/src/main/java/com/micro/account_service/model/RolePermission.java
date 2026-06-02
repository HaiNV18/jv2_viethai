package com.micro.account_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "role_permission")
public class RolePermission {

    @Id
    private String id;

    @Field("role_id")
    private String roleId;

    @Field("role_permission_id")
    private String rolePermissionId;

    @Field("permission_id")
    private String permissionId;
}
