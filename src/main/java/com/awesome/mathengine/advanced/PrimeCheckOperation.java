package com.awesome.mathengine.advanced;

import com.awesome.mathengine.core.Operation;

/**
 * Implementation of prime number check operation.
 * Determines whether a given number is prime.
 * A prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself.
 *
 * @since 1.0.0
 */
public class PrimeCheckOperation implements Operation {

    @Override
    public double execute(double... operands) {
        if (operands == null || operands.length != getRequiredOperands()) {
            throw new IllegalArgumentException(
                String.format("Prime check requires exactly %d operand, got %d",
                    getRequiredOperands(),
                    operands == null ? 0 : operands.length)
            );
        }

        long number = (long) operands[0];

        if (number < 0) {
            throw new IllegalArgumentException("Prime check is not defined for negative numbers");
        }

        return isPrime(number) ? 1.0 : 0.0;
    }

    /**
     * Determines if a number is prime using optimized trial division.
     *
     * @param number the number to check
     * @return true if prime, false otherwise
     */
    private boolean isPrime(long number) {
        if (number < 2) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }

        for (long i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getOperationName() {
        return "Prime Check";
    }

    @Override
    public int getRequiredOperands() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Checks if a number is prime. Returns 1.0 if prime, 0.0 if not prime";
    }
}