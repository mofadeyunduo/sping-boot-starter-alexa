package per.piers.alexa.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import per.piers.alexa.oauth2.constrant.ClassConstants;
import per.piers.alexa.oauth2.constrant.Constants;
import per.piers.alexa.oauth2.model.AlexaRequest;
import per.piers.alexa.oauth2.model.Principal;
import per.piers.alexa.oauth2.model.Token;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequestMapping("/alexa")
@Controller
public class AmazonOAuth2AuthorizationCodeController {

    private static final String REQUEST_KEY = "request:%s";
    private static final String REQUEST_CODE = "code:%s";
    private static final String REQUEST_TOKEN = "user:%s:token";
    private static final String EXPIRES_IN = "3600";

    private final Constants constants = new ClassConstants();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/login/page")
    public void loginPage(@RequestParam String state, @RequestParam("client_id") String clientId, String scope, @RequestParam("response_type") String responseType, @RequestParam("redirect_uri") String redirectUri, HttpServletResponse response) throws IOException {
        if (!validLogin(state, clientId, scope, clientId, responseType)) {
            response.setStatus(401);
        }
        String request = UUID.randomUUID().toString();
        AlexaRequest login = AlexaRequest.builder()
                .state(state)
                .clientId(clientId)
                .scope(scope)
                .responseType(responseType)
                .redirectUri(redirectUri)
                .build();
        redisTemplate.opsForValue().set(String.format(REQUEST_KEY, request), login.toJson(), 600, TimeUnit.SECONDS);
        response.sendRedirect(getConstants().getLoginPage() + "?request=" + request);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestParam String request, @RequestBody Principal principal, HttpServletResponse response) throws IOException {
        if (!validUserNameAndPassword(principal)) {
            response.setStatus(401);
        }
        AlexaRequest login = AlexaRequest.fromJson(redisTemplate.opsForValue().get(String.format(REQUEST_KEY, request)));
        String code = UUID.randomUUID().toString();
        String state = login.getState();
        principal.setCode(code);
        redisTemplate.opsForValue().set(String.format(REQUEST_CODE, code), principal.toJson(), 600, TimeUnit.SECONDS);
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("state", state);
        map.put("redirect", login.getRedirectUri());
        return map;
    }

    @RequestMapping(value = "/accessToken")
    @ResponseBody
    public Map<String, String> accessToken(@RequestParam String code, HttpServletResponse response) {
        if (!validCode(code)) {
            response.setStatus(401);
        }

        String accessToken = UUID.randomUUID().toString();
        String expiresIn = EXPIRES_IN;
        String refreshToken = UUID.randomUUID().toString();
        Token token = Token.builder()
                .accessToken(accessToken)
                .expiresIn(expiresIn)
                .refreshToken(refreshToken)
                .build();
        Principal principal = Principal.fromJson(redisTemplate.opsForValue().get(String.format(REQUEST_CODE, code)));
        redisTemplate.opsForValue().set(String.format(REQUEST_TOKEN, principal.getUsername()), token.toJson());
        Map<String, String> map = new HashMap<>();
        map.put("access_token", accessToken);
        map.put("expires_in", expiresIn);
        map.put("refresh_token", refreshToken);
        return map;
    }

    protected boolean validLogin(String state, String clientId, String scope, String responseType, String redirectUri) {
        return Objects.equals(getConstants().getClientId(), clientId) && Objects.equals(getConstants().getResponseType(), responseType);
    }

    protected Constants getConstants() {
        return constants;
    }

    protected boolean validUserNameAndPassword(Principal principal) {
        return true;
    }

    private boolean validCode(String code) {
        return true;
    }

}
