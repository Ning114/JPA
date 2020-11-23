# Hiragana + Katakana Practice Application

## An Application For Basic Hiragana and Katakana Practice

**What will the application do?**

This application can be used to help the user practice memorizing certain Japanese subjects
in hiragana and katakana (kanji not included). 
The application will allow the user to run through pre-made problem sets, or randomly
generate a problem set of their own. This randomly generated problem set will be 
of length determined by the user and contain only subjects enabled by the User. 

Subjects include:
- Telling the time
- Memorizing hiragana 
- Memorizing katakana 
- Basic Vocabulary 

**Who will use this application?**

This application is designed for complete beginners who want to learn Japanese
Hiragana and Katakana characters, as well as some basic vocabulary. 

**Why is this project of interest to you?**

My future goal is to become a video game programmer and work somewhere in Japan.
I'm taking JAPN100 this term, so I could use this application 
in the future myself to practice!


**User Stories**

The things the user should be able to do include:

Phase 1:
- Choose what vocabulary topics they want to practice (kind of implemented)
(More vocabulary sets will be implemented down the line)
- Choose which Hiragana row they want to practice (implemented)
- Choose which Katakana row they want to practice 
- Choose the input type (display Japanese characters, type english or vice versa) (implemented)
- Choose if they want to be timed or not (implemented)
- Provide feedback based off score, timed performance and past attempts (implemented)

Phase 2:
- As a user, I want to be able to store created Problem sets to file
- As a user, I want to be able to load and play through a ProblemSet I created previously 

Phase 3:
For my GUI, I have implemented the following user stories: 

Recall that a required user story is that a user must be able to "add multiple Xs to a Y" where X and Y are classes that you've designed yourself.
- Implemented create ProblemSet tab, which allows the user to create a problemset
(i.e add multiple X's to Y)

Your GUI must include a panel that displays the Xs that have been added to the Y.
- Implemented a table to display every problem in the problemSet.

It must also allow the user to generate at least two events related to those Xs and Y (e.g. click a button, move/drag the mouse, select a menu item) which are appropriately handled in the code.  
- Implemented a delete button, which allows the user to remove a specific X from Y
- created a "filter" that allows the user to choose what types of problems 
they want in their problemset, as well as the problem's display type. 

there are buttons or menu items that allow the user to save and to load the state of the application to/from file
- Implemented Load/Save problem set buttons on the main menu 

Phase 4:
Test and design a class in your model package that is robust.  You must have at least one method 
that throws a checked exception.  You must have one test for the case where the exception 
is expected and another where the exception is not expected.

- Implemented exceptions into ProblemSet class, making it robust. Have
also added appropriate tests to test these exceptions. One problem is that
the GUI Create problem set button stops working once it has caught and
recovered from an exception. 
