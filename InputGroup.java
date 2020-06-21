import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Class to provide one panel containing a vertical list of fields with input descriptions
 */
public class InputGroup extends JPanel
{
    // Construct the panel
    // The fields are created from the InputItem array
    // The array is kept separate for validation
    // It contains more items than go in this group; the items for this group are distinguished by the group id
    // The group is responsible for positioning the items in its panel - the item jsut contains the text field

    // there are no instance data here
    // the input items are owned by the Gui which validates them
    // the input group stores them as items in the panel, i.e. by swing rather than by class membership

    // construct from group id (which is one character, P or A) and an array of input items
    public InputGroup(String groupid, InputItem[] inputs)
    {
        super(new GridBagLayout());
        // constrain fields with vertical fill and surround space
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);
        constraints.fill = GridBagConstraints.VERTICAL;

        // start component adding top left
        constraints.gridx = 0;
        constraints.gridy = 0;
        int gy = 0;
        int gx = 0;

        // use input items to populate panel
        for (InputItem field : inputs)
        {
            // only add items where groupid matches initial of item group
            if (field.group.substring(0,1).equals(groupid))
            {
                // right justify
                constraints.anchor = GridBagConstraints.EAST;

                // add the item descriptor label left
                add(field.label, constraints);

                // add the text field right
                gx = 1;
                constraints.gridx = gx;
                add(field.field, constraints);

                // down to next row, move back to left
                gy++;
                constraints.gridy = gy;
                gx = 0;
                constraints.gridx = gx;
            }
        }

        // border round table
        TitledBorder title = BorderFactory.createTitledBorder(groupid.equals("P") ? "Personal Details" : "Address");
        setBorder(title);
    }
}
