package hexlet.code.schemas;
/**
 * Base abstract schema class for value validation.
 *
 * @param <T> the type of value to validate
 */
public abstract class BaseSchema<T> {

    private boolean required = false;

    /**
     * Validates the given value according to schema rules.
     *
     * @param value value to validate
     * @return true if value is valid, false otherwise
     */
    public abstract boolean validate(T value);

    /**
     * Marks the value as required (null is not allowed).
     *
     * @return current schema instance
     */
    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    protected final boolean isRequired() {
        return required;
    }
    /**
     * Checks whether the given value is valid.
     *
     * @param value value to validate
     * @return true if value is valid, false otherwise
     */
    public boolean isValid(T value) {
        if (value == null) {
            return !required;
        }
        return validate(value);
    }
}
