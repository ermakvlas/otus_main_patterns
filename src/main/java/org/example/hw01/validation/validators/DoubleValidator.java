package org.example.hw01.validation.validators;

public interface DoubleValidator {

    double EPSILON = 1e-6;

    boolean isValid(double value);
    String getErrorDescription();
}
