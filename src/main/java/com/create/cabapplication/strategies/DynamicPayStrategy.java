package com.create.cabapplication.strategies;

public class DynamicPayStrategy implements PayStrategy{
    @Override
    public Double calculateAmount(Double distance) {
        return distance*4*1.5;
    }
}
