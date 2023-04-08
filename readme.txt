~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Program Description: OOP Assignment Java - Search Engine
Author: C21342953 Andrei Voiniciuc
Date: 08/04/2023
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

################################################
        Description of Core Functionality:
################################################

The AVSearch program allows the user to search for a term across a set of text sources, a directory containing a set
of text files. The program uses a GUI to display to the user which text files contain the search term, and each file
shows a percentage of how strong the match is between the search term and each file.

The user can search on a single word, as well as on multiple words.
Logic is used to distinguish between a standard two word search, which will only search for the two words appearing
consecutively in the file, or overall appearing in a random order throughout the file.

To allow for an unlimited amount of searches, the GUI contains a "New Search" button which refreshes the program.

################################################
      Description of Optional Functionality:
################################################

Searching is somewhat sophisticated, as it can search for most occurrences of the search term, aka different variants
of the search term, with punctuation, uppercase or Capitalized.

The user can easily pick the search space through the use of the GUI. There is a text field that allows the user to
enter a custom path and a button to apply this path, and the program carries out the search in that search space.

The ranking mechanism is displayed as a % after each match or non-match of the search term to a specific file.


################################################
                List of Classes:
################################################

------------------------
        Control
------------------------

I used the Control class to simply initialise the main screen object, AV Search, in the main function.
This class serves no other purpose.

------------------------
        Screen
------------------------

I used the Screen class to carry out the main function of displaying a GUI to the user, as well as letting the user
interact with a number of the GUI elements in order to carry out the program's primary function as a search engine.

List of GUI elements:

- JFrame x1
- JPanel x1
- JButton x3
- JTextField x2
- JTextArea x1
- JLabel x4

To give each of these GUI components their respective properties, I used functions such as .setLayout, setBackground,
setForeground, setFont, setBounds, setIcon, and setToolTipText.

Other variables I used throughout the class were Strings and Icons, as well as a Font.

My Screen class uses a couple of ActionListeners and FocusListeners for the buttons and text fields.

In the searchButton ActionListener, the main Search function is triggered. This interacts with the overall panel as
well as with the text area to show search results. In this ActionListener I also create a FileProcessor object, which
is the third class I created for this program.

I have ActionListeners for the other two buttons, newSearch and pathButton which carry out their respective action.

For the two text fields, searchField and pathField, I used FocusListeners, more specifically focusLost listeners, which
makes the user experience easier as the user does not have to always press 'Enter' to initialise the respective
variable, and the variable is constantly updated when there is no more change inside the text field.

------------------------
      FileProcessor
------------------------

I used the FileProcessor class to carry out all file processing mechanics, as well as the main method of the search
engine which I named SearchFile.

This class uses a constructor to receive a filepath and a filename which are used in the SearchFile method.

The SearchFile method returns a String when it is called by the Screen class, which is appended to the text area in the
GUI.

SearchFile also receives a String search, which is the search term the user inputted in the text field through the GUI.

I then made variations of the search String which include punctuation, an uppercase variant and a capitalized variant.
This helps to find the most common occurrences of the search term in a file.

The file reading mechanic is placed inside a try/catch, in case the file cannot be read, and allows for error-checking.

The main process of preparing the file for reading involves creating a File object using the filepath received from
scanning a directory, and a Scanner object which is later used to navigate the file word by word.

I included a number of integer counter variables such as totalWordCount and searchcount, to keep track of the number of
words in a file as well as to later carry out the ranking mechanism calculation as a percentage.

My program allows for 3 types of searches based on the search term: searching on a single word, searching for two
consecutive words, and a 'logical' search for two words which requires the user to type '&&' in between the two words.
To check which type of search has to be carried out, I used a Pattern and a Matcher, which checks for the appropriate
regex in the search term.

After this check is done, through the use of if statements the appropriate type of search is carried out. For a 'logic'
search, the search term is split into a string array with the use of the "\\s&&\s" regex. The elements of this string
array are then placed in their own respective String variable.
The logic search contains two separate counters, one for each word.

Using a while loop, the file is navigated word by word, and through the use of if statements each word in the file is
compared to each of the search words. When a match is found, the respective counter is incremented.

After a file is completely navigated, a string is created including a calculation of the percentage ranking match using
the counter variables mentioned above, and this string is returned to the Screen class to be displayed to the user.
Using if statements, there is a different string returned for each case regarding what matches were made.

A similar process is followed for the consecutive double word check and the single word check.

The method ends by returning the result String variable to the Screen class.


################################################
      What would I add if I had more time?
################################################

I would make the ranking mechanism more accurate, and fix a few slight issues with the logical search matches.

I would make the searching very sophisticated, giving the user a lot of flexibility with how they want to search their
text files. I could also possibly implement spelling correction.

I would make the GUI more appealing and modern, as right now it is fairly basic with a few colour changes and custom
component positioning. I would also try implement more components such as scrolling as the text box does not always
show all the contents of the search results.

