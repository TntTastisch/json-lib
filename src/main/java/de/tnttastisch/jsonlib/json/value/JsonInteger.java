package de.tnttastisch.jsonlib.json.value;

import de.tnttastisch.jsonlib.json.tools.ValueType;

public class JsonInteger extends JsonNumber<Integer> {

    public JsonInteger(Integer value) {
        super(value);
    }

    @Override
    public final ValueType getType() {
        return ValueType.INTEGER;
    }

}
