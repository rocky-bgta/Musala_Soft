/**
 * Created By: Md. Nazmus Salahin
 * Created Date:18-April-2021
 * Time: 1:10 PM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.musalasoft.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

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

	@Column(name="vendor", nullable = false)
	private String vendor;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "created_date")
	private LocalDate createdDate;

	@Column(name="status", nullable = false)
	private boolean status;

}
