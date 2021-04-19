/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 17-April-2021
 * Time: 8:14 PM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.musalasoft.controller;

import com.salahin.musalasoft.core.ResponseObject;
import com.salahin.musalasoft.entities.GatewayEntity;
import com.salahin.musalasoft.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/gateway/",
	consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE},
	produces = MediaType.APPLICATION_JSON_VALUE)
public class GatewayController {

    private final GatewayService gatewayService;

    @Autowired
	public GatewayController(GatewayService gatewayService) {
		this.gatewayService = gatewayService;
	}
	
	@PostMapping("create")
    public ResponseObject createGateway(@Valid @RequestBody GatewayEntity gatewayEntity){
        ResponseObject responseObject;
		responseObject = this.gatewayService.createGateWay(gatewayEntity);
        return responseObject;
    }
	
	@GetMapping("get/{id}")
	public ResponseObject getGatewayById(@PathVariable String id){
		ResponseObject responseObject;
		responseObject = this.gatewayService.getGatewayById(id);
		return responseObject;
	}
	
	@GetMapping("list")
	public ResponseObject getAllGateway(){
		ResponseObject responseObject;
		responseObject = this.gatewayService.getAllGateway();
		return responseObject;
	}
 
	@PutMapping("update")
	public ResponseObject updateGateway(@Valid @RequestBody GatewayEntity gatewayEntity){
		ResponseObject responseObject;
		responseObject = this.gatewayService.updateGateWay(gatewayEntity);
		return responseObject;
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseObject deleteGateway(@PathVariable String id){
		ResponseObject responseObject;
		responseObject = this.gatewayService.deleteGatewayById(id);
		return responseObject;
	}
	
}
