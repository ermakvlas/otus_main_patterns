package org.example.hw01.validation;

public interface QuadraticEquationValidator {
    void validateQuadraticEquationCoefficients(double a, double b, double c);
    boolean isZero(double value);
    boolean isNegative(double value);
}
