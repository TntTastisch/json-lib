package de.tnttastisch.jsonlib.json.io;

public class JsonSyntaxException extends RuntimeException {

    private static final long serialVersionUID = -461425017365081360L;

    public JsonSyntaxException() {
        super();
    }

    public JsonSyntaxException(String message) {
        super(message);
    }

    public JsonSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

}
