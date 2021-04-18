package com.salahin.musalasoft.service;

import com.salahin.musalasoft.core.ResponseObject;
import com.salahin.musalasoft.model.PeripheralModel;

import java.util.UUID;

public interface PeripheralService {
	ResponseObject createPeripheral(PeripheralModel peripheralModel);
	ResponseObject updatePeripheral(PeripheralModel peripheralModel);
	ResponseObject getPeripheralById(UUID uuid);
	ResponseObject getAllPeripheral();
	ResponseObject deletePeripheralById(UUID uuid);
}
