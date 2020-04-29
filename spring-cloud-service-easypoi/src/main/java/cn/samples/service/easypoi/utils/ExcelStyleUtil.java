package cn.samples.service.easypoi.utils;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.params.ExcelForEachParams;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.*;

/**
 * @author 王鑫磊
 * @date 2019/11/04
 */
public class ExcelStyleUtil implements IExcelExportStyler {
    private static final short STRING_FORMAT = (short) BuiltinFormats.getBuiltinFormat("TEXT");
    private static final short FONT_SIZE_TEN = 10;
    private static final short FONT_SIZE_ELEVEN = 11;
    private static final short FONT_SIZE_TWELVE = 12;
    /**
     * 大标题样式
     */
    private CellStyle headerStyle;
    /**
     * 每列标题样式
     */
    private CellStyle titleStyle;
    /**
     * 数据行样式
     */
    private CellStyle styles;

    public ExcelStyleUtil(Workbook workbook) {
        this.init(workbook);
    }

    /**
     * 初始化样式
     *
     * @param workbook
     */
    private void init(Workbook workbook) {
        this.headerStyle = initHeaderStyle(workbook);
        this.titleStyle = initTitleStyle(workbook);
        this.styles = initStyles(workbook);
    }

    /**
     * 大标题样式
     *
     * @param color
     * @return
     */
    @Override
    public CellStyle getHeaderStyle(short color) {
        return headerStyle;
    }

    /**
     * 每列标题样式
     *
     * @param color
     * @return
     */
    @Override
    public CellStyle getTitleStyle(short color) {
        return titleStyle;
    }

    /**
     * 数据行样式
     *
     * @param parity 可以用来表示奇偶行
     * @param entity 数据内容
     * @return 样式
     */
    @Override
    public CellStyle getStyles(boolean parity, ExcelExportEntity entity) {
        return styles;
    }

    /**
     * 获取样式方法
     *
     * @param dataRow 数据行
     * @param obj     对象
     * @param data    数据
     */
    @Override
    public CellStyle getStyles(Cell cell, int dataRow, ExcelExportEntity entity, Object obj, Object data) {
        return getStyles(true, entity);
    }

    /**
     * 模板使用的样式设置
     */
    @Override
    public CellStyle getTemplateStyles(boolean isSingle, ExcelForEachParams excelForEachParams) {
        return null;
    }

    /**
     * 初始化--大标题样式
     *
     * @param workbook
     * @return
     */
    private CellStyle initHeaderStyle(Workbook workbook) {
        CellStyle style = getBaseCellStyle(workbook);
        style.setFont(getFont(workbook, FONT_SIZE_TWELVE, true));
        return style;
    }

    /**
     * 初始化--每列标题样式
     *
     * @param workbook
     * @return
     */
    private CellStyle initTitleStyle(Workbook workbook) {
        CellStyle style = getBaseCellStyle(workbook);
        Sheet sheet = workbook.getSheetAt(0);
        // 给想要的单元格添加批注信息
        Drawing patriarch = sheet.createDrawingPatriarch();
        Row row = sheet.getRow(0);
        for (int i = 0; i < 3; i++) {
            Cell cell = row.getCell(i);
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);

            String commentStr = "";
            switch (i) {
                case 0:
                    commentStr = "参照数据字典-卸货地备案，填写卸货地代码";
                    HSSFComment comment1 = (HSSFComment) patriarch.createCellComment(
                            new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 8, 8));

                    //输入批注信息
                    comment1.setString(new HSSFRichTextString(commentStr));
                    //将批注添加到单元格对象中
                    cell.setCellComment(comment1);

                    break;
                case 1:
                    commentStr = "参照数据字典-卸货地备案，填写卸货地代码";
                    HSSFComment comment2 = (HSSFComment) patriarch.createCellComment(
                            new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 8, 8));

                    //输入批注信息
                    comment2.setString(new HSSFRichTextString(commentStr));
                    //将批注添加到单元格对象中
                    cell.setCellComment(comment2);

