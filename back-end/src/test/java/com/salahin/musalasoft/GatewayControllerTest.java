package com.salahin.musalasoft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salahin.musalasoft.constant.MessageConstant;
import com.salahin.musalasoft.controller.GatewayController;
import com.salahin.musalasoft.core.ResponseObject;
import com.salahin.musalasoft.entities.GatewayEntity;
import com.salahin.musalasoft.entities.PeripheralEntity;
import com.salahin.musalasoft.service.GatewayService;
import com.salahin.musalasoft.utilities.UtilityMethods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@WebMvcTest(value = GatewayController.class)
@RunWith(SpringRunner.class)
public class GatewayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GatewayService gatewayService;

    @Test
    public void testCreateGateway() throws Exception{
        GatewayEntity mockGateway = new GatewayEntity();
        mockGateway.setId("402880eb78ef95200178ef9600ca0000");
        mockGateway.setName("KKK");
        mockGateway.setIpv4address("255.255.255.255");

        PeripheralEntity mockPeripheral = new PeripheralEntity();
        mockPeripheral.setUid(1L);
        mockPeripheral.setVendor("Computer 555 source");
        mockPeripheral.setStatus(true);
        mockPeripheral.setCreatedDate(null);
        mockGateway.getPeripheralList().add(mockPeripheral);

        ResponseObject responseObject = UtilityMethods.buildResponseObject(mockGateway,
                MessageConstant.SUCCESSFULLY_GATEWAY_CREATED,
                HttpStatus.OK);


        String inputInJson = this.mapToJson(responseObject);

        String URI = "/api/gateway/create";

        Mockito.when(gatewayService.createGateway(Mockito.any(GatewayEntity.class))).thenReturn(responseObject);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    public void testUpdateGateway() throws Exception{
        GatewayEntity mockGateway = new GatewayEntity();
        mockGateway.setId("402880eb78ef95200178ef9600ca0000");
        mockGateway.setName("vpn-up");
        mockGateway.setIpv4address("255.255.255.255");

        PeripheralEntity mockPeripheral = new PeripheralEntity();
        mockPeripheral.setUid(1L);
        mockPeripheral.setVendor("Computer source");
        mockPeripheral.setStatus(true);
        mockPeripheral.setCreatedDate(null);
        mockGateway.getPeripheralList().add(mockPeripheral);

        ResponseObject responseObject = UtilityMethods.buildResponseObject(mockGateway,
                MessageConstant.SUCCESSFULLY_GATEWAY_UPDATED,
                HttpStatus.OK);


        String inputInJson = this.mapToJson(responseObject);

        String URI = "/api/gateway/update";

        Mockito.when(gatewayService.updateGateway(Mockito.any(GatewayEntity.class))).thenReturn(responseObject);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    public void testGetAllGateway() throws Exception{
        GatewayEntity mockGateway = new GatewayEntity();
        mockGateway.setId("402880eb78ef95200178ef9600ca0000");
        mockGateway.setName("KKK");
        mockGateway.setIpv4address("255.255.255.255");

        PeripheralEntity mockPeripheral1 = new PeripheralEntity();
        mockPeripheral1.setUid(1L);
        mockPeripheral1.setVendor("Computer 555 source");
        mockPeripheral1.setStatus(true);
        mockPeripheral1.setCreatedDate(null);

        PeripheralEntity mockPeripheral2 = new PeripheralEntity();
        mockPeripheral2.setUid(1L);
        mockPeripheral2.setVendor("Computer 555 source");
        mockPeripheral2.setStatus(true);
        mockPeripheral2.setCreatedDate(null);

        mockGateway.getPeripheralList().add(mockPeripheral1);
        mockGateway.getPeripheralList().add(mockPeripheral2);

        ResponseObject responseObject = UtilityMethods.buildResponseObject(mockGateway,
                MessageConstant.SUCCESSFULLY_GET_ALL_GATEWAY,
                HttpStatus.OK);

        String inputInJson = this.mapToJson(responseObject);

        String URI = "/api/gateway/list";

        Mockito.when(gatewayService.getAllGateway()).thenReturn(responseObject);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetGatewayById() throws Exception {
        GatewayEntity mockGateway = new GatewayEntity();
        mockGateway.setId("402880eb78ef95200178ef9600ca0000");
        mockGateway.setName("KKK");
        mockGateway.setIpv4address("255.255.255.255");

        ResponseObject responseObject =  UtilityMethods.buildResponseObject(mockGateway,
                MessageConstant.SUCCESSFULLY_GET_GATEWAY_BY_PROVIDED_ID,
                HttpStatus.OK);

        Mockito.when(gatewayService.getGatewayById(Mockito.anyString())).thenReturn(responseObject);

        String URI = "/api/gateway/get/402880eb78ef95200178ef9600ca0000";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(responseObject);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void testDeleteGatewayById() throws Exception {

        ResponseObject responseObject =  UtilityMethods.buildResponseObject(null,
                MessageConstant.SUCCESSFULLY_DELETE_GATEWAY,
                HttpStatus.OK);

        Mockito.when(gatewayService.deleteGatewayById(Mockito.anyString())).thenReturn(responseObject);

        String URI = "/api/gateway/delete/402880eb78ef95200178ef9600ca0000";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(responseObject);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }


    /**
     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     */
    private String mapToJson(Object object) throws JsonProcessingException {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
