
import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;

import com.amazon.ask.Skills;
import handlers.*;


public class DietHelperStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(new CancelandStopIntentHandler(), new OneRestrictionHandler(), new HelpIntentHandler(), new LaunchRequestHandler(), new SessionEndedRequestHandler(), new TestIntentHandler()).withSkillId("amzn1.ask.skill.51ecd59b-980d-441d-8162-67f511ad2481")
                .build();
    }

    public DietHelperStreamHandler() {
        super(getSkill());
    }

}