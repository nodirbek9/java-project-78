package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestSchema {

    @Test
    void testForNull() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        var result = schema.isValid("");
        var result2 = schema.isValid(null);

        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    void testWithRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();
        var result = schema.isValid("");
        var result2 = schema.isValid(null);

        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void testForLengthRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required().minLength(5).minLength(3);
        var result = schema.isValid("ff");
        var result2 = schema.isValid("sdfg");

        assertFalse(result);
        assertTrue(result2);
    }

    @Test
    void testForLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.minLength(5).minLength(3);
        var result = schema.isValid("ff");
        var result2 = schema.isValid("sdfg");

        assertFalse(result);
        assertTrue(result2);
    }

    @Test
    void testForContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        var result = schema.contains("Hello").isValid("Hello world");
        var result2 = schema.contains("Hellow").isValid("Hello world");

        assertTrue(result);
        assertFalse(result2);
    }

    @Test
    void testForAllMethods() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        var result = schema.minLength(10).minLength(4).isValid("Hex");
        var result2 = schema.required().minLength(10).minLength(4).isValid("Hello world");

        assertFalse(result);
        assertTrue(result2);
    }
}
