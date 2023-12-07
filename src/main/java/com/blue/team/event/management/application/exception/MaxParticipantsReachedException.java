package com.blue.team.event.management.application.exception;

public class MaxParticipantsReachedException extends RuntimeException {
    public MaxParticipantsReachedException() {
        super("Maximum number of participants are already registered.");
    }

}
