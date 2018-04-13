package per.piers.alexa.oauth2.constrant;

public abstract class Constants {

    private final String RESPONSE_TYPE = "code";

    public abstract String getClientId();

    public abstract String getLoginPage();

    public String getResponseType() {
        return RESPONSE_TYPE;
    }

}
