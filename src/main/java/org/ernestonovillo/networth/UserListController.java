package org.ernestonovillo.networth;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for UserList resource.
 */
@RestController
public class UserListController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/userlist")
	public UserList userList(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new UserList(counter.incrementAndGet(), String.format(template, name));
	}
}
