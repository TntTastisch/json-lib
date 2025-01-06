package de.tnttastisch.jsonlib.json.value;

import de.tnttastisch.jsonlib.json.tools.JsonValue;

public abstract class JsonSimple<E> extends JsonValue<E> {

    protected final E value;

    public JsonSimple(E value) {
        this.value = value;
    }

    @Override
    public E getValue() {
        return value;
    }

}
