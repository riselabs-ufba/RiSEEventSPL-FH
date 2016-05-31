//#if ${RegistrationSpeakerActivity} == "T"
package rise.splcc.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import rise.splcc.data.ActivitySpeaker;
import rise.splcc.data.User;

public class ActivitySpeakerTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_ACTIVITYID = 0;
		private static final int COL_SPEAKERID = 1;
		
		// Lista de Valores
		private List<ActivitySpeaker> rows;
		
		public ActivitySpeakerTableModel(List<ActivitySpeaker> values){
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
				ActivitySpeaker activitySpeakers = rows.get(rowIndex);
				if (columnIndex == COL_ACTIVITYID) {
					return activitySpeakers.getIdActivity();
				}  else if (columnIndex == COL_SPEAKERID) {
					return activitySpeakers.getIdSpeaker();
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
				case COL_SPEAKERID:
					coluna = "SpeakerID";
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
				} else if (columnIndex == COL_SPEAKERID) {
					return int.class;
				}
				return null;
			}
			
			//Abaixo metodos de InserÁ„o, remoÁ„o, update e etc;
			public ActivitySpeaker get(int row) {
				return rows.get(row);
			}

			public void addActivitySpeaker(ActivitySpeaker activitySpeaker) {
				rows.add(activitySpeaker);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeActivitySpeaker(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarActivitySpeaker(int indiceLinha, ActivitySpeaker activitySpeaker) {
				rows.get(indiceLinha).setIdActivity(activitySpeaker.getIdActivity());
				rows.get(indiceLinha).setIdSpeaker(activitySpeaker.getIdSpeaker());		
				fireTableDataChanged();
			}
			
			public void addListaDeActivitySpeakers(List<ActivitySpeaker> activitySpeakers) {
				int indice = getRowCount();
				rows.addAll(activitySpeakers);
				fireTableRowsInserted(indice, indice + activitySpeakers.size());
			}
			
			public int retornarIndice(ActivitySpeaker activitySpeaker) {
				return rows.indexOf(activitySpeaker);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif