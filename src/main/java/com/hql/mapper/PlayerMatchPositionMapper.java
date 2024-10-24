package com.hql.mapper;

import com.hql.entity.Match;
import com.hql.entity.PlayerMatchPosition;
import com.hql.model.PlayerMatchPositionDTO;
import org.mapstruct.Mapper;

@Mapper(uses = { PlayerMapper.class, Match.class })
public interface PlayerMatchPositionMapper extends BaseMapper<PlayerMatchPositionDTO, PlayerMatchPosition> {

    default PlayerMatchPosition fromId (Long id) {
        if (id == null) {
            return null;
        }
        return PlayerMatchPosition.builder().id(id).build();
    }
}
