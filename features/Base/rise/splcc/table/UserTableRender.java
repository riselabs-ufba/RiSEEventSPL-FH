
package rise.splcc.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class UserTableRender extends DefaultTableCellRenderer {
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
		table.getColumnModel().getColumn(0).setMaxWidth(165);
		table.getColumnModel().getColumn(1).setMaxWidth(165);
		table.getColumnModel().getColumn(2).setMaxWidth(165);
		table.getColumnModel().getColumn(3).setMaxWidth(165);
		table.getColumnModel().getColumn(4).setMaxWidth(165);

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);

		//Texto Centralizado nas Colunas
		this.setHorizontalAlignment(CENTER);
		return this;
	}
}
