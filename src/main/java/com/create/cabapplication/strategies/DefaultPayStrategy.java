package com.create.cabapplication.strategies;

public class DefaultPayStrategy implements PayStrategy{

    @Override
    public Double calculateAmount(Double distance) {

        return distance*4*1.2;
    }
}
