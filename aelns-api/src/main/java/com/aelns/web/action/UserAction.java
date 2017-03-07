package com.aelns.web.action;

import com.aelns.domain.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by aelns on 2017/2/11.
 */
@RestController
@RequestMapping("/user")
public class UserAction {

    @RequestMapping("/{id}")
    public Object get(@PathVariable Long id) {
        User user = new User();
        user.setAge(id.intValue());
        user.setName("test");
        user.setCreateTime(new Date());
        return user;
    }
}
