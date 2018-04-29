package org.ernestonovillo.networth;

import java.util.List;

import org.ernestonovillo.networth.models.NetWorthData;
import org.ernestonovillo.networth.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for UserList resource.
 */
@RestController
public class Controller {

    private DAO dao = null;;

    public Controller(DAO dao) {
        this.dao = dao;
    }

    @GetMapping("/user-list")
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

    @PutMapping("/update-user/{id}")
    public void updateUser(@PathVariable("id") long id, @RequestParam("name") String name,
            @RequestParam("languageId") long languageId) {
        dao.updateUser(id, name, languageId);
    }

    @PostMapping("/add-user")
    public void addUser(@RequestParam("name") String name, @RequestParam("languageId") long languageId) {
        dao.addUser(name, languageId);
    }

    @PutMapping("/update-asset/{id}")
    public void updateAsset(@PathVariable("id") long id, @RequestParam("name") String name,
            @RequestParam("value") double value, @RequestParam("currencyId") long currencyId,
            @RequestParam("categoryId") long categoryId) {
        dao.updateAsset(id, name, value, currencyId, categoryId);
    }
}
