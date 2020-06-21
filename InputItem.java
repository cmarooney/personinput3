import javax.swing.*;
import java.awt.*;

/**
 * Class to hold rows of any table
 * Uses:
 * (1) populate tables according to group id
 * (2) live in external store of rows for validation in one operation
 */
public class InputItem
{
    // pointers to text field, label, optional flag, validator object, group id string
    // noted that need for accessor methods is bypassed by using public
    // lazy shortcut for small class system
    public JTextField field = null;
    public JLabel label = null;
    public boolean optional = false;
    public Validator validator = null;
    public String group = null;

    // construct item from label/group strings, optional flag and validator object
    public InputItem(String gr, boolean opt, Validator val, String lab)
    {
        label = new JLabel(lab);
        field = new JTextField(20);
        optional = opt;
        validator = val;
        group = gr;
    }
}
