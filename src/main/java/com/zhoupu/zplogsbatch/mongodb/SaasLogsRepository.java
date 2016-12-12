/*
 * Copyright 2012-2013 the original author or authors.
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

package com.zhoupu.zplogsbatch.mongodb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface SaasLogsRepository extends MongoRepository<SaasLogs, String> , QueryDslPredicateExecutor<SaasLogs>{
	
	//findByDateAndCidAndUidAndCnameLikeAndUrlLikeAndActionLikeAndParamsLikeAndSuccessAndResultLikeAndTimerGreaterThanEqualAndTimerLessThanEqual
	Page<SaasLogs> findByDateAndCidAndUidAndCnameLikeAndUrlLikeAndActionLikeAndParamsLikeAndSuccessAndResultLikeAndTimerGreaterThanEqualAndTimerLessThanEqual(
			String date,
			Long cid,
			Long uid,
			String cname,
			String url,
			String action,
			String params,
			Boolean success,
			String result,
			String starttime,
			String endtime,
			Pageable pageable);

}
