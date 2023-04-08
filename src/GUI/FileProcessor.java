/*
    Program Description: OOP Assignment Java - Search Engine
    Author: C21342953 Andrei Voiniciuc
    Date: 08/04/2023

 */

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

    // variables initialised by constructor
    public FileProcessor(String filepath, String filename) {
        this.filepath = filepath;
        this.filename = filename;
    }

    // the main search engine method
    public String SearchFile(String search)
    {
        // result is the string variable to be returned when calling SearchFile
        String result = "";

        // variations of the search term, with a comma, or full stop, uppercase or capitalized.
        String search1 = search + ",";
        String search2 = search + ".";
        String search3 = search.toUpperCase(); // Uppercase
        String search4 = search.substring(0,1).toUpperCase() + search.substring(1); // Capitalized

        DecimalFormat df = new DecimalFormat(); // shows calculated ranking mechanism to 2 decimal points
        df.setMaximumFractionDigits(2);

        try // try/catch is used in case the file cannot be read.
        {
            // file object is created using the filepath received from the Screen class
            File currentfile = new File(filepath);
            // scanner navigates the file
            Scanner wordScanner = new Scanner(currentfile);
            // counter variables
            int totalWordCount = 0;
            int searchcount = 0;

            // two checkers to figure out the type of search required, based on the inputted search term
            // check for logic '&&' double word search
            Pattern logicPattern = Pattern.compile("\\s&&\\s");
            Matcher matchToLogic = logicPattern.matcher(search);
            boolean logicCheck = matchToLogic.find();

            // check for double word search
            Pattern whitespace = Pattern.compile("\\s");
            Matcher matchToWhitespace = whitespace.matcher(search);
            boolean doubleWordCheck = matchToWhitespace.find();

            if (logicCheck == true) // search process for logic double words
            {
                // the logicWords are split by whitespace and && characters
                String[] logicWords = search.split("\\s&&\\s");
                // they are then placed in respective String variables
                String logicWord1 = logicWords[0];
                String logicWord2 = logicWords[1];

                // two separate counters for the LogicSearch, one for each word
                int word1Counter = 0;
                int word2Counter = 0;

                while (wordScanner.hasNext()) // while loop navigates through file word by word
                {
                    if (wordScanner.hasNext(logicWord1)) // if the next word is the search word
                    {
                        word1Counter++; // word 1 counter is incremented
                    }
                    if (wordScanner.hasNext(logicWord2)) // if the next word is the search word
                    {
                        word2Counter++; // word 2 counter is incremented
                    }
                    wordScanner.next(); // skips to next word in file
                    totalWordCount++; // totalWordCount is incremented
                }

                // returns result of search for each case
                if ((word1Counter > 0) && (word2Counter == 0))
                {
                    // the returned string is created accordingly,
                    // including a calculation of the percentage ranking match
                    result = df.format(((float)word1Counter/totalWordCount)*100) + "%" + " match to the file: "
                            + filename + "\nSearch term "+ "~~" + logicWord1 + "~~" + " was found "
                            + word1Counter + " times" + "\nSearch term "+ "~~" + logicWord2 + "~~" +
                            " was not found in the file.\n";
                }
                else if ((word2Counter > 0) && (word1Counter == 0))
                {
                    result = df.format(((float)word2Counter/totalWordCount)*100) + "%" + " match to the file: "
                            + filename + "\nSearch term "+ "~~" + logicWord2 + "~~" + " was found "
                            + word2Counter + " times" + "\nSearch term "+ "~~" + logicWord1 + "~~" +
                            " was not found in the file.\n";
                }
                else if ((word1Counter == 0) && (word2Counter == 0))
                {
                    result = "0% match, no results found in the file: "+ filename +"\n";
                }
                else if ((word1Counter > 0) && (word2Counter > 0))
                {
                    result = df.format(((float)(word1Counter + word2Counter)/totalWordCount)*100) + "%" +
                            " match to the file: " + filename + "\nSearch term "+ "~~" + logicWord1 + "~~" +
                            " was found " + word1Counter + " times" + "\nSearch term "+ "~~" + logicWord2 + "~~" +
                            " was found " + word2Counter + " times.\n";
                }
                wordScanner.close();
            }
            else if (doubleWordCheck == true && !logicCheck) // search process for double words
            {
                // split the double word search by the whitespace in the middle
                String[] words = search.split(" ");
                String word1 = words[0];
                String word2 = words[1];

                // this search also checks for a capitalized version of word 1.
                String CapWord1 = word1.substring(0,1).toUpperCase() + word1.substring(1); // Capitalized

                while (wordScanner.hasNext())
                {
                    if (wordScanner.hasNext(word1)) // if the next word is the search word
                    {
                        wordScanner.next();
                        // the search counter only increments if the two words are found consecutively.
                        if (wordScanner.hasNext(word2))
                        {
                            searchcount++;
                        }
                    }
                    if (wordScanner.hasNext(CapWord1)) // if the next word is the search word
                    {
                        wordScanner.next();
                        // the search counter only increments if the two words are found consecutively.
                        if (wordScanner.hasNext(word2))
                        {
                            searchcount++;
                        }
                    }
                    wordScanner.next(); // navigates through each word in file
                    totalWordCount++;
                }

                // returns result of search
                if (searchcount > 0)
                {
                    result = df.format(((float)searchcount/totalWordCount)*100) + "%" + " match to the file: "
                            + filename + "\nSearch term "+ "~~" + search + "~~" + " was found "
                            + searchcount + " times" + "\n";
                }
                else if (searchcount == 0)
                {
                    result = "0% match, no results found in the file: "+ filename +"\n";
                }
                wordScanner.close();
            }
            else // normal 1 word search
            {
                while (wordScanner.hasNext())
                {
                    // check for all variations of the search word.
                    if (wordScanner.hasNext(search)) // if the next word is the search word
                    {
                        searchcount++;
                    }
                    else if (wordScanner.hasNext(search1))
                    {
                        searchcount++;
                    }
                    else if (wordScanner.hasNext(search2))
                    {
                        searchcount++;
                    }
                    else if (wordScanner.hasNext(search3))
                    {
                        searchcount++;
                    }
                    else if (wordScanner.hasNext(search4))
                    {
                        searchcount++;
                    }
                    wordScanner.next(); // navigates through each word in file
                    totalWordCount++;
                }
                // returns result of search
                if (searchcount > 0) // if the search word appeared at least once
                {
                    result = df.format(((float)searchcount/totalWordCount)*100) + "%" + " match to the file: "
                            + filename + "\nSearch term "+ "~~" + search + "~~" + " was found "
                            + searchcount + " times" + "\n";
                }
                else if (searchcount == 0) // if the search word never appeared
                {
                    result = "0% match, no results found in the file: "+ filename +"\n";
                }
                wordScanner.close();
            }
        }
        catch (FileNotFoundException e) // error checking if file is somehow not read.
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result; // the result string is returned to the Screen class.
    }
}
