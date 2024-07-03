package net.zed964.obscure_stars.exceptions.custom;

import lombok.Getter;

import net.zed964.obscure_stars.exceptions.ExceptionCodeEnum;
import net.zed964.obscure_stars.exceptions.ExceptionsEnum;

/**
 * Exception lorsqu'une dimension indiquée dans le fichier de configuration n'est pas trouvé en jeu
 */
@Getter
public class DimensionNotFoundException extends Exception {

    private final ExceptionCodeEnum code;

    /**
     * Constructeur par défaut
     * @param exceptionsEnum Code de l'exception
     */
    public DimensionNotFoundException(ExceptionsEnum exceptionsEnum) {
        super(exceptionsEnum.getMessage());
        this.code = exceptionsEnum.getCode();
    }
}
