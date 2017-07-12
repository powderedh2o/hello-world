package com.pw.helloworld.dagger;

import javax.inject.Inject;

class MessageGenerator {

    private static final String MESSAGE = "Injected message: ";

    @Inject
    public MessageGenerator() {
    }

    public String generateMessage(Source source) {
        return MESSAGE + source;
    }

    public enum Source {

        ACTIVITY("From Activity"),
        FRAGMENT("From Fragment");

        private String source;

        Source(String source) {
            this.source = source;
        }

        @Override
        public String toString() {
            return source;
        }
    }
}
