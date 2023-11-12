package com.ms.user.infra.mock;

import java.security.Key;

import com.ms.user.data.protocol.GetKeys;

public class GetKeysSpy implements GetKeys {
    private Key privateKey;
    private Key publicKey;

    @Override
    public Key getPrivateKey() {
        return this.privateKey;
    }

    @Override
    public Key getPublicKey() {
        return this.publicKey;
    }
}
