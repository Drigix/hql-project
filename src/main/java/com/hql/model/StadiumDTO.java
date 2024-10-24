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
public class StadiumDTO {

    private String name;

    private String location;

    private List<MatchDTO> matches;
}
