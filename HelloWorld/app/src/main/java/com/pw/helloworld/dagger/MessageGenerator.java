package com.pw.helloworld.dagger;

import javax.inject.Inject;

class MessageGenerator {

    private static final String MESSAGE = "Injected message";

    @Inject
    public MessageGenerator() {
    }

    public String generateMessage() {
        return MESSAGE;
    }
}
