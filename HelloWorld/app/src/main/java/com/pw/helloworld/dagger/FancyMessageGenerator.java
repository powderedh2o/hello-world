package com.pw.helloworld.dagger;

public class FancyMessageGenerator implements MessageGenerator {

    private static final String MESSAGE = "Injected message: ";

    @Override
    public String generateMessage(Source source) {
        return MESSAGE + source;
    }
}
