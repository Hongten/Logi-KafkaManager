package com.xiaojukeji.know.streaming.km.common.bean.vo.health;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zengqiao
 * @date 22/03/01
 */
@Data
@ApiModel(description = "健康检查配置")
public class HealthCheckConfigVO {
    /**
     * @see com.xiaojukeji.know.streaming.km.common.enums.health.HealthCheckDimensionEnum
     */
    @ApiModelProperty(value="检查维度Code", example = "1")
    private Integer dimensionCode;

    @ApiModelProperty(value="检查维度名称", example = "Broker")
    private String dimensionName;

    @ApiModelProperty(value="配置组", example = "HEALTH")
    private String configGroup;

    @ApiModelProperty(value="检查名称", example = "Group延迟")
    private String configName;

    @ApiModelProperty(value="检查项", example = "Group延迟")
    private String configItem;

    @ApiModelProperty(value="检查说明", example = "Group延迟")
    private String configDesc;

    @ApiModelProperty(value="权重", example = "10")
    private Float weight;

    @ApiModelProperty(value="检查配置", example = "100")
    private String value;
}
