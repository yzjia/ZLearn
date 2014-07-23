/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.Enterprise;

public interface EnterpriseDao extends PagingAndSortingRepository<Enterprise, Long> {
	Enterprise findByLoginName(String loginName);

	Enterprise findByEmail(String email);
}
