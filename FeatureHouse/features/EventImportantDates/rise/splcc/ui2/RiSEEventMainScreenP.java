package rise.splcc.ui2;

public class RiSEEventMainScreenP extends JFrame {

	//#if ${EventImportantDates} == "T"
	private EventImportantDatesScreenP screenEventImportantDates;
	//#endif
			
	public void init(){
		
		original();
		
		//#if ${EventImportantDates} == "T"
		EventImpontantDatesMenuAction eventImportantDatesAction = new EventImpontantDatesMenuAction();
		//#endif
		
		//#if ${EventImportantDates} == "T"
		JMenuItem mntmEventImportantDates = new JMenuItem("Event Important Dates");
		mnEvent.add(mntmEventImportantDates);
		//#endif
		
		//#if ${EventImportantDates} == "T"
		mntmEventImportantDates.addActionListener(eventImportantDatesAction);
		//#endif
		
	}
		//#if ${EventImportantDates} == "T"
		private class EventImpontantDatesMenuAction implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				screenEventImportantDates = EventImportantDatesScreenP.getInstanceEventImportantDatesScreenP();
			//	desktopPane.add(screenEventImportantDates);
				if(screenEventImportantDates.getParent() == null){
					desktopPane.add(screenEventImportantDates);
				}
				screenEventImportantDates.setVisible(true);
				desktopPane.moveToFront(screenEventImportantDates);
				try {
					screenEventImportantDates.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		//#endif
}
