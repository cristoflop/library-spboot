package es.urjc.cloudapps.library.domain;

import es.urjc.cloudapps.library.exception.FieldFormatException;

import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class Email {

    private String value;

    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                    + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    protected Email() {
    }

    public Email(String value) {
        if (!this.isValid(value))
            throw new FieldFormatException(value + " is not a valid email address");
        this.value = value;
    }

    private boolean isValid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getValue() {
        return this.value;
    }

}
