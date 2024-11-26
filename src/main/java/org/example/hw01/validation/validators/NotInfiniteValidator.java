package org.example.hw01.validation.validators;

public class NotInfiniteValidator implements DoubleValidator {

    @Override
    public boolean isValid(double value) {
        return !Double.isInfinite(value);
    }

    @Override
    public String getErrorDescription() {
        return "can't be infinite number";
    }
}
