package net.zed964.obscure_stars.utils;

public class MethodUtils {

    private MethodUtils() {

    }

    public static float keepMaxValue(float target, float value) {
        return Math.max(target, value);
    }
}
