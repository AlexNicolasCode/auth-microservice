package com.ms.user.infra.cryptography;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.GetKeys;

@Component
public class KeysBuilder implements GetKeys {
    private static KeysBuilder instance;
    private Key publicKey; 
    private Key privateKey;

    private KeysBuilder () throws NoSuchAlgorithmException {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(2048);
        KeyPair keys = keyGenerator.generateKeyPair();
        this.publicKey = keys.getPublic();
        this.privateKey = keys.getPrivate();
    }

    public static KeysBuilder getInstance() throws NoSuchAlgorithmException {
        if (instance == null) {
            instance = new KeysBuilder(); 
        }
        return instance;
    }

    public Key getPublicKey() {
        return this.publicKey;
    }

    public Key getPrivateKey() {
        return this.privateKey;
    }
}
