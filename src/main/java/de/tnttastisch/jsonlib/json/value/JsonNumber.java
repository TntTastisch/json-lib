package de.tnttastisch.jsonlib.json.value;

public abstract class JsonNumber<E extends Number> extends JsonSimple<E> {

    public JsonNumber(E value) {
        super(value);
    }

}
