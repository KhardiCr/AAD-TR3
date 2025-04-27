package com.tasknotes.tasknotes.note.service;

import java.util.List;
import java.util.Optional;

import com.tasknotes.tasknotes.note.repository.Note;

public interface NoteServices {
    
    List<Note> getAllNotes();

    List<Note> getAllNotesByUser_id(Long userid);

    Optional<Note> findById(Long id);

    void addNote(Note note);
}
