package com.petkit.alexa.speechlet;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DefaultSpeechlet extends AbstractSpeechlet {

    private static final List<String> ignoreIntentNames = Arrays.asList("AMAZON.CancelIntent", "AMAZON.FallbackIntent", "AMAZON.HelpIntent", "AMAZON.StopIntent");

    @Override
    protected SpeechletResponse unknownIntentResponse(SpeechletRequestEnvelope<IntentRequest> env) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText("Sorry");
        return SpeechletResponse.newTellResponse(speech);
    }

    @Override
    protected SpeechletResponse commonErrorResponse(SpeechletRequestEnvelope<IntentRequest> env) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText("Sorry");
        return SpeechletResponse.newTellResponse(speech);
    }

    @Override
    protected boolean neededLinkAccount() {
        return true;
    }

    @Override
    protected boolean isCurrentIntentNeededLinkingAccount(String currentIntent) {
        return ignoreIntentNames.contains(currentIntent);
    }

    @Override
    protected SpeechletResponse unlinkResponse() {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText("Unlink");
        return SpeechletResponse.newTellResponse(speech);
    }

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> speechletRequestEnvelope) {
    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> speechletRequestEnvelope) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText("Welcome");
        return SpeechletResponse.newTellResponse(speech);
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> speechletRequestEnvelope) {
    }

}