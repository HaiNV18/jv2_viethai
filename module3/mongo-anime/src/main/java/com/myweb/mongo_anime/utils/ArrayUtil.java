package com.myweb.mongo_anime.utils;

import java.util.List;

public class ArrayUtil {

    public static <T> T getFirstElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
