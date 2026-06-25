package com.myweb.ecommerce.enums;

public enum ProductStatus {
    ACTIVE(1),
    INACTIVE(2),
    OUT_OF_STOCK(3),
    DELETED(4);

    private final int code;

    ProductStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ProductStatus fromCode(int code) {
        for (ProductStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ProductStatus code: " + code);
    }

    public static String getNameByCode(int code) {
        return fromCode(code).name();
    }
}