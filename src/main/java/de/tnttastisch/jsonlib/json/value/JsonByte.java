package de.tnttastisch.jsonlib.json.value;


import de.tnttastisch.jsonlib.json.tools.ValueType;

public class JsonByte extends JsonNumber<Byte> {

    public JsonByte(Byte value) {
        super(value);
    }

    @Override
    public ValueType getType() {
        return ValueType.BYTE;
    }

}
