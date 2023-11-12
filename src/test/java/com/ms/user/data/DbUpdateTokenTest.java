package com.ms.user.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.ms.user.data.usecase.DbUpdateToken;
import com.ms.user.domain.mock.UserMock;
import com.ms.user.infra.mock.GenerateTokenSpy;
import com.ms.user.infra.mock.GetKeysSpy;
import com.ms.user.infra.mock.UpdateTokenRepositorySpy;

public class DbUpdateTokenTest {

    private GetKeysSpy getKeysSpy;
    private GenerateTokenSpy generateTokenSpy;
    private UpdateTokenRepositorySpy updateTokenRepositorySpy;

    public DbUpdateToken makeSut() {
        GetKeysSpy getKeysSpy = new GetKeysSpy();
        GenerateTokenSpy generateTokenSpy = new GenerateTokenSpy();
        UpdateTokenRepositorySpy updateTokenRepositorySpy = new UpdateTokenRepositorySpy();
        this.getKeysSpy = getKeysSpy;
        this.generateTokenSpy = generateTokenSpy;
        this.updateTokenRepositorySpy = updateTokenRepositorySpy;
        return new DbUpdateToken(getKeysSpy, generateTokenSpy, updateTokenRepositorySpy);
    }

    @Test
    public void shouldCallGetKeysTwice() {
        DbUpdateToken sut = makeSut();
        Long fakeUserId = new UserMock().build().getId();
 
        sut.update(fakeUserId);

        assertEquals(this.getKeysSpy.getCount(), 2);
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
        GetKeysSpy getKeysSpy = new GetKeysSpy();
        GenerateTokenSpy generateTokenSpy = mock(GenerateTokenSpy.class);
        DbUpdateToken sut = new DbUpdateToken(getKeysSpy, generateTokenSpy, new UpdateTokenRepositorySpy());
        Long fakeUserId = new UserMock().build().getId();
        when(
            generateTokenSpy.generateToken(
                fakeUserId,
                getKeysSpy.getPublicKey(),
                getKeysSpy.getPrivateKey()
            )
        ).thenThrow(mock(Error.class));
 
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

    @Test
    public void shouldThrowIfUpdateTokenRepositoryThrows() {
        GetKeysSpy getKeysSpy = new GetKeysSpy();
        GenerateTokenSpy generateTokenSpy = new GenerateTokenSpy();
        UpdateTokenRepositorySpy updateTokenRepositorySpy = mock(UpdateTokenRepositorySpy.class);
        DbUpdateToken sut = new DbUpdateToken(getKeysSpy, generateTokenSpy, updateTokenRepositorySpy);
        Long fakeUserId = new UserMock().build().getId();
        when(updateTokenRepositorySpy.updateToken(fakeUserId, generateTokenSpy.getResult())).thenThrow(mock(Error.class));
 
        assertThrows(
           Error.class,
           () -> sut.update(fakeUserId),
           "Exception was expected"
        );
    }
}
