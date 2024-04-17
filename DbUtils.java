package inventory.control.system;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DbUtils {

    public static TableModel resultSetToTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Create column names array
        String[] columnNames = new String[columnCount];
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            columnNames[columnIndex - 1] = metaData.getColumnName(columnIndex);
        }

        // Create data array
        Object[][] data = new Object[0][columnCount];
        int rowCount = 0;
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            // Resize the data array and add the new row
            Object[][] newData = new Object[rowCount + 1][columnCount];
            System.arraycopy(data, 0, newData, 0, rowCount);
            newData[rowCount] = row;
            data = newData;
            rowCount++;
        }

        // Create DefaultTableModel with data and column names
        return new DefaultTableModel(data, columnNames);
    }
}
