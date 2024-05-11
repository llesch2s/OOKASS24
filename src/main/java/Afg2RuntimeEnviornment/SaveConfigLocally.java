package Afg2RuntimeEnviornment;

import java.io.*;

public class SaveConfigLocally {
    private static final String FILE_NAME = "config.txt";

    public static void saveConfig(String content) {
        try {
            File file = new File(System.getProperty("user.home"), FILE_NAME);
            FileWriter writer = new FileWriter(file,true);
            writer.write(content+ "\n");
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String readConfig() {
        StringBuilder content = new StringBuilder();
        try {
            File file = new File(System.getProperty("user.home"), FILE_NAME);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return content.toString();
    }
    public static void emptyConfig() {
        try {
            File file = new File(System.getProperty("user.home"), FILE_NAME);
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.err.println( e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Test the methods
        String configContent = "Setting1=Value1\nSetting2=Value2";
        emptyConfig();
        saveConfig(configContent);
        saveConfig(configContent);
        saveConfig(configContent);
        String readContent = readConfig();
        System.out.println("Content read from config file:");
        System.out.println(readContent);
    }

}
