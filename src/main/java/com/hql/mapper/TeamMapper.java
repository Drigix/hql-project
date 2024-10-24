package com.hql.mapper;

import com.hql.entity.Team;
import com.hql.model.TeamDTO;
import org.mapstruct.Mapper;

@Mapper(uses = { MatchMapper.class, PlayerMapper.class, CoachMapper.class })
public interface TeamMapper extends BaseMapper<TeamDTO, Team> {

    default Team fromId (Long id) {
        if (id == null) {
            return null;
        }
        return Team.builder().id(id).build();
    }
}
