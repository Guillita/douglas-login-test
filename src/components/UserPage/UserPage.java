package components.UserPage;

public class UserPage implements IUserPage {
	
	private static final String clientNumberMessage = "Ihre Kundennummer bei Rückfragen:";
	private static final String clientNumberMessageLocator = "//span[contains(text(),'Ihre Kundennummer bei Rückfragen:')]";

	public String getClientNumberMessageLocator() {
		return clientNumberMessageLocator;
	}
	
	public String getClientNumberMessage() {
		return clientNumberMessage;
	}

}
