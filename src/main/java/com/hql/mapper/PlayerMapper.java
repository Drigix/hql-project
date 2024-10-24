package com.hql.mapper;

import com.hql.entity.Player;
import com.hql.model.PlayerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = { TeamMapper.class })
public interface PlayerMapper extends  BaseMapper<PlayerDTO, Player> {

    default Player fromId (Long id) {
        if (id == null) {
            return null;
        }
        return Player.builder().id(id).build();
    }
}
