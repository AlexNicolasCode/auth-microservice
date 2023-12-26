package data.usecase;

import org.junit.jupiter.api.BeforeEach;
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
    private Email email;
    private Password password;

    private LoadUserByEmailRepositorySpy loadUserByEmailRepositorySpy;
    private ComparePasswordSpy comparePasswordSpy;
    private GenerateTokenSpy generateTokenSpy;
    private GetKeysSpy getKeysSpy; 
    private UpdateTokenRepositorySpy updateTokenRepositorySpy;

    @BeforeEach
    void init() {
        this.email = new EmailMock().build();
        this.password = new PasswordMock().build();
        this.loadUserByEmailRepositorySpy = new LoadUserByEmailRepositorySpy();
        this.comparePasswordSpy = new ComparePasswordSpy(); 
        this.generateTokenSpy = new GenerateTokenSpy(); 
        this.getKeysSpy = new GetKeysSpy();
        this.updateTokenRepositorySpy = new UpdateTokenRepositorySpy();
    }

    DbAuthentication makeSut() {
        DbAuthentication sut = new DbAuthentication(
            loadUserByEmailRepositorySpy,
            comparePasswordSpy,
            generateTokenSpy,
            getKeysSpy,
            updateTokenRepositorySpy
        );
        return sut;
    }

    @Test
    void shouldCallLoadUserByEmailRepositoryOnce() {
        DbAuthentication sut = makeSut();

        sut.auth(email, password);

        assertThat(loadUserByEmailRepositorySpy.getCount()).isEqualTo(1);
    }

    @Test
    void shouldCallComparePasswordOnce() {
        DbAuthentication sut = makeSut();

        sut.auth(email, password);

        assertThat(comparePasswordSpy.getCount()).isEqualTo(1);
    }

    @Test
    void shouldCallGenerateTokenOnce() {
        DbAuthentication sut = makeSut();

        sut.auth(email, password);

        assertThat(generateTokenSpy.getCount()).isEqualTo(1);
    }

    @Test
    void shouldCallGetKeysTwice() {
        DbAuthentication sut = makeSut();

        sut.auth(email, password);

        assertThat(getKeysSpy.getCount()).isEqualTo(2);
    }

    @Test
    void shouldCallUpdateTokenRepositoryOnce() {
        DbAuthentication sut = makeSut();

        sut.auth(email, password);

        assertThat(updateTokenRepositorySpy.getCount()).isEqualTo(1);
    }
}