                    break;
                case 2:
                    commentStr = "日期格式：年/月/日";
                    HSSFComment comment3 = (HSSFComment) patriarch.createCellComment(
                            new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 8, 8));

                    //输入批注信息
                    comment3.setString(new HSSFRichTextString(commentStr));
                    //将批注添加到单元格对象中
                    cell.setCellComment(comment3);

                    break;
            }

        }

        Sheet sheet1 = workbook.getSheetAt(1);
        // 给想要的单元格添加批注信息
        Drawing patriarch1 = sheet1.createDrawingPatriarch();
        Row row1 = sheet1.getRow(0);
        for (int j = 0; j < 8; j++) {
            Cell cell1 = row1.getCell(j);
            String commentStr = "";
            switch (j) {
                case 0:
                    commentStr = "参照数据字典-企业管理，填写统一社会信用代码";
                    HSSFComment comment = (HSSFComment) patriarch1.createCellComment(
                            new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 8));

                    //输入批注信息
                    comment.setString(new HSSFRichTextString(commentStr));
                    //将批注添加到单元格对象中
                    cell1.setCellComment(comment);

                    break;
                case 1:
                    commentStr = "限制16位字符";
                    HSSFComment comment1 = (HSSFComment) patriarch1.createCellComment(
                            new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 8));

                    //输入批注信息
                    comment1.setString(new HSSFRichTextString(commentStr));
                    //将批注添加到单元格对象中
                    cell1.setCellComment(comment1);

                    break;
                case 2:
                    commentStr = "日期格式，年/月/日";
                    HSSFComment comment2 = (HSSFComment) patriarch1.createCellComment(
                            new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 8));

                    //输入批注信息
                    comment2.setString(new HSSFRichTextString(commentStr));
                    //将批注添加到单元格对象中
                    cell1.setCellComment(comment2);

                    break;
                case 3:
                    commentStr = "参照数据字典-箱型表，填写箱型代码";
                    HSSFComment comment3 = (HSSFComment) patriarch1.createCellComment(
                            new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 8));

                    //输入批注信息
                    comment3.setString(new HSSFRichTextString(commentStr));
                    //将批注添加到单元格对象中
                    cell1.setCellComment(comment3);

                    break;
                case 4:
                    commentStr = "参照数据字典-箱型表，填写箱型代码";
                    HSSFComment comment4 = (HSSFComment) patriarch1.createCellComment(
                            new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 8));

                    //输入批注信息
                    comment4.setString(new HSSFRichTextString(commentStr));
                    //将批注添加到单元格对象中
                    cell1.setCellComment(comment4);

                    break;
            }

            if (0 == j || 2 == j || 7 == j) continue;

            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell1.setCellStyle(style);

        }

        style.setFont(getFont(workbook, FONT_SIZE_ELEVEN, false));

        return style;
    }

    /**
     * 初始化--数据行样式
     *
     * @param workbook
     * @return
     */
    private CellStyle initStyles(Workbook workbook) {
        CellStyle style = getBaseCellStyle(workbook);
//        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFont(getFont(workbook, FONT_SIZE_TEN, false));
        style.setDataFormat(STRING_FORMAT);
        return style;
    }

    /**
     * 基础样式
     *
     * @return
     */
    private CellStyle getBaseCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        //下边框
        style.setBorderBottom(BorderStyle.THIN);
        //左边框
        style.setBorderLeft(BorderStyle.THIN);
        //上边框
        style.setBorderTop(BorderStyle.THIN);
        //右边框
        style.setBorderRight(BorderStyle.THIN);
        //水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //上下居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置自动换行
        style.setWrapText(true);
        return style;
    }

    /**
     * 字体样式
     *
     * @param size   字体大小
     * @param isBold 是否加粗
     * @return
     */
    private Font getFont(Workbook workbook, short size, boolean isBold) {
        Font font = workbook.createFont();
        //字体样式
        font.setFontName("宋体");
        //是否加粗
        font.setBold(isBold);
        //字体大小
        font.setFontHeightInPoints(size);
        return font;
    }
}

