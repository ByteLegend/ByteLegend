package com.bytelegend.app.serverapi.data;

import software.amazon.awssdk.enhanced.dynamodb.extensions.annotations.DynamoDbVersionAttribute;

public abstract class Mvcc {
    private Integer version = 0;
    private Boolean updateSuccessful;

    @DynamoDbVersionAttribute
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getUpdateSuccessful() {
        return updateSuccessful;
    }

    public void setUpdateSuccessful(Boolean updateSuccessful) {
        this.updateSuccessful = updateSuccessful;
    }
}
