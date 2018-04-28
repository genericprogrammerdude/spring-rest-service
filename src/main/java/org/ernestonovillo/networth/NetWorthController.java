package org.ernestonovillo.networth;

import java.util.List;

import org.ernestonovillo.networth.models.NetWorthData;
import org.ernestonovillo.networth.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for UserList resource.
 */
@RestController
public class NetWorthController {

    private DAO dao = null;;

    public NetWorthController(DAO dao) {
        this.dao = dao;
    }

    @GetMapping("/userlist")
    public List<User> getUserList() {
        return dao.getUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") long id) {
        return dao.getUser(id);
    }

    @GetMapping("/networth/{id}")
    public NetWorthData getNetWorthData(@PathVariable("id") long id) {
        return dao.getNetWorthData(id);
    }
}
