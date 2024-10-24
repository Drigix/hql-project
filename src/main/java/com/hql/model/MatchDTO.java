package com.hql.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@Getter
@Setter
public class MatchDTO {

    private LocalDateTime date;

    private String result;

    private Long stadiumId;

    private List<TeamDTO> teams;
}
