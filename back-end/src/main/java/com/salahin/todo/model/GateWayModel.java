package com.salahin.todo.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class GateWayModel {

    private UUID id;

    @Size(min=1, max=100)
    @NotNull(message = "Name is mandatory")
    private String name;

    @Size(max=500)
    private String ip4address;
}
