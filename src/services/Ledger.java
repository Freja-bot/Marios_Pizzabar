package services;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger { //change name to LEDGER?

    //TODO
    //save to statistics
    private Ledger() {


        //Adding a dish to a file

    }


    //Returns a list of strings representing data from a file
    public static ArrayList<String> getFileAsArrayListOfStrings(String fileName) {
        ArrayList<String> strings = new ArrayList<>();
        try {
            //Instantiating a File object (NOT THE FILE ITSELF), that contains information about the file
            File myFile = new File(fileName);
            createFile(fileName, myFile);
            //Instantiating a scanner that scans a file
            Scanner reader = new Scanner(myFile);
            //Goes though file, and adds every line to array, until there are no lines in file
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                strings.add(data);
            }
        } catch (IOException e) {
            System.out.println("ERROR!!!");
            e.printStackTrace();
        }
        return strings;
    }

    //Creates a new file, unless one already exists
    public static void createFile(String fileName, File newFile) {
        try {
            if (newFile.createNewFile()) {
                System.out.println("Der er ikke nogen fil der hedder " + fileName + ", opretter en tom fil" + newFile.getName());
            }

        } catch (IOException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
    }


    public static void writeArrayToFile(ArrayList<String> lines, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
    }

//Takes a sentence and adds it to a given file
public static void writeLineToFile(String line, String fileName) {
    try {
        FileWriter writer = new FileWriter(fileName, true);
        writer.write(line + "\n");
        writer.close();
    } catch (IOException e) {
        System.out.println("ERROR!!!");
        e.printStackTrace();
    }
}
}
