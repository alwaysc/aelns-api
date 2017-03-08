package com.aelns.service;

import com.aelns.dao.CityMapper;
import com.aelns.model.City;
import com.aelns.model.CityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by aelns on 2017/3/8.
 */

@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    @Transactional(readOnly = true)
    public City findById(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    public List<City> findAll() {
        return cityMapper.selectByExample(new CityExample());
    }
}
