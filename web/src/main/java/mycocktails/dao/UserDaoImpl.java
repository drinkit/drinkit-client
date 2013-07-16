/*
 * Copyright 2007-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mycocktails.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mycocktails.entity.UserProfile;



@Repository("userDao")
@Transactional(readOnly = true,propagation=Propagation.SUPPORTS)

public class UserDaoImpl implements UserDao  {

    private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /* (non-Javadoc)
	 * @see mycocktails.dao.UserDao#findPersonById(java.lang.Integer)
	 */
    public UserProfile findUserById(Integer id) {
        return em.find(UserProfile.class, id);
    }

    /* (non-Javadoc)
	 * @see mycocktails.dao.UserDao#findUsers(int, int)
	 */
    @SuppressWarnings("unchecked")
    public Collection<UserProfile> findUsers(final int startIndex, final int maxResults) {
        return em.createQuery("select u from UserProfile u order by u.lastName, u.firstName")
            .setFirstResult(startIndex).setMaxResults(maxResults).getResultList();
    }

    /* (non-Javadoc)
	 * @see mycocktails.dao.UserDao#findUsers()
	 */
    @SuppressWarnings("unchecked")
    public Collection<UserProfile> findUsers() {
        return em.createQuery("select u from UserProfile u order by u.lastName, u.firstName").getResultList();
    }

    /* (non-Javadoc)
	 * @see mycocktails.dao.UserDao#findUsersByLastName(java.lang.String)
	 */
    @SuppressWarnings("unchecked")
    public Collection<UserProfile> findUsersByLastName(String lastName) {
        return em.createQuery("select u from UserProfile u where u.lastName = :lastName order by u.lastName, u.firstName")
            .setParameter("lastName", lastName).getResultList();
    }

    
    /* (non-Javadoc)
	 * @see mycocktails.dao.UserDao#findUserByLoginIdPassword(java.lang.String, java.lang.String)
	 */
    public UserProfile findUserByLoginIdPassword(String loginId,String password) {
        return (UserProfile)em.createQuery("select u from UserProfile u where u.loginId = :loginId and u.password = :password")
            .setParameter("loginId", loginId).setParameter("password", password).getSingleResult();
    }

}
