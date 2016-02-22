//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package rise.splcc.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import rise.splcc.data.Submission;
import rise.splcc.data.Submission.TypeSubmission;

public class SubmissionTableModel extends AbstractTableModel{

	// Nome das Colunas
	
		private static final int COL_SUBMISSIONID = 0;
		private static final int COL_ACTIVITYID = 1;
		private static final int COL_TYPE = 2;
		private static final int COL_ABSTRACT = 3;
		private static final int COL_KEYWORDS = 4;
		
		// Lista de Valores
		private List<Submission> rows;
		
		public SubmissionTableModel(List<Submission> values){
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
				Submission submission = rows.get(rowIndex);
				if (columnIndex == COL_SUBMISSIONID) {
					return submission.getIdSubmission();
				} else if (columnIndex == COL_ACTIVITYID) {
					return submission.getIdActivity();
				} else if (columnIndex == COL_TYPE) {
					return submission.getType();
				} else if (columnIndex == COL_ABSTRACT) {
					return submission.getAbstractPaper();
				} else if (columnIndex == COL_KEYWORDS) {
					return submission.getKeywords();
				} 
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				String coluna = "";
				switch (column) {
				case COL_SUBMISSIONID:
					coluna = "Submission Id";
					break;
				case COL_ACTIVITYID:
					coluna = "Activity Id";
					break;
				case COL_TYPE:
					coluna = "Type";
					break;
				case COL_ABSTRACT:
					coluna = "Abstract";
					break;
				case COL_KEYWORDS:
					coluna = "Keywords";
					break;
				default:
					throw new IllegalArgumentException("Coluna Invalida!");
				}
				return coluna;
			}
			
			//Tipo de valor da colna
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == COL_SUBMISSIONID) {
					return int.class;
				} else if (columnIndex == COL_ACTIVITYID) {
					return int.class;
				} else if (columnIndex == COL_TYPE) {
					return TypeSubmission.class;
				} else if (columnIndex == COL_ABSTRACT) {
					return String.class;
				} else if (columnIndex == COL_KEYWORDS) {
					return String.class;
				}
				return null;
			}
			
			//Abaixo metodos de InserÁ„o, remoÁ„o, update e etc;
			public Submission get(int row) {
				return rows.get(row);
			}

			public void addSubmission(Submission submission) {
				rows.add(submission);
				int ultimoIndice = getRowCount() - 1;
				fireTableRowsInserted(ultimoIndice, ultimoIndice);
			}
			
			public void removeSubmission(int indiceLinha) {
				rows.remove(indiceLinha);
				fireTableRowsDeleted(indiceLinha, indiceLinha);
			}
			
			public void alterarSubmission(int indiceLinha, Submission submission) {
				rows.get(indiceLinha).setType(submission.getType());
				rows.get(indiceLinha).setKeywords(submission.getKeywords());
				rows.get(indiceLinha).setAbstractPaper(submission.getAbstractPaper());	
				fireTableDataChanged();
			}
			
			public void addListaDeSubmissionos(List<Submission> submissions) {
				int indice = getRowCount();
				rows.addAll(submissions);
				fireTableRowsInserted(indice, indice + submissions.size());
			}
			
			public int retornarIndice(Submission submission) {
				return rows.indexOf(submission);
			}
			
			// Remove todos os registros.
			public void limpar() {
				rows.clear();

				// Notifica a mudança.
				fireTableDataChanged();
			}
}
//#endif