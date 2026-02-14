package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    @Test
    void testForNull() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null));
    }

    @Test
    void testWithRequired() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertFalse(schema.required().isValid(null));
    }

    @Test
    void testWithRequiredAndPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        schema.positive();
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testForPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        NumberSchema schema2 = v.number();

        assertFalse(schema.positive().isValid(-10));
        assertTrue(schema2.positive().isValid(5));
    }

    @Test
    void testForRange() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(4));
        assertTrue(schema.isValid(100));

        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
