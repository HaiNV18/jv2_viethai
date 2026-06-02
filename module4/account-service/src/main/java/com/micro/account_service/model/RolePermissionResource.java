package com.micro.account_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "role_permission_resource")
public class RolePermissionResource {

    @Id
    private String id;

    @Field("role_permission_id")
    private String rolePermissionId;

    @Field("resource_id")
    private String resourceId;
}
