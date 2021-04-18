package com.salahin.musalasoft.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salahin.musalasoft.entities.GatewayEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class PeripheralModel {

    private Long uid;

    @Size(min=1, max=200)
    @NotNull(message = "Vendor name is mandatory")
    private String vendor;

    @NotNull(message = "Date is mandatory")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdDate;

    @NotNull
    private boolean status;

    private GatewayEntity gateway;
}
