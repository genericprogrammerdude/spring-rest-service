package org.ernestonovillo.networth;

/**
 * Handles user list operations.
 */
public class UserList {
	private final long id;
	private final String content;

	public UserList(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
