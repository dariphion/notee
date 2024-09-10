package com.dariphion.notee.controllers;

import com.dariphion.notee.domain.Note;
import com.dariphion.notee.domain.User;
import com.dariphion.notee.services.NoteService;
import com.dariphion.notee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    // Create a new note for a user
    @PostMapping("/user/{userId}")
    public ResponseEntity<Note> createNote(@PathVariable Long userId, @RequestBody Note note) {
        return userService.findById(userId)
                .map(user -> {
                    Note newNote = noteService.createNote(note, user);
                    return new ResponseEntity<>(newNote, HttpStatus.CREATED);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all notes for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Note>> getNotesByUser(@PathVariable Long userId) {
        return userService.findById(userId)
                .map(user -> {
                    List<Note> notes = noteService.findNotesByUser(user);
                    return new ResponseEntity<>(notes, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get a specific note by ID
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.findNoteById(id)
                .map(note -> new ResponseEntity<>(note, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a note
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        return noteService.findNoteById(id)
                .map(existingNote -> {
                    existingNote.setTitle(updatedNote.getTitle());
                    existingNote.setContent(updatedNote.getContent());
                    Note updated = noteService.updateNote(existingNote);
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a note by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Show a simple list of notes
    @GetMapping("/notes")
    public String getAllNotes(Model model) {
        List<Note> notes = noteService.findAllNotes();  // A service method that fetches notes
        model.addAttribute("notes", notes);
        return "notes";  // This refers to the Thymeleaf template notes.html
    }

}

