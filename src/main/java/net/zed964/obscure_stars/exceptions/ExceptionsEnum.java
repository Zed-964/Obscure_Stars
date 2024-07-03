package net.zed964.obscure_stars.exceptions;

import lombok.Getter;

/**
 * Liste des messages des exceptions du mod associés à leur code
 */
@Getter
public enum ExceptionsEnum {

    BAD_CONFIGURATION_DEFAULT("An error occurred related to the configuration file", ExceptionCodeEnum.OS_100),
    DIMENSION_CONFIG_HAS_SUFFOCATION_NOT_FOUND("A dimension who has configure in config file was not fount at the startup of the server", ExceptionCodeEnum.OS_101);

    private final String message;

    private final ExceptionCodeEnum code;

    /**
     * Constructeur par défaut
     * @param message Message de l'erreur
     * @param code code de l'erreur
     */
    ExceptionsEnum(String message, ExceptionCodeEnum code) {
        this.message = message;
        this.code = code;
    }
}
