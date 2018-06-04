package per.piers.alexa.speechlet;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlexaException extends RuntimeException {

    private int code;
    private String msg;

    public AlexaException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}