/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 23-Jan-2021
 * Time: 5:26 AM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.musalasoft.service.impl;

import com.salahin.musalasoft.constant.MessageConstant;
import com.salahin.musalasoft.core.ResponseObject;
import com.salahin.musalasoft.entities.GatewayEntity;
import com.salahin.musalasoft.model.GatewayModel;
import com.salahin.musalasoft.repository.GatewayRepository;
import com.salahin.musalasoft.service.GatewayService;
import com.salahin.musalasoft.utilities.UtilityMethods;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GatewayServiceImpl implements GatewayService {

	private static final ModelMapper modelMapper = new ModelMapper();
	private final GatewayRepository gatewayRepository;

	@Autowired
	public GatewayServiceImpl(GatewayRepository gatewayRepository) {
		this.gatewayRepository = gatewayRepository;
	}
	
	@Override
	public ResponseObject createGateWay(GatewayModel getGatewayModel) {
		GatewayEntity gatewayEntity;
		ResponseObject responseObject;
		try{
			gatewayEntity = modelMapper.map(getGatewayModel,GatewayEntity.class);
			gatewayEntity = this.gatewayRepository.save(gatewayEntity);
			getGatewayModel.setId(gatewayEntity.getId());
			responseObject = UtilityMethods.buildResponseObject(getGatewayModel,
				MessageConstant.SUCCESSFULLY_GATEWAY_CREATED,
				HttpStatus.OK);
		}catch (Exception ex){
			log.error("createGateWay method got exception ->", ex);
			responseObject = UtilityMethods.buildResponseObject(null,
				MessageConstant.FAILED_TO_CREATE_GATEWAY,
				HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseObject;
	}
	
	@Override
	public ResponseObject updateGateWay(GatewayModel gatewayModel) {
		GatewayEntity updatedGatewayEntity;
		Optional<GatewayEntity> oldGatewayEntity;
		ResponseObject responseObject;
		UUID uuid;
		try{
			uuid = gatewayModel.getId();
			oldGatewayEntity = this.gatewayRepository.findById(uuid);
			if(oldGatewayEntity.isPresent()){
				updatedGatewayEntity = modelMapper.map(gatewayModel ,GatewayEntity.class);
				updatedGatewayEntity = this.gatewayRepository.save(updatedGatewayEntity);
				responseObject = UtilityMethods.buildResponseObject(updatedGatewayEntity,
					MessageConstant.SUCCESSFULLY_GATEWAY_UPDATED,
					HttpStatus.OK);
			}else {
				responseObject = UtilityMethods.buildResponseObject(gatewayModel,
					MessageConstant.REQUESTED_GATEWAY_DOES_NOT_EXIST_NOW,
					HttpStatus.NO_CONTENT);
			}
		}catch (Exception ex){
			log.error("updateGateway method got exception ->", ex);
			responseObject = UtilityMethods.buildResponseObject(null,
				MessageConstant.FAILED_TO_UPDATE_GATEWAY,
				HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseObject;
	}
	
	@Override
	public ResponseObject getGatewayById(UUID uuid) {
		Optional<GatewayEntity> gatewayEntity;
		GatewayModel gatewayModel;
		ResponseObject responseObject;
		try{
			gatewayEntity = this.gatewayRepository.findById(uuid);
			if(gatewayEntity.isPresent()){
				gatewayModel = modelMapper.map(gatewayEntity.get(),GatewayModel.class);
				responseObject = UtilityMethods.buildResponseObject(gatewayModel,
					MessageConstant.SUCCESSFULLY_GET_GATEWAY_BY_PROVIDED_ID,
					HttpStatus.OK);
			}else {
				responseObject = UtilityMethods.buildResponseObject(null,
					MessageConstant.REQUESTED_GATEWAY_DOES_NOT_EXIST_NOW,
					HttpStatus.NO_CONTENT);
			}
		}catch (Exception ex){
			log.error("getTodoById method got exception ->", ex);
			responseObject = UtilityMethods.buildResponseObject(null,
				MessageConstant.FAILED_TO_UPDATE_GATEWAY,
				HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseObject;
	}
	
	@Override
	public ResponseObject getAllGateway() {
		List<GatewayModel> gatewayModelList;
		ResponseObject responseObject;
		try{
			gatewayModelList =  this.gatewayRepository.findAll()
				.stream()
				.map(element -> modelMapper.map(element, GatewayModel.class))
				.collect(Collectors.toList());
			responseObject = UtilityMethods.buildResponseObject(gatewayModelList,
				MessageConstant.SUCCESSFULLY_GET_ALL_GATEWAY,
				HttpStatus.OK);
			
		}catch (Exception ex){
			log.error("getAllTodo method got exception ->", ex);
			responseObject = UtilityMethods.buildResponseObject(null,
				MessageConstant.FAILED_TO_GET_ALL_GATEWAY,
				HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseObject;
	}
	
	@Override
	public ResponseObject deleteGatewayById(UUID uuid) {
		ResponseObject responseObject;
		try{
			this.gatewayRepository.deleteById(uuid);
			responseObject = UtilityMethods.buildResponseObject(null,
				MessageConstant.SUCCESSFULLY_DELETE_GATEWAY,
				HttpStatus.OK);
		}catch (Exception ex){
			log.error("deleteGatewayById method got exception ->", ex);
			responseObject = UtilityMethods.buildResponseObject(null,
				MessageConstant.FAILED_TO_DELETE_GATEWAY,
				HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseObject;
	}
}
