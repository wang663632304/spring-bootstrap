package spring.bootstrap;

import java.util.HashMap;

public class SubstitutionMap extends HashMap<String, String> {
    public static final String APPLICATION = "<application>";
    public static final String APPLICATION_PACKAGE = "<application-package>";
    public static final String APPLICATION_GROUP_ID = "<application-group-id>";
    public static final String APPLICATION_NAME = "<application-name>";
    public static final String APPLICATION_NAME_LOWERCASE = "<application-name-lowercase>";
    public static final String DB_USERNAME = "<db-username>";
    public static final String DB_PASSWORD = "<db-password>";
    public static final String SECURITY_PASSWORD_SALT = "<security-password-salt>";
    public static final String MAIL_USERNAME = "<mail-username>";
    public static final String MAIL_PASSWORD = "<mail-password>";

    public SubstitutionMap(Configuration configuration) {
        put(APPLICATION_GROUP_ID, configuration.getApplicationGroupId());
        put(APPLICATION_NAME, configuration.getApplicationName());
        put(APPLICATION_NAME_LOWERCASE, configuration.getApplicationName().toLowerCase());
        put(DB_USERNAME, configuration.getDBUsername());
        put(DB_PASSWORD, configuration.getDBPassword());
        put(SECURITY_PASSWORD_SALT, configuration.getSecurityPasswordSalt());
        put(MAIL_USERNAME, configuration.getMailUsername());
        put(MAIL_PASSWORD, configuration.getMailPassword());
    }

    public String getApplicationSubstitutionValue() {
        return getSubstitutionValue(APPLICATION_GROUP_ID);
    }

    public String getSubstitutionValue(String substitutionKey) {
        return get(substitutionKey);
    }

    public String getApplicationGroupIdSubstitutionValue() {
        return get(APPLICATION_GROUP_ID);
    }
}
