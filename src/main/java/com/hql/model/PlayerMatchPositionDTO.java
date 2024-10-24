package com.hql.model;

import com.hql.entity.PositionName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@Getter
@Setter
public class PlayerMatchPositionDTO {

    private PositionName position;

    private List<PlayerDTO> players;

    private MatchDTO match;
}
