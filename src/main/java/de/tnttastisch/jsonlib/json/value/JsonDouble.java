package de.tnttastisch.jsonlib.json.value;


import de.tnttastisch.jsonlib.json.tools.ValueType;

public class JsonDouble extends JsonNumber<Double> {

    public JsonDouble(Double value) {
        super(value);
    }

    @Override
    public final ValueType getType() {
        return ValueType.DOUBLE;
    }

}
