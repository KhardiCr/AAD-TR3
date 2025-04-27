package com.tasknotes.tasknotes.note.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasknotes.tasknotes.note.repository.Note;
import com.tasknotes.tasknotes.note.repository.NoteRepository;


@Service
public class NoteServicesImp implements NoteServices{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> getAllNotesByUser_id(Long id) {
        return noteRepository.findByUser_id(id);
    }

    @Override
    public void addNote(Note note) {
        noteRepository.save(note);    
    }

    @Override
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }
    
}
