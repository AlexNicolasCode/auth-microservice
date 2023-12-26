package data.mock.cryptography;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.ms.user.data.protocol.GenerateToken;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Token;

import domain.mock.TokenMock;

public class GenerateTokenSpy implements GenerateToken {
    private Email email;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private Token token = new TokenMock().build();
    private Integer count = 0;

    @Override
    public Token generateToken(Email email, PublicKey publicKey, PrivateKey privateKey) {
        this.count++;
        this.email = email;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        return this.token;
    }

    public Email getEmail() {
        return email;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
