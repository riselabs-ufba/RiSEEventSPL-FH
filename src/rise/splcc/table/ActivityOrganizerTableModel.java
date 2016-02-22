//#if ${RegistrationOrganizerActivity} == "T"
package rise.splcc.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import rise.splcc.data.ActivityOrganizer;

public class ActivityOrganizerTableModel extends AbstractTableModel{
	// Nome das Colunas
	
			private static final int COL_ACTIVITYID = 0;
			private static final int COL_ORGANIZERID = 1;
			
			// Lista de Valores
			private List<ActivityOrganizer> rows;
			
			public ActivityOrganizerTableModel(List<ActivityOrganizer> values){
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
					ActivityOrganizer activityOrganizer = rows.get(rowIndex);
					if (columnIndex == COL_ACTIVITYID) {
						return activityOrganizer.getIdActivity();
					}  else if (columnIndex == COL_ORGANIZERID) {
						return activityOrganizer.getIdOrganizer();
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
					case COL_ORGANIZERID:
						coluna = "OrganizerID";
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
					} else if (columnIndex == COL_ORGANIZERID) {
						return int.class;
					}
					return null;
				}
				
				//Abaixo metodos de InserÁ„o, remoÁ„o, update e etc;
				public ActivityOrganizer get(int row) {
					return rows.get(row);
				}

				public void addActivityOrganizer(ActivityOrganizer activityOrganizer) {
					rows.add(activityOrganizer);
					int ultimoIndice = getRowCount() - 1;
					fireTableRowsInserted(ultimoIndice, ultimoIndice);
				}
				
				public void removeActivityOrganizer(int indiceLinha) {
					rows.remove(indiceLinha);
					fireTableRowsDeleted(indiceLinha, indiceLinha);
				}
				
				public void alterarActivityOrganizer(int indiceLinha, ActivityOrganizer activityOrganizer) {
					rows.get(indiceLinha).setIdActivity(activityOrganizer.getIdActivity());
					rows.get(indiceLinha).setIdOrganizer(activityOrganizer.getIdOrganizer());		
					fireTableDataChanged();
				}
				
				public void addListaDeActivityOrganizers(List<ActivityOrganizer> activityOrganizers) {
					int indice = getRowCount();
					rows.addAll(activityOrganizers);
					fireTableRowsInserted(indice, indice + activityOrganizers.size());
				}
				
				public int retornarIndice(ActivityOrganizer activityOrganizer) {
					return rows.indexOf(activityOrganizer);
				}
				
				// Remove todos os registros.
				public void limpar() {
					rows.clear();

					// Notifica a mudança.
					fireTableDataChanged();
				}
}
//#endif