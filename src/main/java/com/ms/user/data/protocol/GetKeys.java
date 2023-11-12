package com.ms.user.data.protocol;

import java.security.Key;

public interface GetKeys {
    Key getPublicKey();
    Key getPrivateKey();
}
