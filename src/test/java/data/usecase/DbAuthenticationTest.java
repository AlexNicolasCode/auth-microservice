package data.usecase;

import org.junit.jupiter.api.Test;

import com.ms.user.data.usecase.DbAuthentication;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Password;

import data.mock.cryptography.ComparePasswordSpy;
import data.mock.cryptography.GenerateTokenSpy;
import data.mock.cryptography.GetKeysSpy;
import data.mock.db.LoadUserByEmailRepositorySpy;
import data.mock.db.UpdateTokenRepositorySpy;
import domain.mock.EmailMock;
import domain.mock.PasswordMock;

import static org.assertj.core.api.Assertions.assertThat;

class DbAuthenticationTest {
    @Test
    void shouldCallLoadUserByEmailRepositoryOnce() {
        Email email = new EmailMock().build();
        Password password = new PasswordMock().build();
        LoadUserByEmailRepositorySpy loadUserByEmailRepositorySpy = new LoadUserByEmailRepositorySpy(); 
        ComparePasswordSpy comparePasswordSpy = new ComparePasswordSpy(); 
        GenerateTokenSpy generateTokenSpy = new GenerateTokenSpy(); 
        GetKeysSpy getKeysSpy = new GetKeysSpy();
        UpdateTokenRepositorySpy updateTokenRepositorySpy = new UpdateTokenRepositorySpy();
        DbAuthentication sut = new DbAuthentication(
            loadUserByEmailRepositorySpy,
            comparePasswordSpy,
            generateTokenSpy,
            getKeysSpy,
            updateTokenRepositorySpy
        );

        sut.auth(email, password);

        assertThat(loadUserByEmailRepositorySpy.getCount()).isEqualTo(1);
    }
}