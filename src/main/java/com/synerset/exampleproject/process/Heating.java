package com.synerset.exampleproject.process;

import com.synerset.exampleproject.state.AirFlowFactory;
import com.synerset.exampleproject.state.FlowOfDryAir;
import com.synerset.unitsystem.power.KiloWatt;
import com.synerset.unitsystem.power.Power;
import com.synerset.unitsystem.temperature.Celsius;
import com.synerset.unitsystem.temperature.Temperature;

public class Heating {

    private final AirFlowFactory airFlowFactory;

    public Heating(AirFlowFactory airFlowFactory) {
        this.airFlowFactory = airFlowFactory;
    }

    public HeatingResultDto fromOutletTemperature(FlowOfDryAir inletFlow, Celsius targetTemperature) {
        double inTemp = inletFlow.temperature().getValue();
        double outTemp = targetTemperature.getValue();
        double specHeat = inletFlow.specificHeat().getValue();
        double massFlow = inletFlow.massFlow().getValue();
        KiloWatt heatOfProcess = Power.kiloWatt(massFlow * specHeat * (outTemp - inTemp));
        FlowOfDryAir outletAir = airFlowFactory.createFlowOfDryAir(targetTemperature, inletFlow.massFlow());
        return new HeatingResultDto(outletAir, heatOfProcess);
    }

    public HeatingResultDto fromInputPower(FlowOfDryAir inletFlow, KiloWatt inputPower) {
        double inTemp = inletFlow.temperature().getValue();
        double specHeat = inletFlow.specificHeat().getValue();
        double massFlow = inletFlow.massFlow().getValue();
        double heat = inputPower.getValue();
        Celsius outletTemperature = Temperature.celsius(heat / massFlow * specHeat + inTemp);
        FlowOfDryAir outletAir = airFlowFactory.createFlowOfDryAir(outletTemperature, inletFlow.massFlow());
        return new HeatingResultDto(outletAir, inputPower.toKiloWatt());
    }


}
