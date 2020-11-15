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
For my GUI, I plan to implement the following features: 
- Keep the feature of allowing the user to generate their own problem set
- Allow the user to play through the problemset, just like in the console ui
- Add a timer if the user chooses to be timed (Will implement if I have time)
- Display active problem set (Scroll-down list of all problems in problemset)
- Select problems to remove from the problemset in the Scroll-down list 
- Retain the feature of allowing user to save or load problemsets
- Add correct/incorrect sound effects as the user answers problems 

GUI Format:
1.) When you run main, you should be met with a singular menu with all the 
options the UI had: 
- Save problem set (Should be disabled if there is no active problem set)
- Load problem set
- Play active problem set (Should be disabled if there is no active data set)
- Create new problem set
- Quit
2.) There should be a 2nd menu tab plane allows you to view the active
problem set. This tab will display:
- The problem set display type 
- Each problem in the problem set in a scroll down menu, including both
the english and Japanese versions of the problem
- A remove button next to each problem, which will remove the problem if pressed



