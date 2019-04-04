package com.fairy.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by yihaibo on 2019-04-02.
 */
@ApiModel(description = "角色")
@Entity
@Table(name = "role")
@Data
public class Role {
    @ApiModelProperty(value = "角色id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "角色名称")
    @NotNull
    @Column(name = "rule_name", length = 20, nullable = false, unique = true)
    private String ruleName;

    @ApiModelProperty(value = "角色描述")
    @Column(name = "rule_desc")
    private String ruleDesc;
}
