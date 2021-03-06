package cn.education.web.common.util;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * @author Mark
 */
public class ExcelMapping {
    /**
     * 列头（标题）名
     */
    private String headTextName;
    /**
     * 对应字段名
     */
    private String propertyName;
    /**
     * 合并单元格数
     */
    private Integer cols;
    /**
     * 单元格样式
     */
    private XSSFCellStyle cellStyle;

    public ExcelMapping() {
    }

    public ExcelMapping(String headTextName, String propertyName) {
        this.headTextName = headTextName;
        this.propertyName = propertyName;
    }


    public ExcelMapping(String headTextName, String propertyName, Integer cols) {
        super();
        this.headTextName = headTextName;
        this.propertyName = propertyName;
        this.cols = cols;
    }

    public String getHeadTextName() {
        return headTextName;
    }

    public void setHeadTextName(String headTextName) {
        this.headTextName = headTextName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public XSSFCellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(XSSFCellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }
}
