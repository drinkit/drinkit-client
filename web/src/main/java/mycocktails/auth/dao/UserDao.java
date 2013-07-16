package mycocktails.auth.dao;

import java.util.Collection;

import mycocktails.auth.entity.UserProfile;

public interface UserDao {

	/**
	 * Find persons.
	 */
	public abstract UserProfile findUserById(Integer id);

	/**
	 * Find persons using a start index and max number of results.
	 */
	@SuppressWarnings("unchecked")
	public abstract Collection<UserProfile> findUsers(final int startIndex,
			final int maxResults);

	/**
	 * Find persons.
	 */
	@SuppressWarnings("unchecked")
	public abstract Collection<UserProfile> findUsers();

	/**
	 * Find persons by last name.
	 */
	@SuppressWarnings("unchecked")
	public abstract Collection<UserProfile> findUsersByLastName(String lastName);

	public abstract UserProfile findUserByLoginIdPassword(String loginId,
			String password);

}