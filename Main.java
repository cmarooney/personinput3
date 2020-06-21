import javax.swing.*;

public class Main
{

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
