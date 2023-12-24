package com.ms.user.domain.exception;

public class InvalidPasswordComparation {
    
    public InvalidPasswordComparation () throws Exception {
        throw new Exception("Password comparation fail.");
    }

}
