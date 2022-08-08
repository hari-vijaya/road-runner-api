package com.codebasics.milkfarm.model.request;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerRequest {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 15)
    private String customerCode;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String customerName;


    private String mobileNo;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String routeName;

    @Size(min = 1, max = 50)
    private String village;

    private String supervisor;
}
