package com.synerset.unitility.unitsystem.util;

import java.util.List;

public record PhysicalQuantityInfo(
        Class<?> quantityClass,
        List<PhysicalUnitInfo> supportedUnits
) {}