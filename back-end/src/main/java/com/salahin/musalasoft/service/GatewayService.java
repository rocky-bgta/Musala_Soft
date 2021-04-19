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
import com.salahin.musalasoft.entities.GatewayEntity;

public interface GatewayService {
	ResponseObject createGateWay(GatewayEntity gatewayEntity);
	ResponseObject updateGateWay(GatewayEntity gateWayModel);
	ResponseObject getGatewayById(String id);
	ResponseObject getAllGateway();
	ResponseObject deleteGatewayById(String id);
}
