package domain.mock;

import com.github.javafaker.Faker;
import com.ms.user.domain.model.Email;

public class EmailMock {
    public Email build() {
        Faker faker = new Faker();
        String emailString = faker.internet().emailAddress();
        try {
            Email email = new Email(emailString);
            return email;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }
}
