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
public class AlexaRequest {

    private String state;
    private String clientId;
    private String redirectUri;
    private String responseType;
    private String scope;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static AlexaRequest fromJson(String json) {
        return new Gson().fromJson(json, AlexaRequest.class);
    }

}
