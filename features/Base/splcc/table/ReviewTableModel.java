//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package rise.splcc.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import rise.splcc.data.Review;

public class ReviewTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_REVIEWID = 0;
		private static final int COL_SUBMISSIONID = 1;
		private static final int COL_STATUS = 2;
		private static final int COL_DATE = 3;
		private static final int COL_DESCRIPTION = 4;
		
		// Lista de Valores
		private List<Review> rows;
		
		public ReviewTableModel(List<Review> values){
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
				Review review = rows.get(rowIndex);
				if (columnIndex == COL_REVIEWID) {
					return review.getIdReview();
				} else if (columnIndex == COL_SUBMISSIONID) {
					return review.getIdSubmission();
				} else if (columnIndex == COL_STATUS) {
					return review.getStatus();
				} else if (columnIndex == COL_DATE) {
					return review.getDate();
				} else if (columnIndex == COL_DESCRIPTION) {
					return review.getDescription();
				} 
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_REVIEWID:
					coluna = "Review Id";
					break;
				case COL_SUBMISSIONID:
					coluna = "Submission Id";
					break;
				case COL_STATUS:
					coluna = "Status";
					break;
				case COL_DATE:
					coluna = "Date";
					break;
				case COL_DESCRIPTION:
					coluna = "Description";
					break;
				default:
					throw new IllegalArgumentException("Coluna Invalida!");
				}
				return coluna;
			}
			
			//Tipo de valor da colna
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == COL_REVIEWID) {
					return int.class;
				} else if (columnIndex == COL_SUBMISSIONID) {
					return int.class;
				} else if (columnIndex == COL_STATUS) {
					return String.class;
				} else if (columnIndex == COL_DATE) {
					return String.class;
				} else if (columnIndex == COL_DESCRIPTION) {
					return String.class;
				}
				return null;
			}
			
			//Abaixo metodos de InserÁ„o, remoÁ„o, update e etc;
			public Review get(int row) {
				return rows.get(row);
			}

			public void addReview(Review review) {
				rows.add(review);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeReview(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarReview(int indiceLinha, Review review) {
				rows.get(indiceLinha).setStatus(review.getStatus());
				rows.get(indiceLinha).setDate(review.getDate());
				rows.get(indiceLinha).setDescription(review.getDescription());	
				fireTableDataChanged();
			}
			
			public void addListaDeReviewos(List<Review> reviews) {
				int indice = getRowCount();
				rows.addAll(reviews);
				fireTableRowsInserted(indice, indice + reviews.size());
			}
			
			public int retornarIndice(Review review) {
				return rows.indexOf(review);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif