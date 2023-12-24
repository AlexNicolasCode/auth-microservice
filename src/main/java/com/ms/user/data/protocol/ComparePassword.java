package com.ms.user.data.protocol;

import com.ms.user.domain.model.Password;

public interface ComparePassword {
    boolean compare(Password password, Password passwordEncoded);
}
