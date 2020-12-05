package com.entian.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jianggangli
 * @version 1.0 2020/11/30 14:31
 * 功能:
 */
@Data
@ApiModel("用户")
public class UserDto {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户账号")
    private String account;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户邮箱")
    private String email;
}

