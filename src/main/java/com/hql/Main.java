package com.hql;

import com.hql.dao.StadiumDAO;
import com.hql.entity.Stadium;
import com.hql.model.StadiumDTO;
import com.hql.service.StadiumService;
import com.hql.service.impl.StadiumServiceImpl;
import com.hql.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StadiumService stadiumService = new StadiumServiceImpl();
        List<StadiumDTO> stadiums = stadiumService.findAll();
        for(StadiumDTO stadium: stadiums) {
            System.out.println(stadium.getName());
        }
    }
}