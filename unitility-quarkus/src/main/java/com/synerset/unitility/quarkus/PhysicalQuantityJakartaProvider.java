package com.synerset.unitility.quarkus;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemClassNotSupportedException;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The PhysicalQuantityJakartaProvider class is a Jakarta RS {@link ParamConverterProvider} that provides
 * {@link ParamConverter} instances for handling {@link PhysicalQuantity} instances in Jakarta resource methods.
 * This mechanism is required for deserializing {@link PhysicalQuantity} from {@link PathParam} or {@link QueryParam}
 * based on parsers registered in {@link PhysicalQuantityParsingRegistry}.
 */
@Provider
@Priority(1000)
@ApplicationScoped
class PhysicalQuantityJakartaProvider implements ParamConverterProvider {

    private final Map<Class<?>, ParamConverter<?>> immutableConverterRegistry;

    public PhysicalQuantityJakartaProvider(PhysicalQuantityParsingRegistry parsingRegistry) {
        Map<Class<?>, ParamConverter<?>> registry = new ConcurrentHashMap<>();
        parsingRegistry.findAllRegisteredClasses()
                .forEach(quantityClass -> registry.put(
                        quantityClass,
                        new PhysicalQuantityParamJakartaConverter<>(quantityClass, parsingRegistry))
                );
        this.immutableConverterRegistry = Collections.unmodifiableMap(registry);
    }

    /**
     * Returns a {@link ParamConverter} instance for the specified target class.
     *
     * @param targetClass      The class of the target type to be converted.
     * @param parametrizedType The generic type of the target type.
     * @param annotations      Annotations on the target type.
     * @param <T>              The target type.
     * @return A ParamConverter for the specified target class.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> ParamConverter<T> getConverter(Class<T> targetClass, Type parametrizedType, Annotation[] annotations) {
        validateTargetClass(targetClass);
        return (ParamConverter<T>) immutableConverterRegistry.get(targetClass);
    }


    /**
     * Validates whether the target class is supported and registered.
     *
     * @param targetClass The target class to be validated.
     */
    public void validateTargetClass(Class<?> targetClass) {
        if (!PhysicalQuantity.class.isAssignableFrom(targetClass)) {
            throw new UnitSystemClassNotSupportedException("Target class does not implements interface: " +
                    PhysicalQuantity.class + ". Target class: " + targetClass);
        }
        if (!immutableConverterRegistry.containsKey(targetClass)) {
            throw new UnitSystemClassNotSupportedException("Target class not found in converters registry. " +
                    "Target class: " + targetClass);
        }
    }

}