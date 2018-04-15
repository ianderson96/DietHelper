package handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
//import com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class DisclaimerHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "By using this product you agree that we are not responsible for any allergic reactions, health problems, or unwanted consumption that may happen. There may be inaccuracies that arise when looking up product ingredients. You should always look at the ingredients listed on the food you are about to eat.";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .withReprompt(speechText)
                .build();
    }
}
