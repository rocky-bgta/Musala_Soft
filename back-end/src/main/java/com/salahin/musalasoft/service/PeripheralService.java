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
