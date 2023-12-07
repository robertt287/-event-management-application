package com.blue.team.event.management.application.exception;

public class MaxParticipantsException extends Exception {
    public MaxParticipantsException () {
        super("Maximum number of participants are already registered.");
    }

}
