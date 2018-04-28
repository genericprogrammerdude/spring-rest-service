package org.ernestonovillo.networth;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for UserList resource.
 */
@RestController
public class UserListController {
    @GetMapping("/userlist")
    public List<User> getUserList() {
        final DAO dao = new DAO("data/networth.db");
        if (dao.connect()) {
            return dao.getUsers();
        }

        return null;
    }
}
