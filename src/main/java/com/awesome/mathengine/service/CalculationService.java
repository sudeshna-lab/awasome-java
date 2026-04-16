package com.awesome.mathengine.service;

import com.awesome.mathengine.core.Operation;
import com.awesome.mathengine.core.OperationFactory;
import com.awesome.mathengine.core.OperationType;
import com.awesome.mathengine.model.CalculationRequest;
import com.awesome.mathengine.model.CalculationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * Service class responsible for handling mathematical calculation operations.
 * Provides business logic for processing calculation requests and returning results.
 *
 * Implements layered architecture pattern and follows Spring best practices.
 *
 * @since 1.0.0
 */
@Service
@Validated
public class CalculationService {

    private static final Logger logger = LoggerFactory.getLogger(CalculationService.class);

    /**
     * Performs a calculation based on the provided request.
     * Handles validation, operation execution, and error handling.
     *
     * @param request the calculation request containing operation type and operands
     * @return a CalculationResponse with the result or error information
     * @throws NullPointerException if request is null
     */
    public CalculationResponse calculate(CalculationRequest request) {
        if (request == null) {
            logger.error("Calculation request cannot be null");
            throw new NullPointerException("Calculation request cannot be null");
        }

        long startTime = System.currentTimeMillis();

        try {
            logger.info("Starting calculation for operation: {}", request.getOperationType());

            // Validate request
            if (!request.isValid()) {
                logger.warn("Invalid calculation request: missing required fields");
                return CalculationResponse.error(
                    "Unknown",
                    "Invalid request: operation type and operands are required"
                );
            }

            // Create operation
            Operation operation = OperationFactory.createOperation(request.getOperationType());

            // Validate operand count
            if (request.getOperands().length != operation.getRequiredOperands()) {
                String errorMsg = String.format( 
                    "Operation %s requires %d operands, but got %d",
                    operation.getOperationName(),
                    operation.getRequiredOperands(),
                    request.getOperands().length
                );
                logger.warn(errorMsg);
                return CalculationResponse.error(operation.getOperationName(), errorMsg);
            }

            // Execute operation
            double result = operation.execute(request.getOperands());
            long executionTime = System.currentTimeMillis() - startTime;

            logger.info("Calculation completed successfully for operation: {} in {} ms",
                operation.getOperationName(), executionTime);

            return CalculationResponse.success(result, operation.getOperationName(), executionTime);

        } catch (ArithmeticException e) {
            logger.error("Arithmetic error during calculation", e);
            return CalculationResponse.error(
                request.getOperationType().getDisplayName(),
                "Arithmetic error: " + e.getMessage()
            );
        } catch (IllegalArgumentException e) {
            logger.error("Invalid argument for operation", e);
            return CalculationResponse.error(
                request.getOperationType().getDisplayName(),
                "Invalid argument: " + e.getMessage()
            );
        } catch (Exception e) {
            logger.error("Unexpected error during calculation", e);
            return CalculationResponse.error(
                request.getOperationType().getDisplayName(),
                "Unexpected error: " + e.getMessage()
            );
        }
    }

    /**
     * Gets information about a specific operation.
     *
     * @param operationName the name of the operation to look up
     * @return an Optional containing operation description if found
     */
    public Optional<String> getOperationInfo(String operationName) {
        try {
            OperationType type = OperationType.valueOf(operationName.toUpperCase());
            Operation operation = OperationFactory.createOperation(type);
            return Optional.of(operation.getDescription());
        } catch (IllegalArgumentException e) {
            logger.debug("Operation not found: {}", operationName);
            return Optional.empty();
        }
    }

    /**
     * Checks if a calculation is safe to execute (validation).
     *
     * @param request the calculation request to validate
     * @return true if the request can be executed, false otherwise
     */
    public boolean isCalculationValid(CalculationRequest request) {
        if (request == null || !request.isValid()) {
            return false;
        }

        try {
            Operation operation = OperationFactory.createOperation(request.getOperationType());
            return request.getOperands().length == operation.getRequiredOperands();
        } catch (Exception e) {
            logger.debug("Validation failed for calculation request", e);
            return false;
        }
    }
}