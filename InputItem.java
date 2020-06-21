import javax.swing.*;
import java.awt.*;

public class InputItem
{
    public JTextField field = null;
    public JLabel label = null;
    public boolean optional = false;
    public Validator validator = null;
    String group = null;

    public InputItem(String gr, boolean opt, Validator val, String lab)
    {
        label = new JLabel(lab);
        field = new JTextField(20);
        optional = opt;
        validator = val;
        group = gr;
    }
}
