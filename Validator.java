import java.util.regex.Pattern;

public class Validator {

    public class ValidatorType
    {
        static final int
        GENERAL = 0,
        EMAIL = 1,
        ALPHANUMERIC = 2,
        NUMERIC = 3;
    }

    public Pattern pattern = null;
    public String typename = null;

    String[][] types = {
            { "general", "^.+$" },
            { "email", "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$" },
            { "alphanumeric", "^[a-zA-Z0-9 ]+$" },
            { "numeric", "\\d+" }
    };
    private static Pattern numeric = Pattern.compile("\\d+");
    private static Pattern email = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static Pattern alphanum = Pattern.compile("^[a-zA-Z0-9 ]+$");
    private static Pattern general = Pattern.compile("^.+$");

    Validator(int type)
    {
        pattern = Pattern.compile(types[type][1]);
        typename = types[type][0];
    }

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
