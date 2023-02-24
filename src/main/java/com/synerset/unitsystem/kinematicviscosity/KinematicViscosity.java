package com.synerset.unitsystem.kinematicviscosity;

import io.vavr.control.Either;

public sealed interface KinematicViscosity permits SquareInchPerSecond, SquareMeterPerSecond {

    double getValue();

    String getSymbol();

    SquareMeterPerSecond toSquareMeterPerSecond();

    SquareInchPerSecond toSquareInchPerSecond();

    static Either<InvalidKinematicViscosity, SquareMeterPerSecond> squareMeterPerSecond(double value){
        return SquareMeterPerSecond.of(value);
    }

    static Either<InvalidKinematicViscosity, SquareInchPerSecond> squareInchPerSecond(double value){
        return SquareInchPerSecond.of(value);
    }

}

