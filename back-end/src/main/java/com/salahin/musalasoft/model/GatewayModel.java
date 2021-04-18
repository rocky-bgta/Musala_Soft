package com.salahin.musalasoft.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class GatewayModel {

    private UUID id;

    @Size(min=1, max=100)
    @NotNull(message = "Gateway name is mandatory")
    private String name;

    @Size(max=150)
    private String ipv4address;
}
