package org.example.hw01.validation.validators;

public class NonZeroValidator implements DoubleValidator {

    @Override
    public boolean isValid(double value) {
        return Math.abs(value) > EPSILON;
    }

    @Override
    public String getErrorDescription() {
        return "must not be zero";
    }
}
