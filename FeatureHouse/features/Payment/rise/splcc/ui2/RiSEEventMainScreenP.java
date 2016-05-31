package rise.splcc.ui2;

public class RiSEEventMainScreenP extends JFrame {

	//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
	private PaymentInsertScreenP screenInsertPayment;
	private PaymentRemoveScreenP screenRemovePayment;
	private PaymentUpdateScreenP screenUpdatePayment;
	private PaymentSearchScreenP screenSearchPayment;
	private PaymentListAllScreenP screenListAllPayment;
	private PaymentManagementScreenP screenManagementPayment;
	//#endif
			
	public void init(){
		
		original();
		
		//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
		InsertPaymentMenuAction insertPaymentAction = new InsertPaymentMenuAction();
		RemovePaymentMenuAction removePaymentAction = new RemovePaymentMenuAction();
		UpdatePaymentMenuAction updatePaymentAction = new UpdatePaymentMenuAction();
		SearchPaymentMenuAction searchPaymentAction = new SearchPaymentMenuAction();
		ListAllPaymentMenuAction listAllPaymentAction = new ListAllPaymentMenuAction();
		ManagementPaymentMenuAction managementPaymentAction = new ManagementPaymentMenuAction();
		//#endif

		
		//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
		menuBar.add(mnPayment);
		
		JMenuItem mntmInsert_7 = new JMenuItem("Insert");
		mnPayment.add(mntmInsert_7);
		
		JMenuItem mntmRemove_7 = new JMenuItem("Remove");
		mnPayment.add(mntmRemove_7);
		
		JMenuItem mntmUpdate_7 = new JMenuItem("Update");
		mnPayment.add(mntmUpdate_7);
		
		JMenuItem mntmSearch_7 = new JMenuItem("Search");
		mnPayment.add(mntmSearch_7);
		
		JMenuItem mntmListall_7 = new JMenuItem("ListAll");
		mnPayment.add(mntmListall_7);
		
		JMenuItem mntmPaymentManagement = new JMenuItem("Payment Management");
		mnPayment.add(mntmPaymentManagement);
		//#endif

		
		//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
		mntmInsert_7.addActionListener(insertPaymentAction);
		mntmRemove_7.addActionListener(removePaymentAction);
		mntmUpdate_7.addActionListener(updatePaymentAction);
		mntmSearch_7.addActionListener(searchPaymentAction);
		mntmListall_7.addActionListener(listAllPaymentAction);
		mntmPaymentManagement.addActionListener(managementPaymentAction);
		//#endif
		
	}
	
		//PAYMENT
		//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
		private class InsertPaymentMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenInsertPayment = PaymentInsertScreenP.getInstancePaymentInsertScreenP();
			//	desktopPane.add(screenInsertPayment);
				if(screenInsertPayment.getParent() == null){
					desktopPane.add(screenInsertPayment);
				}
				screenInsertPayment.setVisible(true);
				desktopPane.moveToFront(screenInsertPayment);
				try {
					screenInsertPayment.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}  
		}

		private class RemovePaymentMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenRemovePayment = PaymentRemoveScreenP.getInstancePaymentRemoveScreenP();
			//	desktopPane.add(screenRemovePayment);
				if(screenRemovePayment.getParent() == null){
					desktopPane.add(screenRemovePayment);
				}
				screenRemovePayment.setVisible(true);
				desktopPane.moveToFront(screenRemovePayment);
				try {
					screenRemovePayment.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}  
		}

		private class UpdatePaymentMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenUpdatePayment = PaymentUpdateScreenP.getInstancePaymentUpdateScreenP();
				//desktopPane.add(screenUpdatePayment);
				if(screenUpdatePayment.getParent() == null){
					desktopPane.add(screenUpdatePayment);
				}
				screenUpdatePayment.setVisible(true);
				desktopPane.moveToFront(screenUpdatePayment);
				try {
					screenUpdatePayment.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class SearchPaymentMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenSearchPayment = PaymentSearchScreenP.getInstancePaymentSearchScreenP();
				//desktopPane.add(screenSearchPayment);
				if(screenSearchPayment.getParent() == null){
					desktopPane.add(screenSearchPayment);
				}
				screenSearchPayment.setVisible(true);
				desktopPane.moveToFront(screenSearchPayment);
				try {
					screenSearchPayment.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class ListAllPaymentMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenListAllPayment = PaymentListAllScreenP.getInstancePaymentListAllScreenP();
				//desktopPane.add(screenListAllPayment);
				if(screenListAllPayment.getParent() == null){
					desktopPane.add(screenListAllPayment);
				}
				screenListAllPayment.setVisible(true);
				desktopPane.moveToFront(screenListAllPayment);
				try {
					screenListAllPayment.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class ManagementPaymentMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				screenManagementPayment = PaymentManagementScreenP.getInstancePaymentManagementScreenP();
				//desktopPane.add(screenManagementPayment);
				if(screenManagementPayment.getParent() == null){
					desktopPane.add(screenManagementPayment);
				}
				screenManagementPayment.setVisible(true);
				desktopPane.moveToFront(screenManagementPayment);
				try {
					screenManagementPayment.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		//#endif
}
