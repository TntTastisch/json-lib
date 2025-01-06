package de.tnttastisch.jsonlib.json.value;

import de.tnttastisch.jsonlib.json.tools.ValueType;

import java.math.BigDecimal;


public class JsonBigDecimal extends JsonNumber<BigDecimal> {

    public JsonBigDecimal(BigDecimal value) {
        super(value);
    }

    @Override
    public ValueType getType() {
        return ValueType.BIG_DECIMAL;
    }

}
