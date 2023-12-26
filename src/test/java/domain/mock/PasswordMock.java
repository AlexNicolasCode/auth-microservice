package domain.mock;

import com.github.javafaker.Faker;
import com.ms.user.domain.model.Password;

public class PasswordMock {
    public Password build() {
        Faker faker = new Faker();
        String passwordString = faker.internet().password();
        try {
            Password password = new Password(passwordString);
            return password;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }
}
