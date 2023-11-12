package com.ms.user.infra.mock;

import java.security.Key;

import com.ms.user.data.protocol.GetKeys;

public class GetKeysSpy implements GetKeys {
    private int count = 0;
    private Key privateKey;
    private Key publicKey;

    @Override
    public Key getPrivateKey() {
        this.count++;
        return this.privateKey;
    }
    
    @Override
    public Key getPublicKey() {
        this.count++;
        return this.publicKey;
    }

    public int getCount () {
        return this.count;
    }
}
