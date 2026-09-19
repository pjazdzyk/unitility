package com.synerset.unitility.unitsystem.definitions;

import com.synerset.unitility.unitsystem.Unit;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Finds every constant of every {@code *Units} enum in the main (production) classes, by reflection over the
 * compiled output directory. Test-scope enums (the custom-unit examples) are not included, because they are not
 * part of the library.
 * <p>
 * Keys have the form {@code "PressureUnits.PSI"}: the enum's simple name, a dot, the constant's name.
 */
final class UnitCatalog {

    private static final String ROOT_PACKAGE = "com.synerset.unitility.unitsystem";

    private UnitCatalog() {
    }

    static Map<String, Unit> allUnits() {
        Map<String, Unit> units = new LinkedHashMap<>();
        for (Class<?> enumClass : allUnitEnums()) {
            for (Object constant : enumClass.getEnumConstants()) {
                units.put(keyOf((Unit) constant), (Unit) constant);
            }
        }
        return Collections.unmodifiableMap(units);
    }

    static List<Class<?>> allUnitEnums() {
        Path classesRoot = mainClassesRoot();
        Path packageRoot = classesRoot.resolve(ROOT_PACKAGE.replace('.', '/'));
        List<String> classNames;
        try (Stream<Path> paths = Files.walk(packageRoot)) {
            classNames = paths
                    .map(path -> classesRoot.relativize(path).toString().replace('\\', '/'))
                    .filter(name -> name.endsWith("Units.class"))
                    .map(name -> name.substring(0, name.length() - ".class".length()).replace('/', '.'))
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        List<Class<?>> enums = new ArrayList<>();
        for (String className : classNames) {
            Class<?> type = load(className);
            if (type.isEnum() && Unit.class.isAssignableFrom(type)) {
                enums.add(type);
            }
        }
        return enums;
    }

    static String keyOf(Unit unit) {
        Enum<?> constant = (Enum<?>) unit;
        return constant.getDeclaringClass().getSimpleName() + "." + constant.name();
    }

    private static Path mainClassesRoot() {
        try {
            return Paths.get(Unit.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Class<?> load(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

}
