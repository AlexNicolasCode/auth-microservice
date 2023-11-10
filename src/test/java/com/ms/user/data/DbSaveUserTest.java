package com.ms.user.data;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        return new DbSaveUser(this.hasherSpy, this.saveUserRepositorySpy);
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
    public void shouldThrowIfHasherThrows() {
        HasherSpy hasherSpy = mock(HasherSpy.class);
        DbSaveUser sut = new DbSaveUser(hasherSpy, new SaveUserRepositorySpy());
        User fakeUser = new UserMock().build();
        when(hasherSpy.hash(anyString())).thenThrow(mock(Error.class));
 
        assertThrows(
           Error.class,
           () -> sut.save(fakeUser),
           "Exception was expected"
        );
    }

    @Test
    public void shouldCallSaveUserRepositoryWithCorrectParam() {
        DbSaveUser sut = makeSut();
        User fakeUser = new UserMock().build();
 
        sut.save(fakeUser);

        assertEquals(fakeUser, this.saveUserRepositorySpy.getUserParam());
    }

    @Test
    public void shouldCallSaveUserRepositoryWithHash() {
        DbSaveUser sut = makeSut();
        User fakeUser = new UserMock().build();
 
        sut.save(fakeUser);

        assertEquals(this.hasherSpy.getResult(), this.saveUserRepositorySpy.getUserParam().getPassword());
    }

    @Test
    public void shouldCallSaveUserRepositoryOnce() {
        DbSaveUser sut = makeSut();
        User fakeUser = new UserMock().build();
 
        sut.save(fakeUser);

        assertEquals(this.saveUserRepositorySpy.getCount(), 1);
    }

    @Test
    public void shouldThrowIfSaveUserRepositoryThrows() {
        SaveUserRepositorySpy saveUserRepositorySpy = mock(SaveUserRepositorySpy.class);
        DbSaveUser sut = new DbSaveUser(new HasherSpy(), saveUserRepositorySpy);
        User fakeUser = new UserMock().build();
        when(saveUserRepositorySpy.save(fakeUser)).thenThrow(mock(Error.class));
 
        assertThrows(
           Error.class,
           () -> sut.save(fakeUser),
           "Exception was expected"
        );
    }
}
