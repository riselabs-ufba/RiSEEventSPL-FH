package rise.splcc.ui2;

public class RiSEEventMainScreenP extends JFrame {

	//#if ${Receipt} == "T" 
	private ReceiptScreenP screenReceipt;
	//#endif
	
	public void init(){
		original();
		
		//#if ${Receipt} == "T" 
		ReceiptScreenMenuAction receiptAction = new ReceiptScreenMenuAction();
		//#endif

		//#if ${Receipt} == "T" 
		JMenuItem mntmReceiptScreen = new  JMenuItem("Receipt");
		mnPayment.add(mntmReceiptScreen);
		//#endif
		
		//#if ${Receipt} == "T" 
		mntmReceiptScreen.addActionListener(receiptAction);
		//#endif
	}

	//RECEIPT
		//#if ${Receipt} == "T" 
		private class ReceiptScreenMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				screenReceipt = ReceiptScreenP.getInstanceReceiptScreenP();
				//desktopPane.add(screenReceipt);
				if(screenReceipt.getParent() == null){
					desktopPane.add(screenReceipt);
				}
				screenReceipt.setVisible(true);
				desktopPane.moveToFront(screenReceipt);
				try {
					screenReceipt.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		//#endif
		
}
