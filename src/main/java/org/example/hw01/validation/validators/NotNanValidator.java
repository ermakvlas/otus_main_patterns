package org.example.hw01.validation.validators;

public class NotNanValidator implements DoubleValidator {

    @Override
    public boolean isValid(double value) {
        return !Double.isNaN(value);
    }

    @Override
    public String getErrorDescription() {
        return "can't be NaN";
    }
}
