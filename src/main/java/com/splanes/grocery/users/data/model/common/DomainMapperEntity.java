package com.splanes.grocery.users.data.model.common;

import com.splanes.grocery.users.domain.model.common.DomainModel;

public abstract class DomainMapperEntity<D extends DomainModel> {
    public abstract D mapToDomain();
}
