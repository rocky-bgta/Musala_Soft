/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 23-Jan-2021
 * Time: 1:10 PM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.musalasoft.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "peripheral")
public class PeripheralEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid", nullable = false)
	private Long uid;
	
	@Column(name="vendor")
	private String vendor;

	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name="status")
	private boolean status;

//	@ManyToOne(fetch = FetchType.LAZY)
//	private GatewayEntity gateway;
	

}
