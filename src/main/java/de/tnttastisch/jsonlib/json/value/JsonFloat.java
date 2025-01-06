package de.tnttastisch.jsonlib.json.value;


import de.tnttastisch.jsonlib.json.tools.ValueType;

public class JsonFloat extends JsonNumber<Float> {

    public JsonFloat(Float value) {
        super(value);
    }

    @Override
    public ValueType getType() {
        return ValueType.FLOAT;
    }

}
