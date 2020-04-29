package cn.samples.service.easypoi.bean;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget("arriveReportBody")
public class ArriveReportBody implements IExcelModel, Serializable {

    /**
     * 发货企业编号
     */
    @Excel(name = "发货企业", fixedIndex = 0, width = 20)
    private String enterprisesId;

    /**
     * 发运计划编号
     */
    @Excel(name = "发运计划编号", fixedIndex = 1, width = 20)
    private String shipmentPlanNo;

    /**
     * 发运时间
     */
    @Excel(name = "发运时间", format = "yyyy/MM/dd", fixedIndex = 2, width = 20)
    private Date shipmentTime;

    /**
     * 发运时间（数据库字段）
     */
    private Long shipmentDate;

    /**
     * 集装箱号
     */
    @Excel(name = "集装箱号", fixedIndex = 3, width = 20)
    private String contaNo;

    /**
     * 箱型
     */
    @Excel(name = "箱型", fixedIndex = 4, width = 20)
    private String contaType;

    /**
     * 件数
     */
    @Excel(name = "件数（仅支持纯数字）", fixedIndex = 5, width = 30)
    private Integer packNo;

    /**
     * 重量
     */
    @Excel(name = "货物重量/KG（仅支持纯数字）", fixedIndex = 6, width = 30)
    private Double grossWt;

    /**
     * 封志号
     */
    @Excel(name = "封志号", fixedIndex = 7, width = 20)
    private String sealNo;

    // 自定义一个errorMsg接受下面重写IExcelModel接口的get和setErrorMsg方法。
    private String errorMsg;
}
