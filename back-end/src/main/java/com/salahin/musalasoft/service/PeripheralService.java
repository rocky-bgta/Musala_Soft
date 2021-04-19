/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 19-April-2021
 * Time: 5:26 AM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.musalasoft.service;

import com.salahin.musalasoft.core.ResponseObject;
import com.salahin.musalasoft.entities.PeripheralEntity;

import java.util.UUID;

public interface PeripheralService {
	ResponseObject createPeripheral(PeripheralEntity peripheralEntity);
	ResponseObject updatePeripheral(PeripheralEntity peripheralEntity);
	ResponseObject getPeripheralById(UUID uuid);
	ResponseObject getAllPeripheral();
	ResponseObject deletePeripheralById(UUID uuid);
}
