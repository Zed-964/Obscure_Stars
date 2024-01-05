package net.zed964.obscure_stars.exceptions.custom.configuration;

import lombok.Getter;
import net.zed964.obscure_stars.exceptions.ExceptionEnum;
import net.zed964.obscure_stars.exceptions.ExceptionCodeEnum;

@Getter
public class BadConfigurationException extends Exception {

    private final ExceptionCodeEnum code;

    public BadConfigurationException(ExceptionEnum exceptionEnum, Throwable cause) {
        super(exceptionEnum.getMessage(), cause);
        this.code = exceptionEnum.getCode();
    }
}