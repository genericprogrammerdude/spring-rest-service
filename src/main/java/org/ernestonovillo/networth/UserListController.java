package org.ernestonovillo.networth;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for UserList resource.
 */
@RestController
public class UserListController {

    /*
    private DAO dao = null;;

    public UserListController(DAO dao) {
        this.dao = dao;
    }
    */

    @GetMapping("/userlist")
    public List<User> getUserList() {
        final DAO dao = new DAO("data/networth.db");
        if (dao != null && dao.connect()) {
            final List<User> users = dao.getUsers();
            dao.disconnect();
            return users;
        }

        return null;
    }
}
