package com.salahin.musalasoft.service;

import com.salahin.musalasoft.core.ResponseObject;
import com.salahin.musalasoft.model.GatewayModel;

import java.util.UUID;

public interface GatewayService {
	ResponseObject createGateWay(GatewayModel gateWayModel);
	ResponseObject updateGateWay(GatewayModel gateWayModel);
	ResponseObject getGatewayById(UUID uuid);
	ResponseObject getAllGateway();
	ResponseObject deleteGatewayById(UUID uuid);
}
