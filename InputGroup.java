import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class InputGroup extends JPanel
{
    public InputGroup(String groupid, InputItem[] inputs)
    {
        super(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        int gy = 0;
        int gx = 0;
        for (InputItem field : inputs)
        {
            if (field.group.substring(0,1).equals(groupid))
            {
                constraints.anchor = GridBagConstraints.EAST;
                add(field.label, constraints);
                //add_item(field, constraints);
                gx = 1;
                constraints.gridx = gx;
                add(field.field, constraints);
                gy++;
                constraints.gridy = gy;
                gx = 0;
                constraints.gridx = gx;
            }
            gx = 0;
        }
        TitledBorder title = BorderFactory.createTitledBorder(groupid.equals("P") ? "Personal Details" : "Address");
        setBorder(title);

    }
}
