package per.piers.alexa;

import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import per.piers.alexa.helloworld.HelloWorldSpeechlet;

@Configuration
public class AlexaConfiguration {

    @Bean
    public ServletRegistrationBean registration() {
        SpeechletServlet speechletServlet = new SpeechletServlet();
        speechletServlet.setSpeechlet(new HelloWorldSpeechlet());
        return new ServletRegistrationBean(speechletServlet, "/alexa");
    }

}
