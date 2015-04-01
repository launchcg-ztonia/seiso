/* 
 * Copyright 2013-2015 the original author or authors.
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
package com.expedia.seiso.domain.entity;

import com.expedia.seiso.core.ann.Key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.expedia.seiso.domain.entity.key.ItemKey;
import com.expedia.seiso.domain.entity.key.SimpleItemKey;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Michael Van Hoff
 * 
 * A Label describing a set of nodes.  Used to make logical associations, either
 * creating a subgroup in a similar type, or a join across different types.
 * 
 */
@EqualsAndHashCode(callSuper = false, of = "id")
@Data
@Accessors(chain = true)
@Entity
public class Label extends AbstractItem {

	@Key
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", insertable=false, updatable=false)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn( name = "meta_id")
	private Meta meta;

	@Override
	public ItemKey itemKey() {
		return new SimpleItemKey(Label.class, id);
	}
}
