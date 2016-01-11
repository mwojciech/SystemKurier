package com.prz.systemkurier.test.repository;

import com.prz.systemkurier.configuration.PersistenceConfig;
import com.prz.systemkurier.configuration.SpringWebConfig;
import com.prz.systemkurier.domain.Role;
import com.prz.systemkurier.domain.User;
import com.prz.systemkurier.enumerate.RoleName;
import com.prz.systemkurier.enumerate.Status;
import com.prz.systemkurier.repository.UserRepository;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Roman on 07.10.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebConfig.class, PersistenceConfig.class})
@Transactional
public class UserRepositoryTest {

    private Logger logger = Logger.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    private User user = new User();

    @Before
    public void init() {
        user.setRole(new Role(RoleName.KURIER, new Date()));
        user.setCreateDate(new Date());
        user.setLastName("lastname");
        user.setFirstName("firstname");
        user.setStatus(Status.ACTIVE);
        user.setEmail("email");
        user.setIndexNumber(Long.valueOf(111111));
        user.setFirstName("firstname");
        user.setPassword("password");
    }

    @Test
    public void save() {
        try {
            userRepository.save(user);
            System.out.println(userRepository.getByCredentials("lastname", "firstname").getFirstName());
            Assert.assertEquals(user, userRepository.getByCredentials("lastname", "firstname"));
        } catch (SQLException e) {
            logger.info(e);
        }
    }

    @Test
    public void getAll(){
        try {
            List<User> users = userRepository.getAll();
            Assert.assertEquals(users.size(), 14);
        } catch (SQLException e){
            logger.info(e);
        }
    }


}
