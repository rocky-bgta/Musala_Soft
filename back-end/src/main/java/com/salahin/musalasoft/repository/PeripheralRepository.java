/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 23-Jan-2021
 * Time: 4:19 AM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.musalasoft.repository;

import com.salahin.musalasoft.entities.PeripheralEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeripheralRepository extends JpaRepository<PeripheralEntity, Long> {
}
