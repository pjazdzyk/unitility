package com.synerset.unitility.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.synerset.unitility.jackson.module.PhysicalQuantityJacksonModule;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.datatype.jsr310.JavaTimeModule;

/**
 * A factory class that provides a static, singleton instance of a pre-configured {@link ObjectMapper}.
 * This ObjectMapper is specifically designed to handle various data types, including:
 * <ul>
 * <li>Custom physical quantity objects from the 'synerset-unitility' library.</li>
 * <li>Standard Java Date/Time objects (e.g., LocalDate, LocalDateTime, Instant).</li>
 * <li>Other common modules auto-detected by Jackson's module finder.</li>
 * </ul>
 * The singleton approach ensures that the ObjectMapper is initialized only once, promoting efficiency and thread safety
 * throughout the application.
 */
public class UnitilityObjectMapperFactory {

    private UnitilityObjectMapperFactory() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    /**
     * The static, singleton instance of the pre-configured {@link JsonMapper}.
     * This instance is initialized lazily and is ready for use across the application.
     */
    public static final JsonMapper QUANTITY_AWARE_OBJECT_MAPPER = createPhysicalQuantityAwareObjectMapper();

    /**
     * Creates and configures a new {@link JsonMapper} instance.
     * This method registers key modules for handling custom data types and common Java types.
     *
     * @return A new, fully configured {@link JsonMapper} instance.
     */
    private static JsonMapper createPhysicalQuantityAwareObjectMapper() {
        PhysicalQuantityJacksonModule physicalQuantityJacksonModule = new PhysicalQuantityJacksonModule(PhysicalQuantityParsingFactory.getDefaultParsingFactory());

        return JsonMapper.builder()
                .addModule(physicalQuantityJacksonModule)
                .addModule(new JavaTimeModule())
                .findAndAddModules()
                .changeDefaultPropertyInclusion(v ->
                        v.withValueInclusion(JsonInclude.Include.NON_NULL))
                .build();
    }
}