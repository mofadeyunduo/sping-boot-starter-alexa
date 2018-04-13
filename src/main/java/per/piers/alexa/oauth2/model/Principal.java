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
public class Principal {

    private String username;
    private String password;
    private String code;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Principal fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Principal.class);
    }

}
