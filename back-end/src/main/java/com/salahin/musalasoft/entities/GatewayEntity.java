/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 23-Jan-2021
 * Time: 1:10 PM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.musalasoft.entities;

import com.salahin.musalasoft.core.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "gateway")
public class GatewayEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id",columnDefinition = "CHAR(32)")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="ipv4_address")
	private String ipv4address;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "gateway_fk", referencedColumnName = "id")
	List<PeripheralEntity> peripheralList = new ArrayList<>();
}
