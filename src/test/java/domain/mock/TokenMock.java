package domain.mock;

import com.github.javafaker.Faker;
import com.ms.user.domain.model.Token;

public class TokenMock {
    public Token build() {
        Faker faker = new Faker(); 
        Token token = new Token(faker.crypto().sha256());
        return token;
    }
}
