package com.prz.systemkurier.controller;

import com.prz.systemkurier.criteria.Criteria;
import com.prz.systemkurier.domain.User;
import com.prz.systemkurier.dto.PaginationData;
import com.prz.systemkurier.service.RoleService;
import com.prz.systemkurier.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController extends PaginationController<User> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private Logger logger = Logger.getLogger(UserController.class);

    @Override
    public PaginationData<User> fetch(Criteria criteria) throws Exception {
        List<User> data = userService.getUsersWithCriteriaPaginated(criteria);
        Integer totalItems = userService.countUsersWithCriteria(criteria);
        return new PaginationData<User>(totalItems, data);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll() throws SQLException {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(@RequestBody User user) throws SQLException {
        logger.info("test");

        userService.saveUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/userToUpdate", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUser(@RequestBody User user) throws SQLException {
        userService.updateUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/paginated", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getPaginatedUsers() {
        List<User> user = new ArrayList<User>();
        try {
            user = userService.getAllUsersPaginated(new Criteria(0, 10));
        } catch (SQLException e) {
            logger.error(e);
            return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }
}
