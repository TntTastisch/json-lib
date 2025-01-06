package de.tnttastisch.jsonlib.json.value;

import de.tnttastisch.jsonlib.json.tools.ValueType;

public class JsonString extends JsonSimple<String> {

    public JsonString(String value) {
        super(value);
    }

    @Override
    public final ValueType getType() {
        return ValueType.STRING;
    }

}
