import javax.imageio.stream.IIOByteBuffer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Gui extends JFrame {

    // The extra spaces in the label strings do manual alignment on the input fields
    // pending a more effective integration of the grid constraints

    // Format is D/GCV/fieldname
    // where D is the order of display, used by the TreeMap containing the fields
    //       G is the input group (P=Personal or A=Address
    //       C is Compulsory or Optional
    //       V is the validation type, Alphanumeric, Numeric, Email or General
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

    private InputGroup personal = null;
    private InputGroup address = null;

    private static Pattern numeric = Pattern.compile("\\d+");
    private static Pattern email = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static Pattern alphanum = Pattern.compile("^[a-zA-Z0-9 ]+$");
    private static Pattern general = Pattern.compile("^.+$");

    public Gui()
    {
        super("GUI program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(30,30);
        /*
        for (InputItem s : inputs)
        {
            System.out.println("?-"+s);
            fieldmap.put(s, new InputItem(s, new JTextField(20)));
        }
        */
        personal = new InputGroup("P", inputs);
        address = new InputGroup("A", inputs);
        JPanel all = new JPanel(new GridBagLayout());
        all.setSize(200,200);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10,10,10,10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        all.add(personal, constraints);
        constraints.gridx = 1;
        all.add(address, constraints);
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // do everything here...
                boolean tests_ok = true;
                for (InputItem field : inputs)
                {
                    String t = field.field.getText();
                    if (field.optional == false && t.equals(""))
                    {
                        System.out.println("Field "+ field.label.getText() + " should not be unset");
                        tests_ok = false;
                        break;
                    }
                }
                if (tests_ok) {
                    for (InputItem field : inputs) {
                        String t = field.field.getText();
                        if (t.equals("") && field.optional)
                        {
                            continue;
                        }
                        if (!field.validator.validates(t, field.label.getText())) {
                            //System.out.println(t + " is not numeric in field " + field.label);
                            field.label.setForeground(Color.red);
                            field.field.setForeground(Color.red);
                            tests_ok = false;
                            break;
                        } else
                            System.out.println(field.label.getText() + " : " + field.field.getText());
                    }
                }
            }
        });
        constraints.gridx = 2;
        all.add(enter, constraints);
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
        setContentPane(all);
        pack();
        setVisible(true);
    }
}
