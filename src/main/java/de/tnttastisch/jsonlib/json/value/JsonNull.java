package de.tnttastisch.jsonlib.json.value;

import de.tnttastisch.jsonlib.json.tools.JsonValue;
import de.tnttastisch.jsonlib.json.tools.ValueType;

public class JsonNull<E> extends JsonValue<E> {

    private static JsonNull<?> INSTANCE = new JsonNull<>();

    @SuppressWarnings("unchecked")
    public static <E> JsonNull<E> get() {
        return (JsonNull<E>) INSTANCE;
    }

    private JsonNull() {
    }

    @Override
    public ValueType getType() {
        return ValueType.NULL;
    }

    @Override
    public E getValue() {
        return null;
    }

}
