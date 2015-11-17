package com.prz.systemkurier.controller;

import com.prz.systemkurier.criteria.Criteria;
import com.prz.systemkurier.domain.Role;
import com.prz.systemkurier.domain.User;
//import com.prz.systemkurier.dto.PaginationData;
import com.prz.systemkurier.enumerate.RoleName;
import com.prz.systemkurier.enumerate.Status;
import com.prz.systemkurier.service.RoleService;
import com.prz.systemkurier.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RequestMapping("/user")
@RestController
public class UserController /*extends PaginationController<User>*/{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private Logger logger = Logger.getLogger(UserController.class);

    /*@Override
    public PaginationData<User> fetch(Criteria criteria) throws Exception {
        List<User> data = userService.getUsersWithCriteriaPaginated(criteria);
        Integer totalItems = userService.countUsersWithCriteria(criteria);
        return new PaginationData<User>(totalItems, data);
    }*/

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() throws SQLException {
        return userService.getAllUsers();
    }

    public void saveRole() throws SQLException {
        Role role = new Role();
        role.setCreateDate(new Date());
        role.setName(RoleName.ADMIN);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Void> saveUser() throws SQLException {
        logger.info("test User");
        User user = new User();
        user.setId(new Long(1));
        user.setPassword("hasloniedozlamani");
        user.setCreateDate(new Date());
        user.setEmail("mwoj92@gmail.com");
        user.setFirstName("Wojciech");
        user.setLastName("Ma³ek");
        user.setIndexNumber(new Long(1122334455));
        user.setStatus(Status.ACTIVE);
        userService.saveUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "role", method = RequestMethod.GET)
    public Role getRole() throws SQLException {
        return roleService.getRole(RoleName.KURIER);
    }

    @RequestMapping(value = "/users/paginated", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getPaginatedUsers(){
        List<User> user = new ArrayList<User>();
        try {
            user = userService.getAllUsersPaginated(new Criteria(0,10));
        } catch (SQLException e) {
            logger.error(e);
            return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }
}
