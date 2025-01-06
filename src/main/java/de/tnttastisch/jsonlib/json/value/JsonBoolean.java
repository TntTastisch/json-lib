package de.tnttastisch.jsonlib.json.value;


import de.tnttastisch.jsonlib.json.tools.ValueType;

public class JsonBoolean extends JsonSimple<Boolean> {

    public JsonBoolean(Boolean value) {
        super(value);
    }

    @Override
    public ValueType getType() {
        return ValueType.BOOLEAN;
    }

}
