package handlers;

import api.ApiHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IngredientHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("IngredientHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {


        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        ApiHandler api = new ApiHandler();


        Slot foodSlot = slots.get("food");
        Slot dietSlot = slots.get("diet");
        Slot ingredientSlot = slots.get("ingredient");

        System.out.println("foodslot: " + foodSlot);
        System.out.println("dietSlot: " + dietSlot);
        System.out.println("ingredientSlot: " + ingredientSlot);

        String speechText;
        String parsedDietText;
        if (foodSlot.getValue() != null && ingredientSlot.getValue() != null) {
            //Check if food as ingredients
            String foodText = foodSlot.getValue();
            String ingredientText = ingredientSlot.getValue();
            Boolean bool = api.containsIngredient(foodText, ingredientText);
            if (bool) {
                speechText = String.format("Yes, " + foodText + " does contain " + ingredientText);
            }
            else {
                speechText = String.format("No, " + foodText + " doesn't contain " + ingredientText);
            }
        }
        else{
            speechText = String.format("Sorry, that was in an invalid request");
        }
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .build();
    }


}