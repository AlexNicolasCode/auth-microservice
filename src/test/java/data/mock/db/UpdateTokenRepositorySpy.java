package data.mock.db;

import com.ms.user.data.protocol.UpdateTokenRepository;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Token;

public class UpdateTokenRepositorySpy implements UpdateTokenRepository {
    private Email email;
    private Token token;
    private Integer count = 0;

    @Override
    public Token updateToken(Email email, Token token) {
        this.count++;
        this.email = email;
        this.token = token;
        return this.token;
    }

    public Email getEmail() {
        return email;
    }

    public Token getToken() {
        return token;
    }

    public Integer getCount() {
        return count;
    }
}
