package com.synerset.process;

import com.synerset.state.AirFlowFactory;
import com.synerset.state.FlowOfDryAir;
import com.synerset.unitsystem.power.KiloWatt;
import com.synerset.unitsystem.power.Power;
import com.synerset.unitsystem.temperature.Celsius;
import com.synerset.unitsystem.temperature.Temperature;

public class Heating {

    private final AirFlowFactory airFlowFactory;

    public Heating(AirFlowFactory airFlowFactory) {
        this.airFlowFactory = airFlowFactory;
    }

    public HeatingResultDto fromOutletTemperature(FlowOfDryAir inletFlow, Temperature targetTemperature) {
        double inTemp = inletFlow.temperature().toCelsius().getValue();
        double outTemp = targetTemperature.toCelsius().getValue();
        double specHeat = inletFlow.specificHeat().toKiloJoulePerKilogramKelvin().getValue();
        double massFlow = inletFlow.massFlow().toKiloGramPerSecond().getValue();
        KiloWatt heatOfProcess = Power.kiloWatt(massFlow * specHeat * (outTemp - inTemp));
        FlowOfDryAir outletAir = airFlowFactory.createFlowOfDryAir(targetTemperature, inletFlow.massFlow());
        return new HeatingResultDto(outletAir, heatOfProcess);
    }

    public HeatingResultDto fromTargetPower(FlowOfDryAir inletFlow, Power targetPower) {
        double inTemp = inletFlow.temperature().toCelsius().getValue();
        double specHeat = inletFlow.specificHeat().toKiloJoulePerKilogramKelvin().getValue();
        double massFlow = inletFlow.massFlow().toKiloGramPerSecond().getValue();
        double heat = targetPower.toKiloWatt().getValue();
        Celsius outletTemperature = Temperature.celsius(heat / massFlow * specHeat + inTemp);
        FlowOfDryAir outletAir = airFlowFactory.createFlowOfDryAir(outletTemperature, inletFlow.massFlow());
        return new HeatingResultDto(outletAir, targetPower.toKiloWatt());
    }


}
