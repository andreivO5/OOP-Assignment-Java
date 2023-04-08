package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String search1 = search + ",";
        String search2 = search + ".";
        String search3 = search.toUpperCase(); // Uppercase
        String search4 = search.substring(0,1).toUpperCase() + search.substring(1); // Capitalized

        try
        {
            File file1 = new File(filepath);
            Scanner scanner1 = new Scanner(file1);
            int count = 0;
            int searchcount = 0;

            // check for double word search
            Pattern whitespace = Pattern.compile("\\s");
            Matcher matchToWhitespace = whitespace.matcher(search);
            boolean doubleWordCheck = matchToWhitespace.find();

            if (doubleWordCheck == true)
            {
                String[] words = search.split(" ");
                String word1 = words[0];
                String word2 = words[1];

                String CapWord1 = word1.substring(0,1).toUpperCase() + word1.substring(1); // Capitalized

                while (scanner1.hasNext())
                {
                    if (scanner1.hasNext(word1)) // if the next word is the search word
                    {
                        scanner1.next();
                        if (scanner1.hasNext(word2))
                        {
                            searchcount++;
                        }
                    }
                    if (scanner1.hasNext(CapWord1)) // if the next word is the search word
                    {
                        scanner1.next();
                        if (scanner1.hasNext(word2))
                        {
                            searchcount++;
                        }
                    }
                    scanner1.next(); // navigates through each word in file
                    count++;
                }
            }

            // normal 1 word search
            while (scanner1.hasNext())
            {
                if (scanner1.hasNext(search)) // if the next word is the search word
                {
                    searchcount++;
                }
                else if (scanner1.hasNext(search1))
                {
                    searchcount++;
                }
                else if (scanner1.hasNext(search2))
                {
                    searchcount++;
                }
                else if (scanner1.hasNext(search3))
                {
                    searchcount++;
                }
                else if (scanner1.hasNext(search4))
                {
                    searchcount++;
                }
                scanner1.next(); // navigates through each word in file
                count++;
            }

            // returns result of search
            if (searchcount > 0)
            {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                result = df.format(((float)searchcount/count)*100) + "%" + " match to the file: "
                        + filename + "\nSearch term "+ "~~" + search + "~~" + " was found "
                        + searchcount + " times" + "\n";
            }
            else if (searchcount == 0)
            {
                result = "0% match, no results found in the file: "+ filename +"\n";
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
