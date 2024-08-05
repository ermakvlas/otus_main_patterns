package org.example.hw01.validation.validators;

public class NegativeValidator implements DoubleValidator {

    @Override
    public boolean isValid(double value) {
        return value < -EPSILON;
    }

    @Override
    public String getErrorDescription() {
        return "must be negative number";
    }
}
