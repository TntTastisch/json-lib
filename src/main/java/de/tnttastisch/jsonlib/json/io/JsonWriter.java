package de.tnttastisch.jsonlib.json.io;

import de.tnttastisch.jsonlib.io.TextSerializer;
import de.tnttastisch.jsonlib.json.tools.JsonArray;
import de.tnttastisch.jsonlib.json.tools.JsonEntry;
import de.tnttastisch.jsonlib.json.tools.JsonObject;
import de.tnttastisch.jsonlib.json.tools.JsonValue;
import de.tnttastisch.jsonlib.json.value.JsonBoolean;
import de.tnttastisch.jsonlib.json.value.JsonNull;
import de.tnttastisch.jsonlib.json.value.JsonNumber;
import de.tnttastisch.jsonlib.json.value.JsonString;

import java.io.IOException;
import java.io.Writer;

public class JsonWriter implements TextSerializer<JsonValue<?>> {

    private static final String[] ESCAPE_CHARS;

    static {
        ESCAPE_CHARS = new String[128];
        ESCAPE_CHARS['\b'] = "\\b";
        ESCAPE_CHARS['\f'] = "\\f";
        ESCAPE_CHARS['\n'] = "\\n";
        ESCAPE_CHARS['\r'] = "\\r";
        ESCAPE_CHARS['\t'] = "\\t";
        ESCAPE_CHARS['\"'] = "\\\"";
        ESCAPE_CHARS['\\'] = "\\\\";
        for (int code = 0; code < 31; code++) {
            ESCAPE_CHARS[code] = String.format("\\u%04x", code);
        }
    }

    public static final int TAB_SPACES = 4;

    private boolean pretty = false;
    private boolean spaces = false;
    private int indent = 1;

    public boolean isPretty() {
        return pretty;
    }

    public JsonWriter setPretty(boolean pretty) {
        this.pretty = pretty;
        return this;
    }

    public boolean usesSpaces() {
        return spaces;
    }

    public JsonWriter setSpaces(boolean spaces) {
        this.spaces = spaces;
        return this;
    }

    public int getIndent() {
        return indent;
    }

    public JsonWriter setIndent(int indent) {
        this.indent = indent;
        return this;
    }

    public JsonWriter setTabIndent(int indent) {
        this.indent = indent * TAB_SPACES;
        return this;
    }

    @Override
    public void toWriter(JsonValue<?> object, Writer writer) throws IOException {
        writeValue(object, writer, 0);
    }

    public void writeEntry(JsonEntry<?> entry, Writer writer, int depth) throws IOException {
        if (pretty) {
            indent(writer, depth);
        }
        writeStringObject(entry.getKey(), writer);
        writer.append(':');
        if (pretty) {
            writer.append(' ');
        }
        writeValue(entry.getValue(), writer, depth);
    }

    public void writeValue(JsonValue<?> value, Writer writer, int depth) throws IOException {
        switch (value.getType()) {
            case NULL:
                writeNull((JsonNull<?>) value, writer);
                break;
            case ARRAY:
                writeArray((JsonArray) value, writer, depth);
                break;
            case OBJECT:
                writeObject((JsonObject) value, writer, depth);
                break;
            case STRING:
                writeString((JsonString) value, writer);
                break;
            case BOOLEAN:
                writeBoolean((JsonBoolean) value, writer);
                break;
            case BYTE:
            case SHORT:
            case INTEGER:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case BIG_INTEGER:
            case BIG_DECIMAL:
                writeNumber((JsonNumber<?>) value, writer);
                break;
            case NUMBER:
                break;
            default:
                break;
        }
    }

    public void writeObject(JsonObject object, Writer writer, int depth) throws IOException {
        writer.append('{');
        int size = object.size();
        if (size != 0) {
            if (pretty) {
                writer.append('\n');
            }
            int current = 0;
            int deep = depth + 1;
            for (JsonEntry<?> entry : object) {
                writeEntry(entry, writer, deep);
                if (++current != size) {
                    writer.append(',');
                }
                if (pretty) {
                    writer.append('\n');
                }
            }
            if (pretty) {
                indent(writer, depth);
            }
        }
        writer.append('}');
    }

    public void writeArray(JsonArray array, Writer writer, int depth) throws IOException {
        writer.append('[');
        int size = array.size();
        if (size != 0) {
            if (pretty) {
                writer.append('\n');
            }
            int current = 0;
            int deep = depth + 1;
            for (JsonValue<?> value : array) {
                if (pretty) {
                    indent(writer, deep);
                }
                writeValue(value, writer, deep);
                if (++current != size) {
                    writer.append(',');
                }
                if (pretty) {
                    writer.append('\n');
                }
            }
            if (pretty) {
                indent(writer, depth);
            }
        }
        writer.append(']');
    }

    public void writeString(JsonString string, Writer writer) throws IOException {
        writeStringObject(string.getValue(), writer);
    }

    public void writeNumber(JsonNumber<?> number, Writer writer) throws IOException {
        writer.append(number.getValue().toString());
    }

    public void writeNull(JsonNull<?> jsonNull, Writer writer) throws IOException {
        writer.append("null");
    }

    public void writeBoolean(JsonBoolean jsonBoolean, Writer writer) throws IOException {
        writer.append(jsonBoolean.getValue().toString());
    }

    /*
     * Helpers
     */

    private void indent(Writer writer, int depth) throws IOException {
        int amount = indent * depth;
        char append = spaces ? ' ' : '\t';
        for (int count = 0; count < amount; count++) {
            writer.append(append);
        }
    }

    private void writeStringObject(String string, Writer writer) throws IOException {
        char[] array = string.toCharArray();
        StringBuilder builder = new StringBuilder("\"");
        for (int index = 0; index < array.length; index++) {
            char character = array[index];
            if (character < 128) {
                String escaped = ESCAPE_CHARS[character];
                if (escaped != null) {
                    builder.append(escaped);
                    continue;
                }
            }
            if (character == '\u2028') {
                builder.append("\\u2028");
                continue;
            }
            if (character == '\u2029') {
                builder.append("\\u2029");
                continue;
            }
            builder.append(character);
        }
        writer.append(builder.append('"').toString());
    }

}
