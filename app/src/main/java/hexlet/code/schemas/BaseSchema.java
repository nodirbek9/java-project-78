package hexlet.code.schemas;

public abstract class BaseSchema<T> {

    private boolean required = false;

    public abstract boolean validate(T value);

    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    protected boolean isRequired() {
        return required;
    }

    public boolean isValid(T value) {
        if (value == null) {
            return !required;
        }
        return validate(value);
    }
}
