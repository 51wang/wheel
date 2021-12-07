package tuxedo.wheel.workbook.mapper;

public abstract class StandardSheetMapper extends SheetMapper {
    private ColumnMapper[] columnMappers;

    protected abstract int getDataRowCount();

    protected abstract ColumnMapper[] getColumnMappers();

    @Override
    protected final int getRowCount() {
        return getDataRowCount() + 1;
    }

    @Override
    protected final int getColumnCount(int rowIndex) {
        return columnMappers.length;
    }

    @Override
    protected final String getCellValue(int rowIndex, int columnIndex) {
        return columnMappers[columnIndex].getValue(rowIndex);
    }

    @Override
    protected void prepare() {
        columnMappers = getColumnMappers();
    }

    protected interface ColumnMapper {
        String getName();

        String getData(int dataRowIndex);

        default String getValue(int rowIndex) {
            return rowIndex == 0 ? getName() : getData(rowIndex - 1);
        }
    }
}
