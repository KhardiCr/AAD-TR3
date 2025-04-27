package com.tasknotes.tasknotes.requestwrappers;

import com.tasknotes.tasknotes.note.repository.Note;
import com.tasknotes.tasknotes.user.repository.User;

import lombok.Data;

@Data
public class UserNoteRequest {
    private User user;
    private Note note;
}
