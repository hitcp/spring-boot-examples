package org.microframework.java.util.validate;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Data
public class User implements Serializable {

    @NotNull(message = "主键不能为空")
    private Long id;

    @NotBlank(message = "结算月份不能为空")
    private String settleMonth;

    @NotEmpty(message = "员工工号不能为空")
    private List<String> employeeNums;

    /**
     * 货赔总金额
     */
    private BigDecimal amount;

    private static final long serialVersionUID = 1L;
}