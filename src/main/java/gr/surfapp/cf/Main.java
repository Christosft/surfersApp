package gr.surfapp.cf;

import gr.surfapp.cf.view_controller.*;

import java.awt.EventQueue;

/**
 * The {@code Main} class serves as the entry point for the application and manages
 * the visibility and positioning of the different GUI pages in the system.
 * <p>
 * All UI components are statically instantiated and can be accessed via getter methods.
 * The main method initializes the pages on the Event Dispatch Thread to ensure thread safety.
 * </p>
 *
 * @author Christos Ftoulis
 */

public class Main {
	/** Here are the screens of the pages i create */
	private static final LandingPage landingPage = new LandingPage();

	private static final LoginPage loginPage = new LoginPage();

	private static final Dashboard dashboard = new Dashboard();

	private static final InsertSession insertSession = new InsertSession();

	private static final ViewSessionsPage viewSessionsPage = new ViewSessionsPage();

	private static final UpdateSession updateSession = new UpdateSession();

	private static final ViewSession viewSession = new ViewSession();

	private static final InsertSessionSuccessPage insertSuccess = new InsertSessionSuccessPage();

	private static final UpdateSessionSuccessPage updateSuccess = new UpdateSessionSuccessPage();

	/**
	 * The main entry point of the application. Initializes all UI components
	 * and sets their initial visibility and location.
	 *
	 * @param args command-line arguments (not used)
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
				try {
					landingPage.setVisible(true);
					landingPage.setLocationRelativeTo(null);
					
					loginPage.setVisible(false);
					loginPage.setLocationRelativeTo(null);
					
					dashboard.setVisible(false);
					dashboard.setLocationRelativeTo(null);
					
					insertSession.setVisible(false);
					insertSession.setLocationRelativeTo(null);
					
					viewSessionsPage.setVisible(false);
					viewSessionsPage.setLocationRelativeTo(null);
					
					updateSession.setVisible(false);
					updateSession.setLocationRelativeTo(null);
					
					viewSession.setVisible(false);
					viewSession.setLocationRelativeTo(null);

					insertSuccess.setVisible(false);
					insertSuccess.setLocationRelativeTo(null);

					updateSuccess.setVisible(false);
					updateSuccess.setLocationRelativeTo(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		});
	}

	/**
	 * Getters of the pages
	 * @return the pages instance
	 */

	public static LandingPage getLandingPage() {
		return landingPage;
	}

	public static LoginPage getLoginPage() {
		return loginPage;
	}

	public static Dashboard getDashboard() {
		return dashboard;
	}

	public static InsertSession getInsertSession() {
		return insertSession;
	}
	
	public static ViewSessionsPage getViewSessionsPage() {
		return viewSessionsPage;
	}

	public static UpdateSession getUpdateSession() {
		return updateSession;
	}

	public static ViewSession getViewSession() {
		return viewSession;
	}

	public static InsertSessionSuccessPage getInsertSuccess() {
		return insertSuccess;
	}

	public static UpdateSessionSuccessPage getUpdateSuccess() {
		return updateSuccess;
	}


}
