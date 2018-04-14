
import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;

import com.amazon.ask.Skills;
import handlers.CancelandStopIntentHandler;
import handlers.OneRestrictionHandler;
import handlers.HelpIntentHandler;
import handlers.SessionEndedRequestHandler;
import handlers.LaunchRequestHandler;



public class DietHelperStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(new CancelandStopIntentHandler(), new OneRestrictionHandler(), new HelpIntentHandler(), new LaunchRequestHandler(), new SessionEndedRequestHandler())
                .build();
    }

    public DietHelperStreamHandler() {
        super(getSkill());
    }

}