package com.hql.mapper;

import com.hql.entity.Stadium;
import com.hql.model.StadiumDTO;
import org.mapstruct.Mapper;

@Mapper(uses = { MatchMapper.class })
public interface StadiumMapper extends BaseMapper<StadiumDTO, Stadium> {

    default Stadium fromId (Long id) {
        if (id == null) {
            return null;
        }
        return Stadium.builder().id(id).build();
    }
}
