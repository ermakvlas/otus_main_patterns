package org.example.hw01.validation;

import org.example.hw01.validation.validators.*;

import java.util.*;
import java.util.stream.Stream;

public class QuadraticEquationValidatorImpl implements QuadraticEquationValidator {

    private static final Set<String> COMMON_QUADRATIC_EQUATION_PARAMETERS_VALIDATORS_NAMES = Set.of(
            NotInfiniteValidator.class.getSimpleName(),
            NotNanValidator.class.getSimpleName()
    );

    private final Map<String, DoubleValidator> validators = new HashMap<>();

    public QuadraticEquationValidatorImpl() {
        addValidator(new NotInfiniteValidator());
        addValidator(new NotNanValidator());
        addValidator(new NegativeValidator());
        addValidator(new NonZeroValidator());
        addValidator(new ZeroValidator());
    }

    private void addValidator(DoubleValidator validator) {
        validators.putIfAbsent(validator.getClass().getSimpleName(), validator);
    }

    @Override
    public void validateQuadraticEquationCoefficients(double a, double b, double c) {
        validateCoefficients(a, b, c);
        validateCoefficientA(a);
    }

    @Override
    public boolean isZero(double value) {
        return validators.get(ZeroValidator.class.getSimpleName()).isValid(value);
    }

    @Override
    public boolean isNegative(double value) {
        return validators.get(NegativeValidator.class.getSimpleName()).isValid(value);
    }

    private void validateCoefficients(double a, double b, double c) {
        List<DoubleValidator> validatorList = validators.entrySet().stream()
                .filter(e -> COMMON_QUADRATIC_EQUATION_PARAMETERS_VALIDATORS_NAMES.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .toList();

        Stream.of(a, b, c).forEach(param -> validatorList.forEach(v -> {
            if (!v.isValid(param)) {
                throw new IllegalArgumentException(String.format("Invalid parameter: %s %s.", param, v.getErrorDescription()));
            }
        }));
    }

    private void validateCoefficientA(double a) {
        if (isZero(a)) {
            throw new IllegalArgumentException("Coefficient 'a' cannot be zero.");
        }
    }
}
