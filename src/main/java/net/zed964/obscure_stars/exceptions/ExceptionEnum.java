package net.zed964.obscure_stars.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    BAD_CONFIGURATION_DEFAULT("An error occurred related to the configuration file", ExceptionCodeEnum.OS_100),
    SPAWN_DIMENSION_NOT_FOUND("The dimension that is in the configuration file was not found at startup of the server", ExceptionCodeEnum.OS_101),
    SPAWN_STRUCTURE_FILE_NOT_FOUND("The structure file that is in the configuration file was not found at startup of the server", ExceptionCodeEnum.OS_101);


    private final String message;

    private final ExceptionCodeEnum code;

    ExceptionEnum(String message, ExceptionCodeEnum code) {
        this.message = message;
        this.code = code;
    }
}
