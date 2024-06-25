package matchingengine;

import matchingengine.ui.MatchingEngineUI;

public class MatchingEngineMain {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MatchingEngineUI().setVisible(true);
            }
        });
    }
}
