package spring.bootstrap;

import java.io.*;

public class Springify {
    private static final String DEFAULT_DOMAIN_ZONE = "com";

    public static void main(String[] args) throws IOException {
        // process the modules, and replace all the substitutions with the application properties
        new Springify().springify();
    }

    private void springify() throws IOException {
        Console console = System.console();
        console.printf("\nBelow you will see a few questions to help you to setup your project.\n" +
                "Default values are listed in square brackets, like [com], so if you are fine with the default one, you can just press Enter there.\n\n");

        String applicationName =
                console.readLine(" > 1. Enter your application name (usually one word, like Facebook, Twitter, or JUnit): ");

        String domainZone = console.readLine(" > 2. Enter your application domain zone (usually com, org, ru, etc.): [com] ");

        if (domainZone.isEmpty()) {
            domainZone = DEFAULT_DOMAIN_ZONE;
        }

        String applicationGroupId = domainZone + "." + applicationName.toLowerCase();

        String correctApplicationGroupId =
                console.readLine(" > 3. So, based on the application name and the domain zone is the following maven groupId correct: '%s' ?: [Y/n]: ", applicationGroupId);

        if (correctApplicationGroupId.equalsIgnoreCase("n") || correctApplicationGroupId.equalsIgnoreCase("no")) {
            applicationGroupId = console.readLine("\t > 3.1. Enter your maven groupId (must be in <domain zone>.<application name> format, like org.junit, for example): ");
        }

        console.printf("\nPlease wait, while applying your configuration...\n");

        SubstitutionMap substitutionMap = new SubstitutionMap(new Configuration(applicationName, applicationGroupId));

        apply(substitutionMap);

        console.printf("\nThank you for using spring-bootstrap! Wish you the best with your new Great project! :)\n\n");
    }

    private void apply(SubstitutionMap substitutionMap) throws IOException {
        File projectDir = new File(".");
        File[] modules = projectDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("<application>");
            }
        });

        for (File module : modules) {

            apply(module, substitutionMap);

            String moduleName = module.getName().substring(SubstitutionMap.APPLICATION.length() + 1);
            boolean renamed = module.renameTo(new File(module.getParentFile().getCanonicalPath() + File.separator + substitutionMap.getApplicationSubstitutionValue() + "." + moduleName));
            if (!renamed) {
                throw new ApplicationException(String.format("Rename of the module %s failed!", module.getName()));
            }
        }

        // TODO: delete .git folder
    }

    private void apply(File directory, SubstitutionMap substitutionMap) throws IOException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                apply(file, substitutionMap);
                if (file.getName().equals(SubstitutionMap.APPLICATION_PACKAGE)) {
                    String groupId = substitutionMap.getApplicationGroupIdSubstitutionValue();
                    String[] groupIdParts = groupId.split("\\.");
                    String newFileName = file.getParent() + File.separator + groupIdParts[0] + File.separator + groupIdParts[1];
                    File newFile = new File(newFileName);
                    boolean createdDirs = newFile.mkdirs();
                    if (!createdDirs) {
                        throw new ApplicationException(String.format("Directory %s was not created!", newFileName));
                    }
                    boolean renamed = file.renameTo(new File(newFileName));
                    if (!renamed) {
                        throw new ApplicationException(String.format("Rename of %s to %s failed!", file.getCanonicalPath(), newFileName));
                    }
                }
            } else {
                BufferedReader in = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = in.readLine()) != null) {
                    for (String substitutionKey : substitutionMap.keySet()) {
                        line = line.replaceAll(substitutionKey, substitutionMap.getSubstitutionValue(substitutionKey));
                    }
                    sb.append(line).append("\n");
                }
                in.close();

                rewriteFile(file, sb);
            }
        }
    }

    private void rewriteFile(File file, StringBuilder fileContent) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(file));

        out.write(fileContent.toString());

        out.close();
    }
}
