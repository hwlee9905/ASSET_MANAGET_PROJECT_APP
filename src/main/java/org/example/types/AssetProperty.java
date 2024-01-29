package org.example.types;

public enum AssetProperty {
    ASSET_IDX("assetidx"),
    ASSET_TYPE("assetType"),
    SN("sn"),
    DEPT("dept"),
    MANUFACTURER("manufacturer"),
    ASSET_NAME("assetName");

    private final String propertyName;

    AssetProperty(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
