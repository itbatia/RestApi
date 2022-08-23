package com.itbatia.app.service;

import com.itbatia.app.model.File;
import com.itbatia.app.repository.FileRepository;
import com.itbatia.app.repository.db.DbFileRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class FileServiceTest {

    private FileService fileService;
    private String fileName;
    private String content;

    @Mock
    private final FileRepository mock = mock(DbFileRepositoryImpl.class);

    @Before
    public void SetUp() {
        fileService = new FileService(mock);
        fileName = "Test";
        content = "This is some file content";
    }

    @Test
    public void createFile() {
        File file = new File(null, fileName, content);
        File fileExpected = new File(1, fileName, content);

        when(mock.save(file)).thenReturn(fileExpected);
        File fileActual = fileService.createFile(fileName, content);

        verify(mock).save(file); //проверяем, что с методом save() было взаимодействие
        verify(mock, times(1)).save(file); //проверяем, что метод save() был вызван 1 раз
        assertEquals(fileExpected, fileActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void getFile() {
        File fileExpected = new File(1, fileName, content);

        when(mock.getById(1)).thenReturn(fileExpected);
        File fileActual = fileService.getFile(1);

        verify(mock).getById(1); //проверяем, что с методом getById() было взаимодействие
        verify(mock, never()).getById(2); //проверяем, что метод getById с параметром "2" не вызывался
        assertEquals(fileExpected, fileActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void getAllFiles() {
        List<File> filesExpected = new ArrayList<>();

        when(mock.getAll()).thenReturn(filesExpected);
        List<File> filesActual = fileService.getAllFiles();

        verify(mock).getAll(); //проверяем, что с методом getAll() было взаимодействие
        verify(mock, times(1)).getAll(); //проверяем, что метод getAll() был вызван 1 раз
        assertEquals(filesExpected, filesActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void updateFile() {
        File fileExpected = new File(1, fileName, content);

        when(mock.update(fileExpected)).thenReturn(fileExpected);
        File fileActual = fileService.updateFile(fileExpected);

        verify(mock).update(fileExpected); //проверяем, что с методом update() было взаимодействие
        verify(mock, times(1)).update(fileExpected); //проверяем, что метод update() был вызван 1 раз
        assertEquals(fileExpected, fileActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void deleteFile() {
        fileService.deleteFile(1);

        verify(mock).deleteById(1); //проверяем, что с методом deleteById() было взаимодействие
        verify(mock, atLeast(1)).deleteById(1); //проверяем, что взаимодействие с методом
        verify(mock, atMost(2)).deleteById(1); //было минимум 1 раз, но не более 2-х раз
    }
}
