package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

    /**
     * Base abstract schema class for value validation.
     *
     * @param <T> the type of value to validate
     */
public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean requiredS = false;
    /**
     * Marks the value as required (null is not allowed).
     *
     * @return current schema instance
     */
    protected BaseSchema<T> required() {
        requiredS = true;
        return this;
    }

    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }
    /**
     * Checks whether the given value is valid.
     *
     * @param value value to validate
     * @return true if value is valid, false otherwise
     */
    public final boolean isValid(T value) {
        if (value == null) {
            return !requiredS;
        }

        for (Predicate<T> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }

        return true;
    }
}
