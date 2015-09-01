package rise.splcc.ui2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import rise.splcc.ui2.FrequencyPerEventScreenP;

public class RiSEEventMainScreenP extends JFrame {
	//#endif
	//#if ${ReportsFrequencyperEvent} == "T"
	private FrequencyPerEventScreenP screenFrequencyPerEvent;
	//#endif
	
			
	public void init(){
		original();

		//#if ${ReportsFrequencyperEvent} == "T"
		FrequencyPerEventAction frequencyPerEventAction = new FrequencyPerEventAction();
		//#endif
		
		//#if ${ReportsFrequencyperEvent} == "T"
		JMenuItem mntmFrequencyPerEvent = new JMenuItem("Frequency Per Event");
		mnReports.add(mntmFrequencyPerEvent);
		//#endif
		
		//#if ${ReportsFrequencyperEvent} == "T"
		mntmFrequencyPerEvent.addActionListener(frequencyPerEventAction);
		//#endif
		
	}
	
	//#if ${ReportsFrequencyperEvent} == "T"
	private class FrequencyPerEventAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			screenFrequencyPerEvent = FrequencyPerEventScreenP.getInstanceFrequencyPerEventScreenP();
			//desktopPane.add(screenFrequencyPerEvent);
			if(screenFrequencyPerEvent.getParent() == null){
				desktopPane.add(screenFrequencyPerEvent);
			}
			screenFrequencyPerEvent.setVisible(true);
			desktopPane.moveToFront(screenFrequencyPerEvent);
			try {
				screenFrequencyPerEvent.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
	}
	//#endif
}
