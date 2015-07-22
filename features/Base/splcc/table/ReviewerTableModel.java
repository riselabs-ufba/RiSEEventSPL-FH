//#if ${Reviewer} == "T"
package rise.splcc.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import rise.splcc.data.Reviewer;

public class ReviewerTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_USERID = 0;
		//private static final int COL_PASSWORD = 1;
		private static final int COL_NAMEUSER = 1;
		private static final int COL_TYPEUSER = 2;
		private static final int COL_EMAIL = 3;
		private static final int COL_KNOWLEDGEAREA = 4;
		
		// Lista de Valores
		private List<Reviewer> rows;
		
		public ReviewerTableModel(List<Reviewer> values){
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
				Reviewer reviewer = rows.get(rowIndex);
				if (columnIndex == COL_USERID) {
					return reviewer.getIdUser();
				}  else if (columnIndex == COL_NAMEUSER) {
					return reviewer.getNameUser();
				} else if (columnIndex == COL_TYPEUSER) {
					return reviewer.getTypeUser().name();
				} else if (columnIndex == COL_EMAIL) {
					return reviewer.getEmail();
				}  else if (columnIndex == COL_KNOWLEDGEAREA) {
					return reviewer.getKnowledgeArea();
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
				case COL_KNOWLEDGEAREA:
					coluna = "Knowledge Area";
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
				}  else if (columnIndex == COL_KNOWLEDGEAREA) {
					return String.class;
				}
				return null;
			}
			
			//Abaixo metodos de InserÁ„o, remoÁ„o, update e etc;
			public Reviewer get(int row) {
				return rows.get(row);
			}

			public void addReviewer(Reviewer reviewer) {
				rows.add(reviewer);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeReviewer(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarReviewer(int indiceLinha, Reviewer reviewer) {
				rows.get(indiceLinha).setIdUser(reviewer.getIdUser());
				rows.get(indiceLinha).setNameUser(reviewer.getNameUser());
				rows.get(indiceLinha).setTypeUser(reviewer.getTypeUser());
				rows.get(indiceLinha).setEmail(reviewer.getEmail());
				rows.get(indiceLinha).setKnowledgeArea(reviewer.getKnowledgeArea());
				fireTableDataChanged();
			}
			
			public void addListaDeReviewers(List<Reviewer> reviewers) {
				int indice = getRowCount();
				rows.addAll(reviewers);
				fireTableRowsInserted(indice, indice + reviewers.size());
			}
			
			public int retornarIndice(Reviewer reviewer) {
				return rows.indexOf(reviewer);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif