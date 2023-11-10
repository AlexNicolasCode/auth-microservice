package com.ms.user.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.ms.user.data.usecase.DbUpdateToken;
import com.ms.user.domain.mock.UserMock;
import com.ms.user.infra.mock.GenerateTokenSpy;
import com.ms.user.infra.mock.UpdateTokenRepositorySpy;

public class DbUpdateTokenTest {

    @Test
    public void shouldCallGenerateTokenWithCorrectParam() {
        GenerateTokenSpy generateTokenSpy = new GenerateTokenSpy();
        UpdateTokenRepositorySpy updateTokenRepositorySpy = new UpdateTokenRepositorySpy();
        DbUpdateToken sut = new DbUpdateToken(generateTokenSpy, updateTokenRepositorySpy);
        Long fakeUserId = new UserMock().build().getId();
 
        sut.update(fakeUserId);

        assertEquals(fakeUserId, generateTokenSpy.getUserId());
    }

    @Test
    public void shouldCallGenerateTokenOnce() {
        GenerateTokenSpy generateTokenSpy = new GenerateTokenSpy();
        UpdateTokenRepositorySpy updateTokenRepositorySpy = new UpdateTokenRepositorySpy();
        DbUpdateToken sut = new DbUpdateToken(generateTokenSpy, updateTokenRepositorySpy);
        Long fakeUserId = new UserMock().build().getId();
 
        sut.update(fakeUserId);

        assertEquals(generateTokenSpy.getCount(), 1);
    }
}
