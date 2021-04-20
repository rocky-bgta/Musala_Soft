/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 19-April-2021
 * Time: 5:26 AM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.musalasoft.service.impl;

import com.salahin.musalasoft.constant.MessageConstant;
import com.salahin.musalasoft.core.ResponseObject;
import com.salahin.musalasoft.entities.GatewayEntity;
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
    public ResponseObject createGateWay(GatewayEntity gatewayEntity) {

        ResponseObject responseObject;
        try {
            // check p
            gatewayEntity = this.gatewayRepository.save(gatewayEntity);
            responseObject = UtilityMethods.buildResponseObject(gatewayEntity,
                    MessageConstant.SUCCESSFULLY_GATEWAY_CREATED,
                    HttpStatus.OK);
        } catch (Exception ex) {
            log.error("createGateWay method got exception ->", ex);
            responseObject = UtilityMethods.buildResponseObject(null,
                    MessageConstant.FAILED_TO_CREATE_GATEWAY,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseObject;
    }

    @Override
    public ResponseObject updateGateWay(GatewayEntity gatewayEntity) {
        GatewayEntity updatedGatewayEntity;
        ResponseObject responseObject;
        try {
            updatedGatewayEntity = this.gatewayRepository.save(gatewayEntity);
            responseObject = UtilityMethods.buildResponseObject(updatedGatewayEntity,
                    MessageConstant.SUCCESSFULLY_GATEWAY_UPDATED,
                    HttpStatus.OK);
        } catch (Exception ex) {
            log.error("updateGateway method got exception ->", ex);
            responseObject = UtilityMethods.buildResponseObject(null,
                    MessageConstant.FAILED_TO_UPDATE_GATEWAY,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseObject;
    }

    @Override
    public ResponseObject getGatewayById(String id) {
        Optional<GatewayEntity> gatewayEntity;
        GatewayEntity gatewayModel;
        ResponseObject responseObject;
        try {
            gatewayEntity = this.gatewayRepository.findById(id);
            if (gatewayEntity.isPresent()) {
                gatewayModel = modelMapper.map(gatewayEntity.get(), GatewayEntity.class);
                responseObject = UtilityMethods.buildResponseObject(gatewayModel,
                        MessageConstant.SUCCESSFULLY_GET_GATEWAY_BY_PROVIDED_ID,
                        HttpStatus.OK);
            } else {
                responseObject = UtilityMethods.buildResponseObject(null,
                        MessageConstant.REQUESTED_GATEWAY_DOES_NOT_EXIST_NOW,
                        HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            log.error("getTodoById method got exception ->", ex);
            responseObject = UtilityMethods.buildResponseObject(null,
                    MessageConstant.FAILED_TO_UPDATE_GATEWAY,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseObject;
    }

    @Override
    public ResponseObject getAllGateway() {
        ResponseObject responseObject;
        try {
            List<GatewayEntity> gatewayEntities = this.gatewayRepository.findAll();
            responseObject = UtilityMethods.buildResponseObject(gatewayEntities,
                    MessageConstant.SUCCESSFULLY_GET_ALL_GATEWAY,
                    HttpStatus.OK);

        } catch (Exception ex) {
            log.error("getAllTodo method got exception ->", ex);
            responseObject = UtilityMethods.buildResponseObject(null,
                    MessageConstant.FAILED_TO_GET_ALL_GATEWAY,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseObject;
    }

    @Override
    public ResponseObject deleteGatewayById(String id) {
        ResponseObject responseObject;
        try {
            this.gatewayRepository.deleteById(id);
            responseObject = UtilityMethods.buildResponseObject(null,
                    MessageConstant.SUCCESSFULLY_DELETE_GATEWAY,
                    HttpStatus.OK);
        } catch (Exception ex) {
            log.error("deleteGatewayById method got exception ->", ex);
            responseObject = UtilityMethods.buildResponseObject(null,
                    MessageConstant.FAILED_TO_DELETE_GATEWAY,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseObject;
    }
}
