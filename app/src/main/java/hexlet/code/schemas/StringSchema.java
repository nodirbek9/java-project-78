package hexlet.code.schemas;

public class StringSchema {
    private boolean required = false;
    private Integer minLength = null;
    private String containsValue = null;


    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsValue = substring;
        return this;
    }

    public boolean isValid(String value) {
        if (value == null || value.isEmpty()) {
            if (!required) {
                return true;
            } else {
                return false;
            }
        }
        if (minLength != null && value != null) {
            if (minLength > value.length()) {
                return false;
            } else if (value.length() >= minLength) {
                return true;
            } else {
                return false;
            }
        }
        if (containsValue == null && required) {
            return true;
        }
        if (value != null && !value.isEmpty()) {
            if (value.contains(this.containsValue)) {
                return true;
            }
        }
        return false;
    }
}
