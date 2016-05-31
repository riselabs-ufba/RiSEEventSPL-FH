package rise.splcc.ui2;

public class RiSEEventMainScreenP extends JFrame {
	
	//#if ${EventProgram} == "T"
	private EventProgramScreenP screenEventProgram;
	//#endif
			
	public void init(){
		
		original();
		
		//#if ${EventProgram} == "T"
		EventProgramMenuAction eventProgramAction = new EventProgramMenuAction();
		//#endif
		
		//#if ${EventProgram} == "T"
		JMenuItem mntmEventProgram = new JMenuItem("Event Program");
		mnEvent.add(mntmEventProgram);
		//#endif
		
		//#if ${EventProgram} == "T"
		mntmEventProgram.addActionListener(eventProgramAction);
		//#endif
	}
	
		//#if ${EventProgram} == "T"
		private class EventProgramMenuAction implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				screenEventProgram = EventProgramScreenP.getInstanceEventProgramScreenP();
				//desktopPane.add(screenEventProgram);
				if(screenEventProgram.getParent() == null){
					desktopPane.add(screenEventProgram);
				}
				screenEventProgram.setVisible(true);
				desktopPane.moveToFront(screenEventProgram);
				try {
					screenEventProgram.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		//#endif
}
