package com.hql.service.impl;

import com.hql.dao.StadiumDAO;
import com.hql.mapper.StadiumMapper;
import com.hql.mapper.StadiumMapperImpl;
import com.hql.model.StadiumDTO;
import com.hql.service.StadiumService;

import java.util.List;

public class StadiumServiceImpl implements StadiumService {

    private final StadiumMapper stadiumMapper;
    private final StadiumDAO stadiumDAO;

    public StadiumServiceImpl() {
        this.stadiumMapper = new StadiumMapperImpl();
        this.stadiumDAO = new StadiumDAO();
    }

    @Override
    public List<StadiumDTO> findAll() {
        return stadiumMapper.toDto(stadiumDAO.getAll());
    }
}
