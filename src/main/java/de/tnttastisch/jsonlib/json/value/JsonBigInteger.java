package de.tnttastisch.jsonlib.json.value;

import de.tnttastisch.jsonlib.json.tools.ValueType;

import java.math.BigInteger;


public class JsonBigInteger extends JsonNumber<BigInteger> {

    public JsonBigInteger(BigInteger value) {
        super(value);
    }

    @Override
    public ValueType getType() {
        return ValueType.BIG_INTEGER;
    }

}
