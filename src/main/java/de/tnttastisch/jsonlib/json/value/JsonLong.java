package de.tnttastisch.jsonlib.json.value;


import de.tnttastisch.jsonlib.json.tools.ValueType;

public class JsonLong extends JsonNumber<Long> {

    public JsonLong(Long value) {
        super(value);
    }

    @Override
    public final ValueType getType() {
        return ValueType.LONG;
    }

}
