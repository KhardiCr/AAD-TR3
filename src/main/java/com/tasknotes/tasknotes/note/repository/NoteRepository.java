package com.tasknotes.tasknotes.note.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser_id(Long id);
}


