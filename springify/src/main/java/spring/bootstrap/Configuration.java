package spring.bootstrap;

public class Configuration {

    private String applicationName;
    private String applicationGroupId;
    private String dbUsername;
    private String dbPassword = "password";
    private String securityPasswordSalt;
    private String mailUsername = "";
    private String mailPassword = "";

    public Configuration(String applicationName, String applicationGroupId) {
        this.applicationName = applicationName;
        this.applicationGroupId = applicationGroupId;
        this.dbUsername = applicationName.toLowerCase();
    }

    public String getApplicationGroupId() {
        return applicationGroupId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getDBUsername() {
        return dbUsername;
    }

    public String getDBPassword() {
        return dbPassword;
    }

    public String getSecurityPasswordSalt() {
        if (securityPasswordSalt == null) {
            securityPasswordSalt = generateSecurityPasswordSalt();
        }
        return securityPasswordSalt;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    private String generateSecurityPasswordSalt() {
        return "salt";
    }
}
