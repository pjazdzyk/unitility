package com.synerset.unitsystem.kinematicviscosity;

public sealed interface KinematicViscosity permits SquareInchPerSecond, SquareMeterPerSecond {

    double getValue();

    String getSymbol();

    SquareMeterPerSecond toSquareMeterPerSecond();

    SquareInchPerSecond toSquareInchPerSecond();

    static SquareMeterPerSecond squareMeterPerSecond(double value){
        return SquareMeterPerSecond.of(value);
    }

    static SquareInchPerSecond squareInchPerSecond(double value){
        return SquareInchPerSecond.of(value);
    }

}

