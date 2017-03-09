package com.aelns.api;

import com.aelns.core.support.BaseAction;
import com.aelns.core.support.MediaTypes;
import com.aelns.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aelns on 2017/3/8.
 */

@RestController
public class CityEndpoint extends BaseAction {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/exception", produces = MediaTypes.JSON_UTF_8)
    public Object exceptionExample() {
        throw new NullPointerException();
    }

    @RequestMapping(value = "/api/city", produces = MediaTypes.JSON_UTF_8)
    public Object listAllCity() {
        return wraperResult(cityService.findAll());
    }

    @RequestMapping(value = "/api/city/{id}", produces = MediaTypes.JSON_UTF_8)
    public Object listOneCity(@PathVariable Integer id) {
        return wraperResult(cityService.findById(id));
    }
}
