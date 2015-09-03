package rise.splcc.ui2;

public class RiSEEventMainScreenP extends JFrame {
	
	//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
	private ReviewInsertScreenP screenInsertReview;
	private ReviewRemoveScreenP screenRemoveReview;
	private ReviewUpdateScreenP screenUpdateReview;
	private ReviewSearchScreenP screenSearchReview;
	private ReviewListAllScreenP screenListAllReview;
	private ReviewManagementScreenP screenManagementReview;
	private ReviewResultsScreenP screenResultsReview;
	//#endif
	
			
	public void init(){
		
		original();

		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		InsertReviewMenuAction insertReviewAction = new InsertReviewMenuAction();
		RemoveReviewMenuAction removeReviewAction = new RemoveReviewMenuAction();
		UpdateReviewMenuAction updateReviewAction = new UpdateReviewMenuAction();
		SearchReviewMenuAction searchReviewAction = new SearchReviewMenuAction();
		ListAllReviewMenuAction listAllReviewAction = new ListAllReviewMenuAction();
		ManagementReviewMenuAction managementReviewAction = new ManagementReviewMenuAction();
		ResultsReviewMenuAction resultsReviewAction = new ResultsReviewMenuAction();
		//#endif
		
		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		JMenu mnReview = new JMenu("Review");
		menuBar.add(mnReview);
		
		JMenuItem mntmInsert_6 = new JMenuItem("Insert");
		mnReview.add(mntmInsert_6);
		
		JMenuItem mntmRemove_6 = new JMenuItem("Remove");
		mnReview.add(mntmRemove_6);
		
		JMenuItem mntmUpdate_6 = new JMenuItem("Update");
		mnReview.add(mntmUpdate_6);
		
		JMenuItem mntmSearch_6 = new JMenuItem("Search");
		mnReview.add(mntmSearch_6);
		
		JMenuItem mntmListall_6 = new JMenuItem("ListAll");
		mnReview.add(mntmListall_6);
		
		JMenuItem mntmReviewManagement = new JMenuItem("Review Management");
		mnReview.add(mntmReviewManagement);
		
		JMenuItem mntmReviewResults = new JMenuItem("Review Results");
		mnReview.add(mntmReviewResults);
		//#endif

		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		mntmInsert_6.addActionListener(insertReviewAction);
		mntmRemove_6.addActionListener(removeReviewAction);
		mntmUpdate_6.addActionListener(updateReviewAction);
		mntmSearch_6.addActionListener(searchReviewAction);
		mntmListall_6.addActionListener(listAllReviewAction);
		mntmReviewManagement.addActionListener(managementReviewAction);
		mntmReviewResults.addActionListener(resultsReviewAction);
		//#endif
		
	}
		//REVIEW
		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		private class InsertReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenInsertReview = ReviewInsertScreenP.getInstanceReviewInsertScreenP();
				//desktopPane.add(screenInsertReview);
				if(screenInsertReview.getParent() == null){
					desktopPane.add(screenInsertReview);
				}
				screenInsertReview.setVisible(true);
				desktopPane.moveToFront(screenInsertReview);
				try {
					screenInsertReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}  
		}

		private class RemoveReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenRemoveReview = ReviewRemoveScreenP.getInstanceReviewRemoveScreenP();
				//desktopPane.add(screenRemoveReview);
				if(screenRemoveReview.getParent() == null){
					desktopPane.add(screenRemoveReview);
				}
				screenRemoveReview.setVisible(true);
				desktopPane.moveToFront(screenRemoveReview);
				try {
					screenRemoveReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}  
		}

		private class UpdateReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenUpdateReview = ReviewUpdateScreenP.getInstanceReviewUpdateScreenP();
				//desktopPane.add(screenUpdateReview);
				if(screenUpdateReview.getParent() == null){
					desktopPane.add(screenUpdateReview);
				}
				screenUpdateReview.setVisible(true);
				desktopPane.moveToFront(screenUpdateReview);
				try {
					screenUpdateReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class SearchReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenSearchReview = ReviewSearchScreenP.getInstanceReviewSearchScreenP();
				//desktopPane.add(screenSearchReview);
				if(screenSearchReview.getParent() == null){
					desktopPane.add(screenSearchReview);
				}
				screenSearchReview.setVisible(true);
				desktopPane.moveToFront(screenSearchReview);
				try {
					screenSearchReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class ListAllReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenListAllReview = ReviewListAllScreenP.getInstanceReviewListAllScreenP();
				//desktopPane.add(screenListAllReview);
				if(screenListAllReview.getParent() == null){
					desktopPane.add(screenListAllReview);
				}
				screenListAllReview.setVisible(true);
				desktopPane.moveToFront(screenListAllReview);
				try {
					screenListAllReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class ManagementReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				screenManagementReview = ReviewManagementScreenP.getInstanceReviewManagementScreenP();
				//desktopPane.add(screenManagementReview);
				if(screenManagementReview.getParent() == null){
					desktopPane.add(screenManagementReview);
				}
				screenManagementReview.setVisible(true);
				desktopPane.moveToFront(screenManagementReview);
				try {
					screenManagementReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		
		private class ResultsReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				screenResultsReview = ReviewResultsScreenP.getInstanceReviewResultsScreenP();
				//desktopPane.add(screenManagementReview);
				if(screenResultsReview.getParent() == null){
					desktopPane.add(screenResultsReview);
				}
				screenResultsReview.setVisible(true);
				desktopPane.moveToFront(screenResultsReview);
				try {
					screenResultsReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		//#endif
}
