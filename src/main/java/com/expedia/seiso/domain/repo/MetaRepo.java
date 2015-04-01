package com.expedia.seiso.domain.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.expedia.seiso.core.ann.RestResource;
import com.expedia.seiso.domain.entity.Meta;

@RestResource(path = RepoKeys.META)
public interface MetaRepo extends PagingAndSortingRepository<Meta, Long> {

}
