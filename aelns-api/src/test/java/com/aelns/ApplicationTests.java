package com.aelns;

import com.aelns.core.utils.number.RandomUtil;
import com.aelns.dao.CityMapper;
import com.aelns.model.City;
import com.aelns.model.CityExample;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by aelns on 2017/3/8.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private CityMapper cityMapper;

    @Test
    @Rollback
    public void findByName() throws Exception {
        String cityName = "Test city name";

        // add data
        City city = new City();
        city.setId(new RandomUtil().hashCode());
        city.setCityName(cityName);
        cityMapper.insert(city);

        // find by name
        CityExample example = new CityExample();
        example.createCriteria().andCityNameEqualTo(cityName);
        List<City> list = cityMapper.selectByExample(example);
        Assert.assertEquals(true, CollectionUtils.isNotEmpty(list));

        // clean data
        list.forEach(c -> cityMapper.deleteByPrimaryKey(c.getId()));
    }

}
