/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
	
	List<User> findByEnterpriseId(Long id);
	
	Page<User> findByEnterpriseId(Long id, Pageable pageRequest);

	@Modifying
	@Query("delete from User user where user.enterprise.id=?1")
	void deleteByEnterpriseId(Long id);

	User findByEmail(String email);
}
