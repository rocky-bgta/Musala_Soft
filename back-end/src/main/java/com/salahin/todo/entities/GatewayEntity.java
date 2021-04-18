/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 23-Jan-2021
 * Time: 1:10 PM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.todo.entities;

import com.salahin.todo.core.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "getway")
public class GatewayEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", nullable = false)
	private UUID id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="ip4_address")
	private String ip4address;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "gateway")
	List<PeripheralEntity> peripheralEntityList = new ArrayList<>();
}
