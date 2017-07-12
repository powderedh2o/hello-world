package com.pw.helloworld.dagger;

public interface MessageGenerator {

    String generateMessage(Source source);

    enum Source {

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
