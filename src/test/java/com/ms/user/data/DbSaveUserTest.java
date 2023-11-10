package com.ms.user.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ms.user.data.usecase.DbSaveUser;
import com.ms.user.domain.mock.UserMock;
import com.ms.user.domain.model.User;
import com.ms.user.infra.mock.HasherSpy;
import com.ms.user.infra.mock.SaveUserRepositorySpy;

public class DbSaveUserTest {

    @Test
    public void shouldCallHasherWithCorrectParam() {
        HasherSpy hasherSpy = new HasherSpy();
        SaveUserRepositorySpy saveUserRepositorySpy = new SaveUserRepositorySpy();
        DbSaveUser sut = new DbSaveUser(hasherSpy, saveUserRepositorySpy);
        User fakeUser = new UserMock().build();
        String fakePassword = fakeUser.getPassword();
 
        sut.save(fakeUser);

        assertEquals(fakePassword, hasherSpy.getPlaintext());
    }
}
