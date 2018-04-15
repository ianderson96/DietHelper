package handlers;

import api.ApiHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OneRestrictionPluralHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("OneRestrictionPluralHandler"));
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

        System.out.println("foodslot: "+foodSlot);
        System.out.println("dietSlot: "+dietSlot);
        System.out.println("ingredientSlot: "+ingredientSlot);

        String speechText;
        String parsedDietText;
        if(foodSlot.getValue() != null && dietSlot.getValue() != null){
            //run food diet call
            String foodText = foodSlot.getValue();
            String dietText = dietSlot.getValue();
            String foodTitle = api.getProductName(foodText);
//            speechText = String.format("The food is: "+foodText+" The diet is: "+dietText);
            if (this.parseUserDiet(dietText) != null) {
                parsedDietText = this.parseUserDiet(dietText);
                System.out.println("The parsed diet text is: " + parsedDietText);
                Boolean bool = api.containsBadge(foodText, parsedDietText);
                if (bool) {
                    speechText = String.format("Yes, I found that "+foodTitle+" are "+dietText);

                }
                else {
                    speechText = String.format("No, I found that "+foodTitle+" are not "+dietText);

                }
            }
            else {
                speechText = String.format("I do not understand "+dietText);
            }
        }else{
            speechText = String.format("Sorry, that was in an invalid request");
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .build();
    }

    private String parseUserDiet(String diet) {
        Map<String, String> dietMap = new HashMap<String, String>();
        dietMap.put("dairy free","dairy_free");
        dietMap.put("gluten free","gluten_free");
        dietMap.put("nut free","nut_free");
        dietMap.put("seafood free","seafood_free");
        dietMap.put("shellfish free","shellfish_free");
        dietMap.put("wheat free","wheat_free");
        dietMap.put("peanut free","peanut_free");
        dietMap.put("soy free","soy_free");
        dietMap.put("grain free","grain_free");
        dietMap.put("sesame free","sesame_free");
        dietMap.put("keto","ketogenic");
        dietMap.put("paleo","paleo");
        dietMap.put("vegan","vegan");
        dietMap.put("vegetarian","vegetarian");
        dietMap.put("primal","primal");
        dietMap.put("whole thirty","whole_30");
        dietMap.put("whole 30","whole_30");
        if (dietMap.containsKey(diet)) {
            return dietMap.get(diet);
        } else {
            return null;
        }
    }

}