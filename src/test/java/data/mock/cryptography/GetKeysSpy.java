package data.mock.cryptography;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.ms.user.data.protocol.GetKeys;
import com.ms.user.infra.cryptography.KeysBuilder;

public class GetKeysSpy implements GetKeys {
    private KeysBuilder keysBuilder = new KeysBuilder();
    private PrivateKey privateKey = this.keysBuilder.getPrivateKey();
    private PublicKey publicKey = this.keysBuilder.getPublicKey();
    private Integer count = 0;

    public void setPrivateKey(PrivateKey privateKey) {
        this.count++;
        this.privateKey = privateKey;
    }
    
    public void setPublicKey(PublicKey publicKey) {
        this.count++;
        this.publicKey = publicKey;
    }

    @Override
    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    @Override
    public PublicKey getPublicKey() {
        return this.publicKey;
    }

     public Integer getCount() {
         return count;
     }
}
