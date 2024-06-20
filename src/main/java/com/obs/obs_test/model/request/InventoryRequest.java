package com.obs.obs_test.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryRequest {

    @NotBlank(message = "item id is mandatory")
    private Long item;

    @NotBlank(message = "type transaction is mandatory (T/W)")
    @Size(max = 1, message = "type length 1 char")
    private String type;

    @NotNull(message = "quantity is mandatory")
    private int qty;
}
