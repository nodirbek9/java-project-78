package hexlet.code.schemas;
/** This class is abstract class and man class for all a */
public abstract class BaseSchema<T> {

    private boolean required = false;

    public abstract boolean validate(T value);

    /** required method is allowing is in other classes and disable null value */
    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    protected boolean isRequired() {
        return required;
    }
    /** This valid method checking the given value is Valid or not */
    public boolean isValid(T value) {
        if (value == null) {
            return !required;
        }
        return validate(value);
    }
}
