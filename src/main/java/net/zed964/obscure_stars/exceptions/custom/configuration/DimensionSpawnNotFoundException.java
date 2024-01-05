package net.zed964.obscure_stars.exceptions.custom.configuration;

import lombok.Getter;
import net.zed964.obscure_stars.exceptions.ExceptionCodeEnum;
import net.zed964.obscure_stars.exceptions.ExceptionEnum;

@Getter
public class DimensionSpawnNotFoundException extends Exception {

    private final ExceptionCodeEnum code;

    public DimensionSpawnNotFoundException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }
}
