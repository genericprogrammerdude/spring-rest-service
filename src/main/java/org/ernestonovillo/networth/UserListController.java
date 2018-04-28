package org.ernestonovillo.networth;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for UserList resource.
 */
@RestController
public class UserListController {

    private DAO dao = null;;

    public UserListController(DAO dao) {
        this.dao = dao;
    }

    @GetMapping("/userlist")
    public List<User> getUserList() {
        return dao.getUsers();
    }
}
