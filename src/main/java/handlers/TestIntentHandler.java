package handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;
import java.util.Properties;

public class TestIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("TestIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
      String speechText = "Hello world: you are in the test intent";

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .build();
    }

}