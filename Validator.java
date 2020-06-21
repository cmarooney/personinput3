import java.util.regex.Pattern;

/**
 * Class to validate input in text fields in the dialog
 */
public class Validator {

    // Naming of validator types to correlate Pattern strings with validator names by number
    public static class ValidatorType
    {
        static final int
        GENERAL = 0,
        EMAIL = 1,
        ALPHANUMERIC = 2,
        NUMERIC = 3;
    }

    // Array of validator names and pattern regexes
    public static String[][] types = {
            { "general", "^.+$" },
            { "email", "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$" },
            { "alphanumeric", "^[a-zA-Z0-9 ]+$" },
            { "numeric", "\\d+" }
    };
    /* MOVED to types array above
    private static Pattern numeric = Pattern.compile("\\d+");
    private static Pattern email = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static Pattern alphanum = Pattern.compile("^[a-zA-Z0-9 ]+$");
    private static Pattern general = Pattern.compile("^.+$");
    */

    // dat for pattern for validation and typename for error output
    private Pattern pattern = null;
    private String typename = null;

    // Construct a validator giving it a compiled pattern and a name for output
    Validator(int type)
    {
        pattern = Pattern.compile(types[type][1]);
        typename = types[type][0];
    }

    // method to validate a string s for the field named
    boolean validates(String s, String field)
    {
        if (!pattern.matcher(s).matches())
        {
            System.out.println(s + " is not "+typename+" in field " + field);
            return false;
        }
        return true;
    }
}
