package com.dariphion.notee.services;

import com.dariphion.notee.domain.Note;
import com.dariphion.notee.domain.User;
import com.dariphion.notee.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // Create a new note for a specific user
    public Note createNote(Note note, User user) {
        note.setUser(user);  // Associate the note with the user
        return noteRepository.save(note);
    }

    // Find all notes for a specific user
    public List<Note> findNotesByUser(User user) {
        return noteRepository.findByUser(user);
    }

    // Method to return all notes
    public List<Note> findAllNotes() {
        return noteRepository.findAll();  // Assuming there's a NoteRepository that extends JpaRepository
    }

    // Find a note by its ID
    public Optional<Note> findNoteById(Long id) {
        return noteRepository.findById(id);
    }

    // Update an existing note
    public Note updateNote(Note updatedNote) {
        return noteRepository.save(updatedNote);
    }

    // Delete a note by its ID
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}