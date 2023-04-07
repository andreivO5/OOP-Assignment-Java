package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileProcessor
{
    public String filepath;
    public String filename;

    public FileProcessor(String filepath, String filename) {
        this.filepath = filepath;
        this.filename = filename;
    }

    public String SearchFile(String search)
    {
        String result = "";
        try
        {
            File file1 = new File(filepath);
            Scanner scanner1 = new Scanner(file1);
            int count = 0;
            int searchcount = 0;

            while (scanner1.hasNext())
            {
                if (scanner1.hasNext(search)) // if the next word is the search word
                {
                    searchcount++;
                }
                scanner1.next(); // navigates through each word in file
                count++;
            }
            if (searchcount > 0)
            {
                result = "Found the word: " + search + " in the file: "
                        + filename + "\n" + searchcount + " times" + "\n" +
                        "Total word count: " + count + "\n";
            }
            else if (searchcount == 0)
            {
                result = "No results found in the file: "+ filename +"\n";
            }
            scanner1.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }
}
