package com.hql.mapper;

import java.util.List;

public interface BaseMapper<M, E> {

    M toDto(E entity);

    E toEntity(M model);

    List<M> toDto(List<E> entities);

    List<E> toEntity(List<M> models);
}
