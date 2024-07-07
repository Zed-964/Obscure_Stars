package net.zed964.obscure_stars.exceptions.custom;

import lombok.Getter;

import net.zed964.obscure_stars.exceptions.ExceptionCodeEnum;
import net.zed964.obscure_stars.exceptions.ExceptionsEnum;

/**
 * Exception lorsque le fichier de conf est mal configuré
 */
@Getter
public class BadConfigurationException extends Exception {

    private final ExceptionCodeEnum code;

    /**
     * Constructeur par défaut
     * @param exceptionsEnum Code de l'exception
     * @param cause Cause de l'exception
     */
    public BadConfigurationException(ExceptionsEnum exceptionsEnum, Throwable cause) {
        super(exceptionsEnum.getMessage(), cause);
        this.code = exceptionsEnum.getCode();
    }
}
