//#if ${RegistrationUserActivity} == "T"
package rise.splcc.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import rise.splcc.data.ActivityUser;

public class ActivityUserTableModel extends AbstractTableModel{
	// Nome das Colunas
	
	private static final int COL_ACTIVITYID = 0;
	private static final int COL_USERID = 1;
	
	// Lista de Valores
	private List<ActivityUser> rows;
	
	public ActivityUserTableModel(List<ActivityUser> values){
		this.rows = values;
	}
	
	public int getRowCount() {
		return rows.size();
	}
	
	//Quantidade de Colunas
	public int getColumnCount() {
		return 2;
	}
	
	//Preenchimento de cada coluna
		public Object getValueAt(int rowIndex, int columnIndex) {
			ActivityUser activityUser = rows.get(rowIndex);
			if (columnIndex == COL_ACTIVITYID) {
				return activityUser.getIdActivity();
			}  else if (columnIndex == COL_USERID) {
				return activityUser.getIdUser();
			} 
			return null;
		}
		
		@Override
		public String getColumnName(int column) {
			String coluna = "";
			switch (column) {
			case COL_ACTIVITYID:
				coluna = "ActivityID";
				break;
			case COL_USERID:
				coluna = "UserID";
				break;  
			default:
				throw new IllegalArgumentException("Coluna Invalida!");
			}
			return coluna;
		}
		
		//Tipo de valor da colna
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex == COL_ACTIVITYID) {
				return int.class;
			} else if (columnIndex == COL_USERID) {
				return int.class;
			}
			return null;
		}
		
		//Abaixo metodos de InserÁ„o, remoÁ„o, update e etc;
		public ActivityUser get(int row) {
			return rows.get(row);
		}

		public void addActivityUser(ActivityUser activityUser) {
			rows.add(activityUser);
			int ultimoIndice = getRowCount() - 1;
			fireTableRowsInserted(ultimoIndice, ultimoIndice);
		}
		
		public void removeActivityUser(int indiceLinha) {
			rows.remove(indiceLinha);
			fireTableRowsDeleted(indiceLinha, indiceLinha);
		}
		
		public void alterarActivityUser(int indiceLinha, ActivityUser activityUser) {
			rows.get(indiceLinha).setIdActivity(activityUser.getIdActivity());
			rows.get(indiceLinha).setIdUser(activityUser.getIdUser());		
			fireTableDataChanged();
		}
		
		public void addListaDeActivityUsers(List<ActivityUser> activityUsers) {
			int indice = getRowCount();
			rows.addAll(activityUsers);
			fireTableRowsInserted(indice, indice + activityUsers.size());
		}
		
		public int retornarIndice(ActivityUser activityUser) {
			return rows.indexOf(activityUser);
		}
		
		// Remove todos os registros.
		public void limpar() {
			rows.clear();

			// Notifica a mudança.
			fireTableDataChanged();
		}
}
//#endif