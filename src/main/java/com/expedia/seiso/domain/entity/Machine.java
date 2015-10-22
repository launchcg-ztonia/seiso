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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.expedia.seiso.core.ann.Key;
import com.expedia.seiso.core.ann.Projection;
import com.expedia.seiso.core.ann.Projection.Cardinality;
import com.expedia.seiso.core.ann.Projections;
import com.expedia.seiso.domain.entity.key.ItemKey;
import com.expedia.seiso.domain.entity.key.SimpleItemKey;
import com.expedia.seiso.domain.repo.RepoKeys;
import com.expedia.serf.ann.RestResource;

/**
 * @author Willie Wheeler
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false, of = { "name" })
@ToString(callSuper = true, of = { "name", "fqdn", "ipAddress", "dataCenter" })
@Entity
//@formatter:off
@Projections({
	@Projection(cardinality = Cardinality.COLLECTION, paths = { "dataCenter" }),
	@Projection(cardinality = Cardinality.SINGLE, paths = { "dataCenter", "nodes.serviceInstance" })
	})
//@formatter:on
public class Machine extends AbstractItem {
	
	// TODO Figure out the right regex @Pattern here, if any.
	@NotNull
	@Size(min = 1, max = 250)
	@Key
	private String name;

	@Size(max = 80)
	private String os;
	
	@Size(max = 80)
	private String osVersion;
	
	@Size(max = 80)
	private String platform;
	
	@Size(max = 80)
	private String platformVersion;
	
	@Size(max = 250)
	private String hostname;
	
	@Size(max = 250)
	private String domain;
	
	@Size(max = 250)
	private String fqdn;
	
	@Size(max = 20)
	private String ipAddress;
	
	@Size(max = 80)
	@Column(name = "ip6_address")
	private String ip6Address;
	
	@Size(max = 80)
	private String macAddress;
	
	@Size(max = 250)
	private String nativeMachineId;
	
	@Size(max = 250)
	private String machineType;
	
	@Size(max = 250)
	private String chefRole;
	
	@Size(max = 250)
	private String virtualSystem;
	
	@Size(max = 250)
	private String virtualRole;

	@ManyToOne
	@JoinColumn(name = "data_center_id")
	@RestResource(path = "data-center")
	private DataCenter dataCenter;

	@NonNull
	@OneToMany(mappedBy = "machine")
	@RestResource(path = "nodes")
	private List<Node> nodes = new ArrayList<Node>();

	@Override
	public ItemKey itemKey() {
		return new SimpleItemKey(Machine.class, name);
	}

	@Override
	public String[] itemPath() {
		return new String[] { RepoKeys.MACHINES, name };
	}
}
