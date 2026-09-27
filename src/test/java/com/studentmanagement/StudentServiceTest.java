package com.studentmanagement;

import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    @Test
    void addStudent_shouldSaveStudent() {
        Student student = new Student("Priya", "priya@gmail.com", "BSc IT", 21);
        when(repository.save(student)).thenReturn(student);

        Student result = service.addStudent(student);

        assertEquals("Priya", result.getName());
        verify(repository).save(student);
    }

    @Test
    void getStudents_shouldReturnStudents() {
        Student student = new Student("Priya", "priya@gmail.com", "BSc IT", 21);
        when(repository.findAll()).thenReturn(List.of(student));

        List<Student> result = service.getStudents();

        assertEquals(1, result.size());
        assertEquals("Priya", result.get(0).getName());
    }

    @Test
    void getStudent_shouldReturnStudent() {
        Student student = new Student("Priya", "priya@gmail.com", "BSc IT", 21);
        when(repository.findById(1L)).thenReturn(Optional.of(student));

        Student result = service.getStudent(1L);

        assertNotNull(result);
        assertEquals("Priya", result.getName());
    }

    @Test
    void deleteStudent_shouldDeleteExistingStudent() {
        when(repository.existsById(1L)).thenReturn(true);

        boolean result = service.deleteStudent(1L);

        assertTrue(result);
        verify(repository).deleteById(1L);
    }
}
