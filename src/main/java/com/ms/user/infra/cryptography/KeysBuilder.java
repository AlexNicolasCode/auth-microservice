package com.ms.user.infra.cryptography;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.GetKeys;

@Component
public class KeysBuilder implements GetKeys {
    private static KeysBuilder instance;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    private KeysBuilder () {
        try {
            boolean hasKeysFiles = this.checkIfKeysFilesExists();
            if (!hasKeysFiles) {
                this.generatePairKey();
                this.savePairKey();
            }
            this.loadPairKey();
        } catch (Exception error) {
            System.out.println(error);
        }
    }

    public static KeysBuilder getInstance() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (instance == null) {
            instance = new KeysBuilder(); 
        }
        return instance;
    }

    private boolean checkIfKeysFilesExists() {
        File publicKeyFile = new File("public.key");
        File privateKeyFile = new File("private.key");
        boolean hasPrivateKeyFile = privateKeyFile.exists() && !privateKeyFile.isDirectory();
        boolean hasPublicKeyFile = publicKeyFile.exists() && !publicKeyFile.isDirectory();
        return hasPrivateKeyFile && hasPublicKeyFile;
    }

    private void loadPairKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        File publicKeyFile = new File("public.key");
        File privateKeyFile = new File("private.key");
        byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
        byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.privateKey = keyFactory.generatePrivate(privateKeySpec);;
        this.publicKey = keyFactory.generatePublic(publicKeySpec);
    }

    private void generatePairKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(2048);
        KeyPair keys = keyGenerator.generateKeyPair();
        this.privateKey = keys.getPrivate();
        this.publicKey = keys.getPublic();
    }

    public void savePairKey() throws IOException {
        try (FileOutputStream fileOutputPublicKey = new FileOutputStream("public.key")) {
            fileOutputPublicKey.write(this.publicKey.getEncoded());
        }
        try (FileOutputStream fileOutputPrivateKey = new FileOutputStream("private.key")) {
            fileOutputPrivateKey.write(this.privateKey.getEncoded());
        }
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
}
