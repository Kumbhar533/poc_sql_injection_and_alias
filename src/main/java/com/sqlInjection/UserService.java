package com.sqlInjection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private EntityManager entityManager;

	public String Register(UserEntity entity) {

		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(entity.getUserName());
		userEntity.setPassword(entity.getPassword());

		repository.save(userEntity);

		return "User register";

	}

	// perform sql injection
	// here name is part of sql query structure not treat as data
	public List<UserEntity> getUserByName(String name) {
		String queryString = "SELECT * FROM user_entity u WHERE u.user_name =" + name;
		return entityManager.createNativeQuery(queryString, UserEntity.class).getResultList();
	}

	// ways prevent from sql injection

	// Use Parameterized Queries and use validation for taking input such as for
	// special character
	// using Parameterized query it first create the sql query structure the takes
	// input as data to find which prevents sql injection
	public UserEntity getUserByName1(String name) {
		return repository.findUser(name);
	}

	// using jpa

	// When you pass name as an argument, Spring Data JPA automatically binds it as
	// a parameter in a prepared statement.
	// This means that the input is treated as a parameter and not part of the SQL
	// command itself.

	public UserEntity getUserByName2(String name) {

		UserEntity user = repository.findByUserName(name);

		return user;
	}

	// point to remember
//	1. Avoid Direct String Concatenation
//  2. Validate Input
//  3. Escaping All User Supplied Input
//	4. Use the Criteria API
//	5. Limit User Privileges
}
