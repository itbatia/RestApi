package com.itbatia.app.service;

import com.itbatia.app.model.*;
import com.itbatia.app.repository.UserRepository;
import com.itbatia.app.repository.db.DbUserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;
    private String name;
    private List<Event> events;

    @Mock
    private final UserRepository mock = mock(DbUserRepositoryImpl.class);

    @Before
    public void setUp() {
        userService = new UserService(mock);
        name = "Valera";
        events = new ArrayList<>();
    }

    @Test
    public void createUser() {
        User user = new User(null, name, events);
        User userExpected = new User(1, name, events);

        when(mock.save(user)).thenReturn(userExpected);
        User userActual = userService.createUser(name, events);

        verify(mock).save(user); //проверяем, что с методом save() было взаимодействие
        verify(mock, times(1)).save(user); //проверяем, что метод save() был вызван 1 раз
        assertEquals(userExpected, userActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void getUser() {
        User userExpected = new User(1, name, events);

        when(mock.getById(1)).thenReturn(userExpected);
        User userActual = userService.getUser(1);

        verify(mock).getById(1); //проверяем, что с методом getById() было взаимодействие
        verify(mock, never()).getById(2); //проверяем, что метод getById с параметром "2" не вызывался
        assertEquals(userExpected, userActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void getAllUsers() {
        List<User> usersExpected = new ArrayList<>();

        when(mock.getAll()).thenReturn(usersExpected);
        List<User> usersActual = userService.getAllUsers();

        verify(mock).getAll(); //проверяем, что с методом getAll() было взаимодействие
        verify(mock, times(1)).getAll(); //проверяем, что метод getAll() был вызван 1 раз
        assertEquals(usersExpected, usersActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void updateUser() {
        User userExpected = new User(1, name, events);

        when(mock.update(userExpected)).thenReturn(userExpected);
        User userActual = userService.updateUser(userExpected);

        verify(mock).update(userExpected); //проверяем, что с методом update() было взаимодействие
        verify(mock, times(1)).update(userExpected); //проверяем, что метод update() был вызван 1 раз
        assertEquals(userExpected, userActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void deleteUser() {
        userService.deleteUser(1);

        verify(mock).deleteById(1); //проверяем, что с методом deleteById() было взаимодействие
        verify(mock, atLeast(1)).deleteById(1); //проверяем, что взаимодействие с методом
        verify(mock, atMost(2)).deleteById(1); //было минимум 1 раз, но не более 2-х раз
    }
}
