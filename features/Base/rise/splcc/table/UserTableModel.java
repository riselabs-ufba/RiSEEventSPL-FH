
package rise.splcc.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import rise.splcc.data.User;

public class UserTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_USERID = 0;
		//private static final int COL_PASSWORD = 1;
		private static final int COL_NAMEUSER = 1;
		private static final int COL_TYPEUSER = 2;
		private static final int COL_EMAIL = 3;
		private static final int COL_FILIATION = 4;
		
		// Lista de Valores
		private List<User> rows;
		
		public UserTableModel(List<User> values){
			this.rows = values;
		}
		
		public int getRowCount() {
			return rows.size();
		}
		
		//Quantidade de Colunas
		public int getColumnCount() {
			return 5;
		}
		
		//Preenchimento de cada coluna
			public Object getValueAt(int rowIndex, int columnIndex) {
				User user = rows.get(rowIndex);
				if (columnIndex == COL_USERID) {
					return user.getIdUser();
				}  else if (columnIndex == COL_NAMEUSER) {
					return user.getNameUser();
				} else if (columnIndex == COL_TYPEUSER) {
					return user.getTypeUser().name();
				} else if (columnIndex == COL_EMAIL) {
					return user.getEmail();
				}  else if (columnIndex == COL_FILIATION) {
					return user.getFiliation();
				}
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_USERID:
					coluna = "User Id";
					break;
				case COL_NAMEUSER:
					coluna = "Name";
					break;
				case COL_TYPEUSER:
					coluna = "Type";
					break;
				case COL_EMAIL:
					coluna = "Email";
					break;
				case COL_FILIATION:
					coluna = "Filiation";
					break;
				default:
					throw new IllegalArgumentException("Coluna Invalida!");
				}
				return coluna;
			}
			
			//Tipo de valor da colna
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == COL_USERID) {
					return int.class;
				} else if (columnIndex == COL_NAMEUSER) {
					return String.class;
				} else if (columnIndex == COL_TYPEUSER) {
					return String.class;
				} else if (columnIndex == COL_EMAIL) {
					return String.class;
				}  else if (columnIndex == COL_FILIATION) {
					return String.class;
				}
				return null;
			}
			
			//Abaixo metodos de InserÁ„o, remoÁ„o, update e etc;
			public User get(int row) {
				return rows.get(row);
			}

			public void addUser(User user) {
				rows.add(user);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeUser(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarUser(int indiceLinha, User user) {
				rows.get(indiceLinha).setIdUser(user.getIdUser());
				rows.get(indiceLinha).setNameUser(user.getNameUser());
				rows.get(indiceLinha).setTypeUser(user.getTypeUser());
				rows.get(indiceLinha).setEmail(user.getEmail());
				rows.get(indiceLinha).setFiliation(user.getFiliation());
				fireTableDataChanged();
			}
			
			public void addListaDeUsers(List<User> users) {
				int indice = getRowCount();
				rows.addAll(users);
				fireTableRowsInserted(indice, indice + users.size());
			}
			
			public int retornarIndice(User user) {
				return rows.indexOf(user);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
