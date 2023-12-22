package com.ms.user.data.protocol;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface GetKeys {
    PublicKey getPublicKey();
    PrivateKey getPrivateKey();
}
