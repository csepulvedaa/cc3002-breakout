package gui;

import controller.Game;
import gui.BasicApp;

import java.util.Observable;
import java.util.Observer;

/**Observable Component For the Grafic Interface
 * @author csepu
 * @version Dic 2018
 */

public class AppUpdater implements Observer {
    private BasicApp app;

    /**
     * Creates an Appupdater and set the GUI
     *
     * @param app
     */
    public AppUpdater(BasicApp app){
        this.app=app;

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Game) {
            app.changeLevel();
        }

    }
}
