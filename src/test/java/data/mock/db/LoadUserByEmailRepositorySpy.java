package data.mock.db;

import com.ms.user.data.protocol.LoadUserByEmailRepository;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.User;

import domain.mock.UserMock;

public class LoadUserByEmailRepositorySpy implements LoadUserByEmailRepository {
    private Email email;
    private Integer count = 0;

    @Override
    public User loadUserByEmail(Email email) {
        this.count++;
        this.email = email;
        return new UserMock().build();
    }

    public Email getEmail() {
        return email;
    }

    public Integer getCount() {
        return count;
    }
}
