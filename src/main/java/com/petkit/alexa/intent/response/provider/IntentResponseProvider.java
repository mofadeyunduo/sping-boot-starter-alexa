package com.petkit.alexa.intent.response.provider;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.petkit.alexa.speechlet.AlexaException;

public interface IntentResponseProvider {

    SpeechletResponse getSpeechletResponse(SpeechletRequestEnvelope<IntentRequest> env) throws AlexaException;

    SpeechletResponse getAlexaExceptionResponse(SpeechletRequestEnvelope<IntentRequest> env, AlexaException e);

}