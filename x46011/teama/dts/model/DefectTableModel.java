package x46011.teama.dts.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


/**
 * The DefectTableModel class shows the Defect data in a table.
 * 
 * @author Amit Dhamija
 * @version 1.0
 */
public class DefectTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { Constants.DEFECT_ID, Constants.DEFECT_DATE, Constants.DEFECT_SUBMITTER, Constants.DEFECT_ASSIGNEE, Constants.DEFECT_PRIORITY, Constants.DEFECT_STATUS, Constants.DEFECT_SUMMARY };
	private Object[][] rowData;
	
	public DefectTableModel(ArrayList<Defect> defectList) {
		super();
		
		rowData = new Object[defectList.size()][];
		
		for (int i = 0; i < rowData.length; i++) {
			rowData[i] = setRowData(defectList.get(i));
		}
	}
	
	private Object[] setRowData(Defect defect) {
		Object[] rowData = {defect.getId(), defect.getDate(), defect.getSubmitter().getName(), defect.getAssignee().getName(), defect.getPriority(), defect.getStatus(), defect.getSummary()};
		return rowData;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return rowData.length;
	}
	
	@Override
	public String getColumnName (int column) { 
		return columnNames [column]; 
	}

	@Override
	public Class<?> getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
	
	@Override
	public Object getValueAt (int row, int column) {
		return rowData [row][column];
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
    }
}