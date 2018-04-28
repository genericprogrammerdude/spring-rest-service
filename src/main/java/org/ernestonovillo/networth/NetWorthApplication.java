package org.ernestonovillo.networth;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Handles application things.
 */
@SpringBootApplication
public class NetWorthApplication implements ApplicationRunner {

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("DAO.xml");
        final DAO dao = (DAO) context.getBean("DAO");
        assert (dao != null && dao.isConnected());

        final List<User> users = dao.getUsers();
        for (final User user : users) {
            System.out.println(user.getName());
        }
        SpringApplication.run(NetWorthApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (final String name : args.getOptionNames()) {
            System.out.println(name + ": " + args.getOptionValues(name));
        }
    }
}
