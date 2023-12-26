package domain.mock;

import com.github.javafaker.Faker;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Password;
import com.ms.user.domain.model.User;

public class UserMock {
    public User build() {
        User user = new User();
        try {
            Faker faker = new Faker(); 
            String name = faker.name().firstName();
            Email email = new Email(faker.internet().emailAddress());
            Password password = new Password(faker.internet().password());
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            return user;
        } catch (Exception error) {
            error.printStackTrace();
            return user;
        }
    }
}
