package cn.samples.service.easypoi.controller;

import cn.afterturn.easypoi.cache.manager.POICacheManager;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelXorHtmlUtil;
import cn.afterturn.easypoi.excel.entity.ExcelToHtmlParams;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.samples.service.easypoi.bean.ArriveReportBody;
import cn.samples.service.easypoi.bean.ArriveReportHead;
import cn.samples.service.easypoi.utils.ExcelStyleUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/easypoi")
public class EasyPoiController {

    /**
     * 导出excel
     */
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws Exception {
        Workbook workBook = null;
        try {
            // 创建excel的第一个sheet
            ExportParams epOne = new ExportParams();
            epOne.setSheetName("主单");

            // 创建sheet1使用得map
            Map dataMap1 = new HashMap<>();
            // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
            dataMap1.put("title", epOne);
            // 模版导出对应得实体类型
            dataMap1.put("entity", ArriveReportHead.class);
            // sheet中要填充得数据
            dataMap1.put("data", new ArrayList<>());

            // 创建excel的第二个sheet
            ExportParams epTwo = new ExportParams();
            epTwo.setSheetName("明细单");

            // 创建sheet1使用得map
            Map dataMap2 = new HashMap<>();
            // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
            dataMap2.put("title", epTwo);
            dataMap2.put("entity", ArriveReportBody.class);
            dataMap2.put("data", new ArrayList<>());

            // 将sheet1和sheet2使用得map进行包装
            List<Map<String, Object>> sheetsList = new ArrayList<>();
            sheetsList.add(dataMap1);
            sheetsList.add(dataMap2);

            // 执行方法
            workBook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
            new ExcelStyleUtil(workBook);

            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("模板-导入运抵报告", "UTF-8") + ".xls");
            //编码
            response.setCharacterEncoding("UTF-8");
            workBook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new Exception("导出异常");
        } finally {
            if (workBook != null) {
                try {
                    workBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/importExcel")
    public void importExcel(HttpServletResponse response) throws IOException {
        File file = new File("C:\\Users\\zp&xyh\\Downloads\\test.xls");
        InputStream is = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(is);
        HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();
        // 水平居中
        style.setAlignment(HorizontalAlignment.LEFT);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = "导出错误excel" + sdf.format(new Date()) + ".xls";

        response.addHeader("Content-Disposition", "attachment;filename=" + new String(dateStr.getBytes("gb2312"), "ISO8859-1"));
        response.setContentType("application/octet-stream;charset=UTF-8");
        workbook.write(response.getOutputStream());
    }

    @GetMapping("/get")
    public void get() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                "doc/delete-test.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("manmark", "男人");
        map.put("letest", "123456789");
        map.put("fntest", "822");
        map.put("fdtest", new Date());

        List<Map<String, Object>> mapList = new ArrayList<>(4);
        List<Map<String, Object>> entityList = new ArrayList<>(4);
        List<Map<String, Object>> sitest = new ArrayList<>(4);

        for (int i = 1; i <= 4; i++) {
            Map<String, Object> temp = new HashMap<>(4);
            temp.put("id", i);
            temp.put("name", "张三" + i);
            temp.put("sex", i > 2 ? 1 : 0);
            mapList.add(temp);

            Map<String, Object> temp2 = new HashMap<>(4);
            temp2.put("accountType", i);
            temp2.put("projectName", "张三" + i);
            temp2.put("approvedAmount", 10 * i);
            temp2.put("amountApplied", 100 * i);
            entityList.add(temp2);

            Map<String, Object> temp3 = new HashMap<>(4);
            temp3.put("si", i);
            sitest.add(temp3);
        }
        map.put("maplist", mapList);
        map.put("entitylist", entityList);
        map.put("sitest", sitest);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("D:/home/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/home/excel/delete-test-gen.xls");
        workbook.write(fos);
        fos.close();

    }


    /**
     * excel预览
     */
    @GetMapping("/showExcel")
    public void showExcel(HttpServletResponse response)throws IOException {
        ExcelToHtmlParams params = new ExcelToHtmlParams(WorkbookFactory.create(POICacheManager.getFile("doc/delete-test.xlsx")), 2);

        String rspStr = ExcelXorHtmlUtil.excelToHtml(params);
        response.getOutputStream().write(rspStr.getBytes("GBK"));
    }

    /**
     * word预览
     */
    @GetMapping("/showWord")
    public void showWord(HttpServletResponse response)throws IOException {
        ExcelToHtmlParams params = new ExcelToHtmlParams(WorkbookFactory.create(POICacheManager.getFile("doc/delete-test.xlsx")));

        String rspStr = ExcelXorHtmlUtil.excelToHtml(params);
        response.getOutputStream().write(rspStr.getBytes("GBK"));
    }
}
