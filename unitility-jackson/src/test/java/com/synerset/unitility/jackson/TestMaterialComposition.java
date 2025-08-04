package com.synerset.unitility.jackson;

import java.util.List;

public class TestMaterialComposition {

    private List<TestMaterialLayer> layers;

    public TestMaterialComposition() {
    }

    public TestMaterialComposition(List<TestMaterialLayer> layers) {
        this.layers = layers;
    }

    public List<TestMaterialLayer> getLayers() {
        return layers;
    }

    public void setLayers(List<TestMaterialLayer> layers) {
        this.layers = layers;
    }
}