package com.synerset.unitility.jackson.modules;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.synerset.unitility.jackson.serdes.GeoDistanceDeserializer;
import com.synerset.unitility.jackson.serdes.GeoDistanceSerializer;
import com.synerset.unitility.jackson.serdes.LatitudeDeserializer;
import com.synerset.unitility.jackson.serdes.LongitudeDeserializer;
import com.synerset.unitility.unitsystem.geographic.GeoDistance;
import com.synerset.unitility.unitsystem.geographic.GeoQuantityParsingFactory;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;

/**
 * The PhysicalQuantityJacksonModule class is a Jackson module that provides serializers and deserializers for
 * handling {@link Latitude} and {@link Longitude} instances during JSON serialization and deserialization.
 */
public class GeoQuantityJacksonModule extends SimpleModule {

    public GeoQuantityJacksonModule(GeoQuantityParsingFactory geoParsingFactory) {
        super("GeographicQuantityJacksonModule");

        /* SERIALIZERS */
        addSerializer(new GeoDistanceSerializer(GeoDistance.class));

        /* DESERIALIZERS */
        addDeserializer(Latitude.class, new LatitudeDeserializer(geoParsingFactory));
        addDeserializer(Longitude.class, new LongitudeDeserializer(geoParsingFactory));
        addDeserializer(GeoDistance.class, new GeoDistanceDeserializer(geoParsingFactory));
    }

}