package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {

    @Test
    void testForNull() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testWithRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testForLengthRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertFalse(schema.required().minLength(5).isValid(""));
        assertFalse(schema.required().minLength(5).isValid("abcd"));
        assertTrue(schema.required().minLength(5).isValid("abcde"));
    }

    @Test
    void testForLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.minLength(5).isValid(null));
        assertFalse(schema.minLength(5).isValid(""));
    }

    @Test
    void testForContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        StringSchema schema2 = v.string();

        assertTrue(schema.contains("Hello").contains("world").isValid("Hello world"));
        assertFalse(schema2.contains("Hello").contains("Hellow").isValid("Hello world"));
    }
    @Test
    void testForRequireContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertFalse(schema.required().contains("a").isValid(null));
        assertFalse(schema.required().contains("a").isValid(""));
    }
}
