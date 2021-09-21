package utils;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * 合计行设置样式
 *
 * @author wcl
 */
@Slf4j
public class TotalRowHandler implements CellWriteHandler {

    /**
     * 开始添加样式的行下标
     */
    private Integer startRow;

    public TotalRowHandler(Integer startRow) {
        this.startRow = startRow;
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head,
                                 Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head,
                                Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData,
                                       Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList,
                                 Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        // 非表头项
        if (!isHead) {
            if (cell != null) {
                // 从数据开始，表头不会读取
                if (relativeRowIndex >= startRow) {
                    if (head != null) {
                        log.debug("表头====》" + head.getHeadNameList().get(0));
                    }
                    if (CellType.STRING.name().equals(cell.getCellTypeEnum().name())) {
                        log.debug("单元格数据===》" + cell.getStringCellValue());
                    } else if (CellType.NUMERIC.name().equals(cell.getCellTypeEnum().name())) {
                        log.debug("单元格数据===》" + cell.getNumericCellValue());
                    }
                    log.info("");
                    // 设置样式
                    Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
                    // 设置字体样式
                    WriteCellStyle writeCellStyle = new WriteCellStyle();
                    WriteFont headWriteFont = new WriteFont();
                    headWriteFont.setFontName("宋体");
                    headWriteFont.setFontHeightInPoints((short) 14);
                    headWriteFont.setBold(true);
                    writeCellStyle.setWriteFont(headWriteFont);
                    // 设置背景色
                    writeCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

                    // 样式写入单元格
                    CellStyle cellStyle = StyleUtil.buildHeadCellStyle(workbook, writeCellStyle);
                    cell.setCellStyle(cellStyle);
                }
            }
        }

    }
}
