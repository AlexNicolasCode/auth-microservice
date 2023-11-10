package com.ms.user.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ms.user.data.usecase.DbSaveUser;
import com.ms.user.domain.mock.UserMock;
import com.ms.user.domain.model.User;
import com.ms.user.infra.mock.HasherSpy;
import com.ms.user.infra.mock.SaveUserRepositorySpy;

public class DbSaveUserTest {

    private HasherSpy hasherSpy;
    private SaveUserRepositorySpy saveUserRepositorySpy;

    public DbSaveUser makeSut() {
        HasherSpy hasherSpy = new HasherSpy();
        SaveUserRepositorySpy saveUserRepositorySpy = new SaveUserRepositorySpy();
        this.hasherSpy = hasherSpy;
        this.saveUserRepositorySpy = saveUserRepositorySpy;
        return new DbSaveUser(hasherSpy, saveUserRepositorySpy);
    }

    @Test
    public void shouldCallHasherWithCorrectParam() {
        DbSaveUser sut = makeSut();
        User fakeUser = new UserMock().build();
        String fakePassword = fakeUser.getPassword();
 
        sut.save(fakeUser);

        assertEquals(fakePassword, this.hasherSpy.getPlaintext());
    }

    @Test
    public void shouldCallHasherOnce() {
        DbSaveUser sut = makeSut();
        User fakeUser = new UserMock().build();
 
        sut.save(fakeUser);

        assertEquals(this.hasherSpy.getCount(), 1);
    }

    @Test
    public void shouldCallSaveUserRepositoryWithCorrectParam() {
        DbSaveUser sut = makeSut();
        User fakeUser = new UserMock().build();
 
        sut.save(fakeUser);

        assertEquals(fakeUser, this.saveUserRepositorySpy.getUserParam());
    }
}
