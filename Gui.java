import javax.imageio.stream.IIOByteBuffer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

    /**
     * This is the main frame of the input dialog.
     */

    // "inputs" specifies the input fields of the dialog.
    // Each input field is an InputItem instance.
    // It contains
    // - a string describing the group within the input dialog containing the field (arg #1)
    // - a flag saying if the input to that field is optional
    // - a validator object which will check correct formatting of the input
    // - a string for the label for the input field
    // A JTextField will be created within the object for the input field.
    // Assembling them here outside the GUI enables a validation sweep of all of them to be made on Enter.
    InputItem[] inputs = new InputItem[]{
            new InputItem("Personal", false,new Validator(Validator.ValidatorType.ALPHANUMERIC), "Given Name"),
            new InputItem("Personal", false, new Validator(Validator.ValidatorType.ALPHANUMERIC), "Surname"),
            new InputItem("Personal", false, new Validator(Validator.ValidatorType.NUMERIC), "Phone Number"),
            new InputItem("Personal", false, new Validator(Validator.ValidatorType.EMAIL), "Email"),
            new InputItem("Address", false, new Validator(Validator.ValidatorType.GENERAL), "Address 1"),
            new InputItem("Address", true,new Validator(Validator.ValidatorType.GENERAL),"Address 2"),
            new InputItem("Address", false, new Validator(Validator.ValidatorType.ALPHANUMERIC), "Town"),
            new InputItem("Address",true, new Validator(Validator.ValidatorType.ALPHANUMERIC),"County"),
            new InputItem("Address",false, new Validator(Validator.ValidatorType.ALPHANUMERIC), "Country"),
            new InputItem("Address", false,new Validator(Validator.ValidatorType.GENERAL), "Post Code")
    };

    // The input frame is divided into 2 bordered input groups, one for personal data, one for address data
    // These are JPanels within the frame.
    private InputGroup personal = null;
    private InputGroup address = null;

    // Construct the frame of the dialog and populate it from the above array of input items
    // Make it listen for data entry and data clearance
    public Gui()
    {
        super("GUI program"); // JFrame title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(30,30); // Optional?

        // Create two bordered panels, one for personal data, one for address data
        personal = new InputGroup("P", inputs);
        address = new InputGroup("A", inputs);

        // Create panel to contain all objects of the GUI as the content pane of the frame
        JPanel all = new JPanel(new GridBagLayout());
        all.setSize(200,200);

        // This panel stores the two bordered panels horizontally with padding space around
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10,10,10,10);

        // Personal data goes on the left
        constraints.gridx = 0;
        constraints.gridy = 0;
        all.add(personal, constraints);

        // Address data goes on the right
        constraints.gridx = 1;
        all.add(address, constraints);

        // A button to validate and process the data once it is input
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Validation tests initially assumed OK - will be false if anything is wrong
                boolean tests_ok = true;

                // test if data is present if input is not optional
                for (InputItem field : inputs)
                {
                    String t = field.field.getText();
                    if (field.optional == false && t.equals(""))
                    {
                        // TODO: convey in the dialog that the field should be set
                        System.out.println("Field "+ field.label.getText() + " should not be unset");
                        tests_ok = false;
                        break;
                    }
                }

                // validate data content
                if (tests_ok) {
                    for (InputItem field : inputs) {
                        // ignore blank optional fields
                        String t = field.field.getText();
                        if (t.equals("") && field.optional)
                        {
                            continue;
                        }

                        // if validator fails, mark bad field in red
                        // else restore any red to black if it is now ok
                        if (!field.validator.validates(t, field.label.getText())) {
                            //System.out.println(t + " is not numeric in field " + field.label);
                            field.label.setForeground(Color.red);
                            field.field.setForeground(Color.red);
                            tests_ok = false;
                            break;
                        }
                        else {
                            field.label.setForeground(Color.black);
                            field.field.setForeground(Color.black);
                            System.out.println(field.label.getText() + " : " + field.field.getText());
                        }
                    }
                }
            }
        });
        constraints.gridx = 2;
        all.add(enter, constraints);

        // A button to clear all the fields, bottom right
        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    for (InputItem field : inputs) {
                        field.field.setText("");
                    }
            }
        });
        constraints.gridy = 1;
        all.add(clear, constraints);

        // finish dialog creation
        setContentPane(all);
        pack();
        setVisible(true);
    }
}
