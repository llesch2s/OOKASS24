package Afg2RuntimeEnviornment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveConfigLocally {
    private static final String FILE_NAME = "config.txt";

    public static void saveConfigLine(String content) {
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
    public static void updateConfigLine(int id, String newLine) {
        try {
            File file = new File(System.getProperty("user.home"), FILE_NAME);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(":")[0].equals(""+id)) {
                    lines.add(newLine);
                } else {
                    lines.add(line);
                }
            }
            reader.close();

            FileWriter writer = new FileWriter(file);
            for (String updatedLine : lines) {
                writer.write(updatedLine + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void deleteConfigLine(int id) {
        try {
            File file = new File(System.getProperty("user.home"), FILE_NAME);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(":")[0].equals(""+id)) {

                } else {
                    lines.add(line);
                }
            }
            reader.close();

            FileWriter writer = new FileWriter(file);
            for (String updatedLine : lines) {
                writer.write(updatedLine + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
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
        saveConfigLine("1:n:notrunning:path");
        saveConfigLine("2:n:notrunning:path");
        saveConfigLine("3:n:notrunning:path");
        String readContent = readConfig();
        System.out.println(readContent);
        System.out.println("*******************+");
        updateConfigLine(1,"1:n:running:path");
        readContent = readConfig();
        System.out.println(readContent);

    }

}
