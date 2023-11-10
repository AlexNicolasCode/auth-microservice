package com.ms.user.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.ms.user.data.usecase.DbUpdateToken;
import com.ms.user.domain.mock.UserMock;
import com.ms.user.infra.mock.GenerateTokenSpy;
import com.ms.user.infra.mock.UpdateTokenRepositorySpy;

public class DbUpdateTokenTest {

    private GenerateTokenSpy generateTokenSpy;
    private UpdateTokenRepositorySpy updateTokenRepositorySpy;

    public DbUpdateToken makeSut() {
        GenerateTokenSpy generateTokenSpy = new GenerateTokenSpy();
        UpdateTokenRepositorySpy updateTokenRepositorySpy = new UpdateTokenRepositorySpy();
        this.generateTokenSpy = generateTokenSpy;
        this.updateTokenRepositorySpy = updateTokenRepositorySpy;
        return new DbUpdateToken(generateTokenSpy, updateTokenRepositorySpy);
    }


    @Test
    public void shouldCallGenerateTokenWithCorrectParam() {
        DbUpdateToken sut = makeSut();
        Long fakeUserId = new UserMock().build().getId();
 
        sut.update(fakeUserId);

        assertEquals(fakeUserId, this.generateTokenSpy.getUserId());
    }

    @Test
    public void shouldCallGenerateTokenOnce() {
        DbUpdateToken sut = makeSut();
        Long fakeUserId = new UserMock().build().getId();
 
        sut.update(fakeUserId);

        assertEquals(this.generateTokenSpy.getCount(), 1);
    }

    @Test
    public void shouldThrowIfGenerateTokenThrows() {
        GenerateTokenSpy generateTokenSpy = mock(GenerateTokenSpy.class);
        DbUpdateToken sut = new DbUpdateToken(generateTokenSpy, new UpdateTokenRepositorySpy());
        Long fakeUserId = new UserMock().build().getId();
        when(generateTokenSpy.generateToken(fakeUserId)).thenThrow(mock(Error.class));
 
        assertThrows(
           Error.class,
           () -> sut.update(fakeUserId),
           "Exception was expected"
        );
    }

    @Test
    public void shouldCallUpdateTokenRepositoryWithCorrectParams() {
        DbUpdateToken sut = makeSut();
        Long fakeUserId = new UserMock().build().getId();
 
        sut.update(fakeUserId);

        assertEquals(fakeUserId, this.updateTokenRepositorySpy.getUserId());
        assertEquals(this.generateTokenSpy.getResult(), this.updateTokenRepositorySpy.getToken());
    }

    @Test
    public void shouldCallUpdateTokenRepositoryOnce() {
        DbUpdateToken sut = makeSut();
        Long fakeUserId = new UserMock().build().getId();
 
        sut.update(fakeUserId);

        assertEquals(this.updateTokenRepositorySpy.getCount(), 1);
    }

}
