package com.salahin.musalasoft.service;

import com.salahin.musalasoft.core.ResponseObject;
import com.salahin.musalasoft.entities.GatewayEntity;

import java.util.UUID;

public interface GatewayService {
	ResponseObject createGateWay(GatewayEntity gatewayEntity);
	ResponseObject updateGateWay(GatewayEntity gateWayModel);
	ResponseObject getGatewayById(UUID uuid);
	ResponseObject getAllGateway();
	ResponseObject deleteGatewayById(UUID uuid);
}
