//#if ${RegistrationSpeakerActivity} == "T"
package rise.splcc.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ActivitySpeakerTableRender extends DefaultTableCellRenderer {
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
		table.getColumnModel().getColumn(0).setMaxWidth(13);
		table.getColumnModel().getColumn(1).setMaxWidth(213);

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);

		//Texto Centralizado nas Colunas
		this.setHorizontalAlignment(CENTER);
		return this;
	}
}
//#endif