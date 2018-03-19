package org.wjc.maven.util;

public final class StringUtils {

    private StringUtils() {
        // Prevent instantiation of this class
    }

    public static boolean isNullOrEmpty(@org.jetbrains.annotations.Nullable final String string) {
        return string == null || string.isEmpty();
    }
}
