package com.kdyncs.dragonsong.commons;

import java.util.Collections;
import java.util.List;

public class SafeList {
    public static <T> List<T> get(List<T> other) {
        return other == null ? Collections.emptyList() : other;
    }
}
