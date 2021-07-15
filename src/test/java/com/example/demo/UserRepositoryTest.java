package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private User_repository repo;
	
	@Autowired
	private TestEntityManager entityManager;

	private Object object;
	
	@Test
	public void testCreateUser() {
		
		User user = new User();
		user.setEmail("lex@gmail.com");
		user.setPassword("1qaz2wsx");
		user.setFirstname("lex");
		user.setLastname("sander");
		
		User savedUser = repo.save(user);
		
		User existuser = entityManager.find(User.class,savedUser.getId());
		
		assertThat(existuser.getEmail()).isEqualTo(user.getEmail());
		
	}
	
	@Test
	public void testFindUserByEmail() {
		
		String email = "papsavindri@gmail.com";
		
		User user = repo.findByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
	

}
