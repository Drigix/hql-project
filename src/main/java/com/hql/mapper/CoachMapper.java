package com.hql.mapper;

import com.hql.entity.Coach;
import com.hql.model.CoachDTO;
import org.mapstruct.Mapper;

@Mapper(uses = { TeamMapper.class })
public interface CoachMapper extends BaseMapper<CoachDTO, Coach> {

    default Coach fromId (Long id) {
        if (id == null) {
            return null;
        }
        return Coach.builder().id(id).build();
    }
}
