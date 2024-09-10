package com.dariphion.notee.repositories;

import com.dariphion.notee.domain.Note;
import com.dariphion.notee.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    // Find all notes for a specific user
    List<Note> findByUser(User user);

    // Find notes by title for a specific user
    List<Note> findByUserAndTitleContaining(User user, String keyword);
}