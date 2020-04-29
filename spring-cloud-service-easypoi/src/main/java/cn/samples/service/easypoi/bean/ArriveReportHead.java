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
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget("arriveReportHead")
public class ArriveReportHead implements IExcelModel, Serializable {
    /**
     * 海关代码
     */
    @Excel(name = "申报海关", fixedIndex = 0, width = 20)
    private String customsCode;

    /**
     * 卸货地代码
     */
    @Excel(name = "卸货地", fixedIndex = 1, width = 20)
    private String dischargePlace;

    /**
     * 运抵时间
     */
    private Long arriveDate;

    /**
     * 运抵卸货地时间
     */
    @Excel(name = "运抵卸货地时间", format = "yyyy/MM/dd", fixedIndex = 2, width = 30)
    private Date arriveTime;

    // 自定义一个errorMsg接受下面重写IExcelModel接口的get和setErrorMsg方法。
    private String errorMsg;
}
