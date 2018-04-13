package per.piers.alexa.oauth2.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Token {

    private String accessToken;
    private String expiresIn;
    private String refreshToken;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Token fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Token.class);
    }

}
