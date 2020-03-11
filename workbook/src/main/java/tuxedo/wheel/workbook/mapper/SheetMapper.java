package tuxedo.wheel.workbook.mapper;

import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class SheetMapper {
    protected abstract String getSheetName();

    protected abstract int getRowCount();

    protected abstract int getColumnCount(int rowIndex);

    protected abstract String getCellValue(int rowIndex, int columnIndex);

    public final void write(Workbook workbook) {
        prepare();
        writeSheet(workbook.createSheet(getSheetName()));
    }

    protected void prepare() {
    }

    private void writeSheet(Sheet sheet) {
        IntStream.range(0, getRowCount()).forEach(rowIndex -> writeRow(sheet.createRow(rowIndex), rowIndex));
    }

    private void writeRow(Row row, int rowIndex) {
        IntStream.range(0, getColumnCount(rowIndex)).forEach(columnIndex -> writeCell(row.createCell(columnIndex), rowIndex, columnIndex));
    }

    private void writeCell(Cell cell, int rowIndex, int columnIndex) {
        cell.setCellValue(getCellValue(rowIndex, columnIndex));
    }
}
