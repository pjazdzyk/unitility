package com.synerset.process;

import com.synerset.state.FlowOfDryAir;
import com.synerset.unitsystem.power.KiloWatt;

public record HeatingResultDto(FlowOfDryAir outletFlow, KiloWatt outputPower) {
}
