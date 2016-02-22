package rise.splcc.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import rise.splcc.data.Registration;

public class RegistrationTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_IDREGISTRATION = 0;
		private static final int COL_IDUSER = 1;
		private static final int COL_TOTALVALUE = 2;
		
		// Lista de Valores
		private List<Registration> rows;
		
		public RegistrationTableModel(List<Registration> values){
			this.rows = values;
		}
		
		public int getRowCount() {
			return rows.size();
		}
		
		//Quantidade de Colunas
		public int getColumnCount() {
			return 3;
		}
		
		//Preenchimento de cada coluna
			public Object getValueAt(int rowIndex, int columnIndex) {
				Registration registration = rows.get(rowIndex);
				if (columnIndex == COL_IDREGISTRATION) {
					return registration.getIdRegistration();
				}  else if (columnIndex == COL_IDUSER) {
					return registration.getIdUser();
				}	else if (columnIndex == COL_TOTALVALUE) {
					return registration.getTotalValue();
				}
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_IDREGISTRATION:
					coluna = "Registration Id";
					break;
				case COL_IDUSER:
					coluna = "User Id";
					break;
				case COL_TOTALVALUE:
					coluna = "Total Value";
					break;
				default:
					throw new IllegalArgumentException("Coluna Invalida!");
				}
				return coluna;
			}
			
			//Tipo de valor da colna
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == COL_IDREGISTRATION) {
					return int.class;
				} else if (columnIndex == COL_IDUSER) {
					return int.class;
				}else if (columnIndex == COL_TOTALVALUE) {
					return float.class;
				}
				return null;
			}
			
			//Abaixo metodos de InserÁ„o, remoÁ„o, update e etc;
			public Registration get(int row) {
				return rows.get(row);
			}

			public void addRegistration(Registration registration) {
				rows.add(registration);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeRegistration(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarReviewer(int indiceLinha, Registration registration) {
				rows.get(indiceLinha).setIdRegistration(registration.getIdRegistration());
				rows.get(indiceLinha).setIdUser(registration.getIdUser());
				rows.get(indiceLinha).setTotalValue(registration.getTotalValue());
				fireTableDataChanged();
			}
			
			public void addListaDeReviewers(List<Registration> registrations) {
				int indice = getRowCount();
				rows.addAll(registrations);
				fireTableRowsInserted(indice, indice + registrations.size());
			}
			
			public int retornarIndice(Registration registration) {
				return rows.indexOf(registration);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
