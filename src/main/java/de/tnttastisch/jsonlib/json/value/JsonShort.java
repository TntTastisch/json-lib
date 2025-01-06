package de.tnttastisch.jsonlib.json.value;

import de.tnttastisch.jsonlib.json.tools.ValueType;

public class JsonShort extends JsonNumber<Short> {

    public JsonShort(Short value) {
        super(value);
    }

    @Override
    public final ValueType getType() {
        return ValueType.SHORT;
    }

}
