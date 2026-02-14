package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positiveNum = false;
    private Integer minRange = null;
    private Integer maxRange = null;

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    @Override
    public boolean validate(Integer value) {
        if (positiveNum && value <= 0) {
            return false;
        }
        if (minRange != null && maxRange != null) {
            if (!(value >= minRange && value <= maxRange)) {
                return false;
            }
        }
        return true;
    }

    public NumberSchema positive() {
        this.positiveNum = true;
        return this;
    }

    public NumberSchema range(Integer minRange, Integer maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        return this;
    }
}
