package com.hql.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Getter
@Setter
public class PlayerDTO {

    private String fName;

    private String sName;

    private Long teamId;
}
