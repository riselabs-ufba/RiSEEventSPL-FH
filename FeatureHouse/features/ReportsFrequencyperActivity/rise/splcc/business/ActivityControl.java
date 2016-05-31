package rise.splcc.business;

public class ActivityControl {
	
	//#if ${ReportsFrequencyperActivity} == "T"
	public void frequencyPerActivity(List<String> participantsPerActivity, Activity activity , String eventName) throws DocumentException, IOException{
		activity.frequencyPerActivity(participantsPerActivity, eventName);
	}
	//#endif
}
