package rise.splcc.ui2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import rise.splcc.facade.RiSEventFacade;

public class RiSEEventMainScreenP extends JFrame {

	
	//#if ${ReportsFrequencyperActivity} == "T" 
	private FrequencyPerActivityScreenP screenFrequencyPerActivity;
	//#endif
	
	public void init(){
		
		original();
		
		//#if ${ReportsFrequencyperActivity} == "T"
		FrequencyPerActivityAction frequencyPerActivityAction = new FrequencyPerActivityAction();
		//#endif
		
		
		//#if ${ReportsFrequencyperActivity} == "T"
		JMenuItem mntmFrequencyPerActivity = new JMenuItem("Frequency Per Activity");
		mnReports.add(mntmFrequencyPerActivity);
		//#endif
		
		//#if ${ReportsFrequencyperActivity} == "T"
		mntmFrequencyPerActivity.addActionListener(frequencyPerActivityAction);
		//#endif
		
	}
	
		
		//REPORTS
		//#if ${ReportsFrequencyperActivity} == "T"
		private class FrequencyPerActivityAction implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				screenFrequencyPerActivity = FrequencyPerActivityScreenP.getInstanceFrequencyPerActivityScreenP();
				//desktopPane.add(screenFrequencyPerActivity);
				if(screenFrequencyPerActivity.getParent() == null){
					desktopPane.add(screenFrequencyPerActivity);
				}
				screenFrequencyPerActivity.setVisible(true);
				desktopPane.moveToFront(screenFrequencyPerActivity);
				try {
					screenFrequencyPerActivity.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		//#endif
}
