package com.hql.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@Getter
@Setter
public class TeamDTO {

    private String country;

    private List<MatchDTO> matches;

    private List<PlayerDTO> players;
}
