package com.synerset.unitility.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UnitilityObjectMapperFactoryTest {

    @Test
    @DisplayName("Object mapper factory: should successfully use object mapper from factory")
    void shouldSuccessfullyUseObjectMapperFromFactory() throws JsonProcessingException {
        ObjectMapper objectMapper = UnitilityObjectMapperFactory.QUANTITY_AWARE_OBJECT_MAPPER;

        TestMaterialComposition parsedClass = objectMapper.readValue("{\"layers\":[{\"code\":\"M_STG\",\"thickness\":{\"value\":0.5,\"unit\":\"mm\"}}]}", TestMaterialComposition.class);

        assertThat(parsedClass).isNotNull();
        assertThat(parsedClass.getLayers()).isNotNull().isNotEmpty();
        TestMaterialLayer testMaterialLayer = parsedClass.getLayers().get(0);
        assertThat(testMaterialLayer).isNotNull();

        assertThat(testMaterialLayer.getThickness()).isNotNull();
        assertThat(testMaterialLayer.getThickness().getValue()).isEqualTo(0.5);

    }

}