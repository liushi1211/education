package cn.education.web.common.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mark on 2017/9/14 0014.
 */
public final class ExportExcelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportExcelUtil.class);

    public static final void export(String[] headers) {
        Workbook workbook = new XSSFWorkbook();
        createFont(workbook);
        Sheet sheet = workbook.createSheet(); // 创建sheet页
        Row row = sheet.createRow(0);
    }

    /**
     * 创建样式
     *
     * @param workbook
     */
    private static void createFont(Workbook workbook) {
        // 表头
        CellStyle fontStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 14);// 设置字体大小
        fontStyle.setFont(font);
    }

    /**
     * 根据ExcelMapping 生成列头（多行列头）
     *
     * @param sheet 工作簿
     * @param map 每行每个单元格对应的列头信息
     */
    public static final void createTableHeader(XSSFSheet sheet,
            Map<Integer, List<ExcelMapping>> map) {
        int startIndex = 0;// cell起始位置
        int endIndex = 0;// cell终止位置

        for (Map.Entry<Integer, List<ExcelMapping>> entry : map.entrySet()) {
            XSSFRow row = sheet.createRow(entry.getKey());
            List<ExcelMapping> excels = entry.getValue();
            for (int x = 0; x < excels.size(); x++) {
                // 合并单元格
                if (excels.get(x).getCols() > 1) {
                    if (x == 0) {
                        endIndex += excels.get(x).getCols() - 1;
                        CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                        startIndex += excels.get(x).getCols();
                    } else {
                        endIndex += excels.get(x).getCols();
                        CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                        startIndex += excels.get(x).getCols();
                    }
                    XSSFCell cell = row.createCell(startIndex - excels.get(x).getCols());
                    cell.setCellValue(excels.get(x).getHeadTextName());// 设置内容
                    if (excels.get(x).getCellStyle() != null) {
                        cell.setCellStyle(excels.get(x).getCellStyle());// 设置格式
                    }
                } else {
                    XSSFCell cell = row.createCell(x);
                    cell.setCellValue(excels.get(x).getHeadTextName());// 设置内容
                    if (excels.get(x).getCellStyle() != null) {
                        cell.setCellStyle(excels.get(x).getCellStyle());// 设置格式
                    }
                }
            }
        }
    }

    public static void createTableRows(XSSFSheet sheet, Map<Integer, List<ExcelMapping>> map,
            List objectList, Class clazz) throws Exception {
        int maxKey = 0;
        List<ExcelMapping> ems;
        for (Map.Entry<Integer, List<ExcelMapping>> entry : map.entrySet()) {
            if (entry.getKey() > maxKey) {
                maxKey = entry.getKey();
            }
        }
        ems = map.get(maxKey);
        List<Integer> widths = new ArrayList<>(ems.size());
        for (Object obj : objectList) {
            for (int i = 0; i < ems.size(); i++) {
                ExcelMapping em = ems.get(i);
                // 获得get方法
                PropertyDescriptor pd = new PropertyDescriptor(em.getPropertyName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object rtn = getMethod.invoke(obj);
                String value = "";
                if (rtn != null) {
                    value = rtn.toString();
                }
                // 获得最大列宽
                int width = value.getBytes().length * 300;
                // 还未设置，设置当前
                if (widths.size() <= i) {
                    widths.add(width);
                    continue;
                }
                // 比原来大，更新数据
                if (width > widths.get(i)) {
                    widths.set(i, width);
                }
            }
        }
        // 设置列宽
        for (int index = 0; index < widths.size(); index++) {
            Integer width = widths.get(index);
            width = width < 2500 ? 2500 : width + 300;
            width = width > 10000 ? 10000 + 300 : width + 300;
            sheet.setColumnWidth(index, width);
        }
    }

    public static <T, K> void generateExcel(OutputStream outputStream, List<T> srcList,
            Class<K> exportModel) throws Exception {
        if (exportModel == null) {
            return;
        }

        XSSFWorkbook workbook = generateExcelWorkBook(srcList, exportModel);
        try {
            workbook.write(outputStream);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public static <T, K> XSSFWorkbook generateExcelWorkBook(List<T> srcList, Class<K> exportModel) {
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet();
        XSSFCellStyle headStyle = getHeadStyle(workBook);
        XSSFCellStyle bodyStyle = getBodyStyle(workBook);
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);

        XSSFCell cell = null;

        int cellIndex = 0;
        Field[] exportFields = exportModel.getDeclaredFields();
        for (int i = 0; i < exportFields.length; i++) {
            Value titleValue = exportFields[i].getAnnotation(Value.class);
            if (titleValue != null) {
                cell = headRow.createCell(cellIndex++);
                cell.setCellStyle(headStyle);
                cell.setCellValue(titleValue.value());
            }
        }

        for (int i = 0; i < exportFields.length; i++) {
            sheet.setColumnWidth(i, 3000); // 调整第i列宽度
        }

        if (srcList != null && !srcList.isEmpty()) {
            T obj = srcList.get(0);
            for (int i = 0; i < srcList.size(); i++) {
                XSSFRow bodyRow = sheet.createRow(i + 1);
                int j = 0;
                for (Field exportField : exportFields) {
                    // 如果导出字段上面没有Value注解，说明不是导出字段，需要过滤掉
                    if (exportField.getAnnotation(Value.class) == null) {
                        continue;
                    }

                    // 仅针对公共字段
                    try {
                        Field srcfield = null;
                        try {
                            srcfield = obj.getClass().getDeclaredField(exportField.getName());
                        } catch (Exception e) {
                            continue;
                        }

                        srcfield.setAccessible(true);
                        cell = bodyRow.createCell(j);
                        cell.setCellStyle(bodyStyle);
                        String value = srcfield.get(srcList.get(i)) != null
                                ? srcfield.get(srcList.get(i)).toString()
                                : "";
                        cell.setCellValue(value);
                        j++;
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }

        }

        // for (int i = 0; i < exportFields.length; i++) {
        // sheet.autoSizeColumn((short) i); // 调整第i列宽度
        // }

        return workBook;
    }

    /**
     * 设置表头的单元格样式
     *
     * @return
     */
    public static XSSFCellStyle getHeadStyle(XSSFWorkbook wb) {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色
      //  cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
     //   cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

    /**
     * 设置表体的单元格样式
     *
     * @return
     */
    public static XSSFCellStyle getBodyStyle(XSSFWorkbook wb) {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        // font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

    /**
     * 读取excel数据
     * @param is
     * @return
     * @throws IOException
     */
    public static List<Object[]> getImportData(InputStream is) throws IOException {
        List<Object[]> data = new ArrayList<>();
        Workbook wb = new XSSFWorkbook(is);
        //得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        //得到Excel的行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows == 0 || sheet.getRow(0) == null) {
            return data;
        }
        //得到Excel的列数
        int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        // 读取每行的数据
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            Object [] oo = new Object[totalCells];
            // 读取每列的数据
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if(Cell.CELL_TYPE_STRING == cell.getCellType()){
                        oo[c] = cell.getStringCellValue();
                    } else if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()){
                        oo[c] = cell.getNumericCellValue();
                    } else if(Cell.CELL_TYPE_BOOLEAN == cell.getCellType()){
                        oo[c] = cell.getBooleanCellValue();
                    } else {
                        oo[c] = cell.getStringCellValue();
                    }
                }
            }
            data.add(oo);
        }
        return data;
    }

    public static void appendDigital(StringBuilder stringBuilder, Object object){
        if(object == null){
            stringBuilder.append(",");
        } else {
            stringBuilder.append("=\"").append(object).append("\"").append(",");
        }
    }
}
