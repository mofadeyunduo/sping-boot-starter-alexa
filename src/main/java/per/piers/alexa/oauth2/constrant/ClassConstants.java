package per.piers.alexa.oauth2.constrant;

public class ClassConstants extends Constants {

    private static final String CLIENT_ID = "alexa";
    private static final String LOGIN_PAGE = "/login.html";

    @Override
    public String getClientId() {
        return CLIENT_ID;
    }

    @Override
    public String getLoginPage() {
        return LOGIN_PAGE;
    }

}
