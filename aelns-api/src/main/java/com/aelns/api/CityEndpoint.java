package com.aelns.api;

import com.aelns.core.support.MediaTypes;
import com.aelns.model.City;
import com.aelns.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by aelns on 2017/3/8.
 */

@RestController
public class CityEndpoint {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/city", produces = MediaTypes.JSON_UTF_8)
    public List<City> listAllCity() {
        return cityService.findAll();
    }

    @RequestMapping(value = "/api/city/{id}", produces = MediaTypes.JSON_UTF_8)
    public City listOneCity(@PathVariable Integer id) {
        return cityService.findById(id);
    }
}
