package com.hql.todo.service;

import java.util.List;

public interface CoachService {

    List<String> findAllSecondNamesByTeamId(Long teamId, boolean useCriteria);
}
