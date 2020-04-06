package Note;

import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.easymock.EasyMock.*;

public class NotesServiceTest {

    NotesService service;
    NotesStorage storage;

    @BeforeEach
    public void setUp() {
        storage = createMock(NotesStorage.class);
        service = createMock(NotesService.class);

    }

    @Test
    public void testClear() {

        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(Note.of("Adam", 5.0f));

        service.clear();
        EasyMock.expectLastCall().andAnswer(() -> {
            notes.clear();
            return null;
        }).times(1);

        replay(service);

        assertThat(notes).hasSize(1);
        service.clear();
        assertThat(notes).isEmpty();
        verify(service);
    }

    @Test
    public void testAddNote() {
        Note input = Note.of("Adam", 4.5f);
        ArrayList<Note> expected = new ArrayList<Note>();

        storage.add(input);
        EasyMock.expectLastCall().andAnswer(() -> {
            expected.add(input);
            return null;
        }).times(1);
        replay(storage);

        storage.add(input);

        assertThat(expected).hasSize(1);
        assertThat(expected.get(0).getName()).isEqualTo("Adam");
        assertThat(expected.get(0).getNote()).isEqualTo(4.5f);

        verify(storage);
    }

    @Test
    public void testAddMoreNotes() {
        //Prepare
        Note input = Note.of("Adam", 3.0f);
        ArrayList<Note> expected = new ArrayList<Note>();

        //Act
        storage.add(input);
        EasyMock.expectLastCall().andAnswer(() -> {
            expected.add(input);
            return null;
        }).times(3);
        replay(storage);

        storage.add(input);
        storage.add(input);
        storage.add(input);

        assertThat(expected).hasSize(3);

        verify(storage);
    }


    @Test
    public void testGetAllNotes() {
        List<Note> expected = Arrays.asList(new Note[]{
                Note.of("Adam", 5),
                Note.of("Adam", 4)
        });
        expect(storage.getAllNotesOf("Adam")).andReturn(expected);
        replay(storage);
        assertThat(storage.getAllNotesOf("Adam")).hasSize(2).containsAll(expected);
        verify(storage);
    }

    @Test
    public void testAverageGrade() {
        float avg = 3.7f;
        expect(service.averageOf("Adam")).andReturn(avg);
        replay(service);

        assertThat(service.averageOf("Adam")).isEqualTo(avg);
        verify(service);
    }

    @Test
    public void testAverageGradeEmpty() {

        EasyMock.expect(service.averageOf("")).andThrow(new IllegalArgumentException("Imię ucznia nie może być puste"));
        EasyMock.replay(service);
        assertThatThrownBy(() -> {
            service.averageOf("");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być puste");

     EasyMock.verify(service);


    }

    @Test
    public void testAverageGradeNull() {

        EasyMock.expect(service.averageOf(null)).andThrow(new IllegalArgumentException("Imię ucznia nie może być null"));
        EasyMock.replay(service);
        assertThatThrownBy(() -> {
            service.averageOf(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być null");

        EasyMock.verify(service);
    }

    @AfterEach
    public void tearDown(){
        storage = null;
        service = null;
    }


}
