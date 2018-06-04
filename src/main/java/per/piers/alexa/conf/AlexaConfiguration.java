package per.piers.alexa.conf;

import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import per.piers.alexa.speechlet.AbstractSpeechlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlexaConfiguration {

    @Bean
    public ServletRegistrationBean registration(AbstractSpeechlet speechlet) {
        SpeechletServlet speechletServlet = new SpeechletServlet();
        speechletServlet.setSpeechlet(speechlet);
        return new ServletRegistrationBean<>(speechletServlet, "/api/alxea");
    }

}