package com.expedia.seiso.domain.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.expedia.seiso.core.ann.RestResource;
import com.expedia.seiso.domain.entity.Label;

@RestResource(path = RepoKeys.LABELS)
public interface LabelRepo extends PagingAndSortingRepository<Label, Long> {

}
