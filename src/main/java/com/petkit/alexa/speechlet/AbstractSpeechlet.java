package com.petkit.alexa.speechlet;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.petkit.alexa.intent.response.provider.IntentResponseProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.util.StringUtils;

public abstract class AbstractSpeechlet extends ApplicationObjectSupport implements SpeechletV2 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> env) {
        String intentName = env.getRequest().getIntent().getName();

        try {
            if (StringUtils.isEmpty(env.getSession().getUser().getAccessToken()) && neededLinkAccount() && !isCurrentIntentNeededLinkingAccount(intentName)) {
                return unlinkResponse();
            }
        } catch (Exception e) {
            logger.error("unknown exception", e);
            return commonErrorResponse(env);
        }

        IntentResponseProvider provider;
        try {
            ApplicationContext context = getApplicationContext();
            provider = context.getBean(intentName, IntentResponseProvider.class);
            logger.info("get provider, {}", provider.getClass().getName());
        } catch (BeansException e) {
            logger.error("provider not found, will call unknownIntentResponse", e);
            return unknownIntentResponse(env);
        } catch (Exception e) {
            logger.error("unknown exception", e);
            return commonErrorResponse(env);
        }

        try {
            return provider.getSpeechletResponse(env);
        } catch (AlexaException e) {
            logger.error("handler alexa exception", e);
            return provider.getAlexaExceptionResponse(env, e);
        } catch (Exception e) {
            logger.error("unknown exception", e);
            return commonErrorResponse(env);
        }
    }

    protected abstract SpeechletResponse unknownIntentResponse(SpeechletRequestEnvelope<IntentRequest> env);

    protected abstract SpeechletResponse commonErrorResponse(SpeechletRequestEnvelope<IntentRequest> env);

    protected abstract boolean neededLinkAccount();

    protected abstract boolean isCurrentIntentNeededLinkingAccount(String currentIntent);

    protected abstract SpeechletResponse unlinkResponse();

}