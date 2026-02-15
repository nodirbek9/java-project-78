package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {

    @Test
    void testForNull() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        var data = new HashMap<String, String>();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

    }

    @Test
    void testWithRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        var data = new HashMap<String, String>();

        assertFalse(schema.required().isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testSizeOfForNull() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.sizeof(2);

        assertTrue(schema.isValid(null));
    }

    @Test
    void testWithSizeOf() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        var data = new HashMap<String, String>();
        schema.sizeof(2);
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testRequiredAndSizeOf() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        var data = new HashMap<String, String>();
        schema.sizeof(2);
        schema.required();
        assertFalse(schema.isValid(new HashMap<>()));
        assertFalse(schema.isValid(null));
        data.put("key1", "value1");
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }
}
