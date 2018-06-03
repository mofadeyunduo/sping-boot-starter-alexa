package com.petkit.alexa.intent.response.providerImpl;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.petkit.alexa.intent.response.provider.IntentResponseProvider;
import com.petkit.alexa.speechlet.AlexaException;
import org.springframework.stereotype.Service;

@Service("helloworld")
public class HelloWorldIntentProvider implements IntentResponseProvider {

    @Override
    public SpeechletResponse getSpeechletResponse(SpeechletRequestEnvelope<IntentRequest> env) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText("Hello World");
        return SpeechletResponse.newTellResponse(speech);
    }

    @Override
    public SpeechletResponse getAlexaExceptionResponse(SpeechletRequestEnvelope<IntentRequest> env, AlexaException e) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText("Hello Error");
        return SpeechletResponse.newTellResponse(speech);
    }

}