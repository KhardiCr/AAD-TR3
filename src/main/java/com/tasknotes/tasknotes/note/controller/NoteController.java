package com.tasknotes.tasknotes.note.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasknotes.tasknotes.note.repository.Note;
import com.tasknotes.tasknotes.note.service.NoteServices;
import com.tasknotes.tasknotes.requestwrappers.UserNoteRequest;
import com.tasknotes.tasknotes.user.repository.User;
import com.tasknotes.tasknotes.user.service.UserServices;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteServices noteServices;
    
    @Autowired
    private UserServices userServices;

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Note>> getAll(@PathVariable Long id){
        return new ResponseEntity<>(noteServices.getAllNotesByUser_id(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable Long id, @RequestBody UserNoteRequest userNoteRequest){
        
        Optional<Note> note = noteServices.findById(id);
        
        if (!note.isEmpty() && note.get().getUser().getUsername().equals(userNoteRequest.getUser().getUsername())) {
            return new ResponseEntity<>(note.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/new")
    public ResponseEntity<HttpStatus> addNote(@RequestBody UserNoteRequest userNoteRequest){
        
        Optional<User> userOptional = userServices.findByUsername(userNoteRequest.getUser().getUsername());

        if (userOptional.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else{

            System.out.println(userNoteRequest.getUser().getUsername());
            System.out.println(userOptional.get().getId());
            Note note = userNoteRequest.getNote();
            note.setUser(userOptional.get());
            noteServices.addNote(note);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> modNote(@RequestBody Note newNote){
        
        Optional<Note> note = noteServices.findById(newNote.getId());

        if (note.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            note.get().setTitle(newNote.getTitle());
            note.get().setContent(newNote.getContent());
            noteServices.addNote(note.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

}

