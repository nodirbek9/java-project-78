package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {
    private final Validator validator = new Validator();

    @Test
    void testForNull() {
        MapSchema schema = validator.map();
        var data = new HashMap<String, String>();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

    }

    @Test
    void testWithRequired() {
        MapSchema schema = validator.map();
        var data = new HashMap<String, String>();

        assertFalse(schema.required().isValid(null));
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testSizeOfForNull() {
        MapSchema schema = validator.map();
        schema.sizeOf(2);

        assertTrue(schema.isValid(null));
    }

    @Test
    void testWithSizeOf() {
        MapSchema schema = validator.map();
        var data = new HashMap<String, String>();
        schema.sizeOf(2);
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testRequiredAndSizeOf() {
        MapSchema schema = validator.map();
        var data = new HashMap<String, String>();
        schema.sizeOf(2);
        schema.required();
        assertFalse(schema.isValid(new HashMap<>()));
        assertFalse(schema.isValid(null));
        data.put("key1", "value1");
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testShapeWithRequired() {
        MapSchema schema = validator.map();

        Map<String, String> emptyMap = new HashMap<>();
        assertTrue(schema.isValid(emptyMap));

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        assertTrue(schema.isValid(human1));
        assertFalse(schema.isValid(human2));
    }

    @Test
    void testShapeWithAnotherName() {
        MapSchema schema = validator.map();

        Map<String, BaseSchema<? >> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");

        assertFalse(schema.isValid(human3));
    }
}
