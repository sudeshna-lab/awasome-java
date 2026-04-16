package com.awesome.mathengine.core;

public interface Operation {
    double execute(double... operands);
    String getOperationName();
    int getRequiredOperands();
    String getDescription();
}