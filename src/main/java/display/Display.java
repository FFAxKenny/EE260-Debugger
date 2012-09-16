package display;

import jcurses.system.CharColor;
import jcurses.widgets.DefaultLayoutManager;
import jcurses.widgets.Label;
import jcurses.widgets.WidgetsConstants;
import jcurses.widgets.Window;

/**
 * Displays data for EE260 debugger
 * 
 * @author boka
 */
public class Display {
	// Static values
	private static final char PRGM_CTR = 'p';
	private static final char EXEC_CODE = 'c';
	private static final char GPR_A = 'a';
	private static final char GPR_B = 'b';
	private static final char MEM_REG = 'r';
	private static final char I_O = 'i';
	private static final char MSG = 'm';
	private static final int WINDOW_HEIGHT = 24;
	private static final int WINDOW_WIDTH = 80;
	private static final boolean BORDER = true;
	private static final String WINDOW_TITLE = "Main Window";

	// Member objects
	private final Window mWindow;
	private final DefaultLayoutManager mLayoutManager;

	/**
	 * Default constructor
	 */
	public Display() {
		mWindow = new Window(WINDOW_WIDTH, WINDOW_HEIGHT, BORDER, WINDOW_TITLE);
		mLayoutManager = new DefaultLayoutManager();
		mLayoutManager.bindToContainer(mWindow.getRootPanel());
		mLayoutManager.addWidget(new Label("hello", new CharColor(CharColor.WHITE, CharColor.BLACK)), 0, 0, 20, 3,
				WidgetsConstants.ALIGNMENT_CENTER,
				WidgetsConstants.ALIGNMENT_CENTER);
		mWindow.show();
		try {
			Thread.currentThread().sleep(5000);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mWindow.close();
		clear();
	}

	/**
	 * Clears the display
	 */
	public void clear() {
	}

	/**
	 * Updates the display
	 * 
	 * @param
	 */
	public void update(char module, String value) {
		switch (module) {
		}
	}
}
