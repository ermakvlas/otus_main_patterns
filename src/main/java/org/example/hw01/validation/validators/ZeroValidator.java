package org.example.hw01.validation.validators;

public class ZeroValidator implements DoubleValidator {

    @Override
    public boolean isValid(double value) {
        return Math.abs(value) <= EPSILON;
    }

    @Override
    public String getErrorDescription() {
        return "must be zero";
    }
}
