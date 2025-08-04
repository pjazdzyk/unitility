package com.synerset.unitility.jackson;

import com.synerset.unitility.unitsystem.common.Height;

public class TestMaterialLayer {
    private String code;

    private Height thickness;

    public TestMaterialLayer() {
    }

    public TestMaterialLayer(String code, Height thickness) {
        this.code = code;
        this.thickness = thickness;
    }

    public TestMaterialLayer(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Height getThickness() {
        return thickness;
    }

    public void setThickness(Height thickness) {
        this.thickness = thickness;
    }
}