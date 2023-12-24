package com.ms.user.domain.exception;

public class InvalidPasswordLength extends Exception {

    public InvalidPasswordLength () throws Exception {
        throw new Exception("Password have length less than 10. Please, use a password with more than 10 caracteres.");
    }

}
