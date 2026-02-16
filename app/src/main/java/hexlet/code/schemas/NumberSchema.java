package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    private boolean positiveNum = false;
    private Integer givenMinRange = null;
    private Integer givenMaxRange = null;

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
        if (givenMinRange != null && givenMaxRange != null) {
            if (!(value >= givenMinRange && value <= givenMaxRange)) {
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
        this.givenMinRange = minRange;
        this.givenMaxRange = maxRange;
        return this;
    }
}
