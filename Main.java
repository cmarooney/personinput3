import javax.swing.*;

public class Main
{
    /**
     * This is an exercise in creating an input dialog.
     * The input is validated on entry.
     * At the moment there is no further processing after data is entered.
     *
     * This class provides the main program of the application.
     * It launches the GUI on the EDT and stays until stopped.
     *
     * See Gui.java for the main frame of the dialog.
     */

    public static void main(String[] args)
    {
        System.out.println("Getting your man");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui();
            }
        });
    }

}
