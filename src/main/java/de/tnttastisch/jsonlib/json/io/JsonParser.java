package de.tnttastisch.jsonlib.json.io;

import de.tnttastisch.jsonlib.io.TextDeserializer;
import de.tnttastisch.jsonlib.json.tools.JsonArray;
import de.tnttastisch.jsonlib.json.tools.JsonObject;
import de.tnttastisch.jsonlib.json.tools.JsonValue;
import de.tnttastisch.jsonlib.json.value.*;

import java.io.IOException;
import java.io.Reader;


public class JsonParser implements TextDeserializer<JsonValue<?>> {

    @Override
    public JsonValue<?> fromReader(Reader reader) throws IOException, JsonSyntaxException {
        return read(new JsonReader(reader));
    }

    protected JsonValue<?> read(JsonReader reader) throws IOException, JsonSyntaxException {
        JsonToken token = reader.next();
        switch (token) {
            case NULL:
                reader.readNull();
                return JsonNull.get();
            case BOOLEAN:
                return new JsonBoolean(reader.readBoolean());
            case STRING:
                return new JsonString(reader.readString());
            case START_ARRAY:
                JsonArray array = new JsonArray();
                reader.beginArray();
                while (reader.hasNext()) {
                    array.add(read(reader));
                }
                reader.endArray();
                return array;
            case START_OBJECT:
                JsonObject object = new JsonObject();
                reader.beginObject();
                while (reader.hasNext()) {
                    object.set(reader.readName(), read(reader));
                }
                reader.endObject();
                return object;
            case BYTE:
                return new JsonByte(reader.readByte());
            case SHORT:
                return new JsonShort(reader.readShort());
            case INTEGER:
                return new JsonInteger(reader.readInteger());
            case LONG:
                return new JsonLong(reader.readLong());
            case BIG_INTEGER:
                return new JsonBigInteger(reader.readBigInteger());
            case FLOAT:
                return new JsonFloat(reader.readFloat());
            case DOUBLE:
                return new JsonDouble(reader.readDouble());
            case BIG_DECIMAL:
                return new JsonBigDecimal(reader.readBigDecimal());
            case EOF:
            case KEY:
            case NUMBER:
            case END_ARRAY:
            case END_OBJECT:
            default:
                throw new IllegalArgumentException();
        }
    }

}
