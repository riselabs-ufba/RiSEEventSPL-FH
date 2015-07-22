//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package rise.splcc.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ActivityTableRender extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
					row, column);
			//Cor quando for selecionado, e quando não tiver selecionado.
			if (row % 2 == 0) {
				setBackground(Color.LIGHT_GRAY);
			} else {
				setBackground(null);
			}
			if (isSelected) {
				setBackground(Color.GREEN);
			}
			//Tamanho das Colunas
			table.getColumnModel().getColumn(0).setMaxWidth(2);
			table.getColumnModel().getColumn(1).setMaxWidth(3);
			table.getColumnModel().getColumn(2).setMaxWidth(69);
			table.getColumnModel().getColumn(3).setMaxWidth(4);
			table.getColumnModel().getColumn(4).setMaxWidth(69);
			table.getColumnModel().getColumn(5).setMaxWidth(69);
			table.getColumnModel().getColumn(6).setMaxWidth(69);
			table.getColumnModel().getColumn(7).setMaxWidth(69);
			table.getColumnModel().getColumn(8).setMaxWidth(69);
			table.getColumnModel().getColumn(9).setMaxWidth(69);
			table.getColumnModel().getColumn(10).setMaxWidth(69);
			table.getColumnModel().getColumn(11).setMaxWidth(69);

			table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(2).setResizable(false);
			table.getColumnModel().getColumn(3).setResizable(false);
			table.getColumnModel().getColumn(4).setResizable(false);
			table.getColumnModel().getColumn(5).setResizable(false);
			table.getColumnModel().getColumn(6).setResizable(false);
			table.getColumnModel().getColumn(7).setResizable(false);
			table.getColumnModel().getColumn(8).setResizable(false);
			table.getColumnModel().getColumn(9).setResizable(false);
			table.getColumnModel().getColumn(10).setResizable(false);
			table.getColumnModel().getColumn(11).setResizable(false);
			//Texto Centralizado nas Colunas
			this.setHorizontalAlignment(CENTER);
			return this;
		}
}
//#endif