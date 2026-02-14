package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private Integer minLength = null;
    private String containsValue = null;

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }

    @Override
    public boolean validate(String value) {
        if (isRequired() && value.isEmpty()) {
            return false;
        }
        if (minLength != null && value.length() < minLength) {
            return false;
        }
        if (containsValue != null && !value.contains(containsValue)) {
            return false;
        }
        return true;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsValue = substring;
        return this;
    }
}
