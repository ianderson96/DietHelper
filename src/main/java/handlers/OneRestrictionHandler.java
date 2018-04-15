package handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;

import java.util.Map;
import java.util.Optional;

public class OneRestrictionHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("OneRestrictionHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {


        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();


        Slot foodSlot = slots.get("food");
        Slot dietSlot = slots.get("diet");
        Slot ingredientSlot = slots.get("ingredient");



        String speechText;
        if(foodSlot != null && dietSlot != null && ingredientSlot == null){
            //run food diet call
            String foodText = foodSlot.getValue();
            String dietText = dietSlot.getValue();
            speechText = String.format("The food is: "+foodText+" The diet is: "+dietText);
        }else if(foodSlot != null && ingredientSlot != null){
            //Check if food as ingredients
            String foodText = foodSlot.getValue();
            String ingredientText = ingredientSlot.getValue();
            speechText = String.format("The food is: "+foodText+" The ingredients are: "+ingredientText);
        }else{
            speechText = String.format("Sorry that was in an invalid request");
        }



        return input.getResponseBuilder()
                .withSpeech(speechText)
                .build();
    }

}