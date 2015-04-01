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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.expedia.seiso.core.ann.Key;
import com.expedia.seiso.core.ann.Projection;
//import com.expedia.seiso.core.ann.Projections;
import com.expedia.seiso.core.ann.RestResource;
import com.expedia.seiso.core.ann.Projection.Cardinality;
import com.expedia.seiso.domain.entity.key.ItemKey;
import com.expedia.seiso.domain.entity.key.SimpleItemKey;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Michael Van Hoff
 * 
 * An identity to track metadata between entities and meta-information tables.
 * A one-to-one association, mapping entity instances to meta-instances.
 * To Add Metadata
 * 		Add a column to this class and associated table to track the metadata's id.
 * To Add an Entity
 * 		Add a column to the entity's class and table for meta_id.
 * 		Add functionality to associate the entity's meta_id to one meta id/row.
 * 
 * Example:
 * 	To Add "Label"ing (metadata) a "Node" (entity):
 * 		Create class "Label" and table "label".
 * 		Add column 'label_id' to class 'Meta' and table 'meta'.
 *		Add column 'meta_id' to class 'Node' and table 'node'.
 *		Create "Add a label to a node" functionality
 *			Add the new label to the label table if it doesn't already exist.
 *			If node has a meta_id value
 *				Populate meta's label_id field with label.id where the new label equals label.name.
 *			If node's meta_id is null
 *				Add a new row to meta, setting label_id equals the new label's label.id
 *				Populate node's meta_id field with the newly generated meta table id. 
 *
 */
@Data
@Accessors(chain = true)
@Entity
@EqualsAndHashCode(callSuper = false, of = "id")
@Projection(cardinality = Cardinality.SINGLE, paths = { "meta" })

public class Meta extends AbstractItem {
	
	@Key
	@Column(name = "id", insertable=false, updatable=false)
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "meta", cascade = CascadeType.ALL, orphanRemoval = true)
	@RestResource(path = "labels")
	private List<Label> labels = new ArrayList<>();
	
	@Override
	public ItemKey itemKey() {
		return new SimpleItemKey(Meta.class, id);
	}
}
