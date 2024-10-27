package com.sqlInjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

//	@Query(value = "SELECT * FROM user_entity u where u.user_name= '' and u.password = '' OR '1'='1'", nativeQuery = true)
//	List<UserEntity> findUser(@Param("uname") String name, @Param("password") String password);

	@Query(value = "SELECT * FROM user_entity u where u.user_name = :name", nativeQuery = true)
	UserEntity findUser(@Param("name") String name);

	UserEntity findByUserName(String name);
}
