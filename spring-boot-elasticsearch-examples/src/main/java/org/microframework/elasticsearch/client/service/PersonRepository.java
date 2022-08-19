package org.microframework.elasticsearch.client.service;

import org.microframework.elasticsearch.client.entity.ValidAge;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Shaoyu Liu
 * @date 2022-08-19
 */
public interface PersonRepository extends ElasticsearchRepository<ValidAge, Long> {

}
