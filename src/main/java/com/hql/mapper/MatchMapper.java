package com.hql.mapper;

import com.hql.entity.Match;
import com.hql.model.MatchDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MatchMapper extends BaseMapper<MatchDTO, Match>{

    default Match fromId (Long id) {
        if (id == null) {
            return null;
        }
        return Match.builder().id(id).build();
    }
}
