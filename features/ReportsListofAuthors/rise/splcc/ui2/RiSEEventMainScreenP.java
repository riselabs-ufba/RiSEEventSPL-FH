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


	//#if ${ReportsListofAuthors} == "T"
	private ListOfAuthorsPerActivityScreenP screenListOfAuthorsPerActivity;
	//#endif
	
	public void init(){
		
		original();

		//#if ${ReportsListofAuthors} == "T"
		ListOfAuthorsPerActivityAction listOfAuthorsPerActivityAction = new ListOfAuthorsPerActivityAction();
		//#endif
		
		//#if ${ReportsListofAuthors} == "T"
		JMenuItem mntmListOfAuthors = new JMenuItem("List Of Authors");
		mnReports.add(mntmListOfAuthors);
		//#endif
		
		//#if ${ReportsListofAuthors} == "T"
		mntmListOfAuthors.addActionListener(listOfAuthorsPerActivityAction);
		//#endif
	}
		
		//REPORTS
		//#if ${ReportsListofAuthors} == "T"
		private class ListOfAuthorsPerActivityAction implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				screenListOfAuthorsPerActivity = ListOfAuthorsPerActivityScreenP.getInstanceListOfAuthorsPerActivityScreenP();
				//desktopPane.add(screenFrequencyPerEvent);
				if(screenListOfAuthorsPerActivity.getParent() == null){
					desktopPane.add(screenListOfAuthorsPerActivity);
				}
				screenListOfAuthorsPerActivity.setVisible(true);
				desktopPane.moveToFront(screenListOfAuthorsPerActivity);
				try {
					screenListOfAuthorsPerActivity.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		}
		//#endif
}
