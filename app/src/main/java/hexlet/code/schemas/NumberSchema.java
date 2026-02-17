package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        super.required();
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> notNull = Objects::isNull;
        addCheck("positive", notNull.or(value -> value > 0));
        return this;
    }

    public NumberSchema range(Integer minRange, Integer maxRange) {
        addCheck("range", value -> value >= minRange && value <= maxRange);
        return this;
    }
}
