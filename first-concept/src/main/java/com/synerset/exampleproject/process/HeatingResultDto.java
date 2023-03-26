package com.synerset.exampleproject.process;

import com.synerset.exampleproject.state.FlowOfDryAir;
import com.synerset.unitsystem.power.KiloWatt;

public record HeatingResultDto(FlowOfDryAir outletFlow, KiloWatt outputPower) {

}
