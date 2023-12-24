package com.ms.user.domain.exception;

public class InvalidEmailException extends Exception {

    public InvalidEmailException () throws Exception {
        throw new Exception("The email is in the wrong format. Please follow the email pattern and try again.");
    }

}
