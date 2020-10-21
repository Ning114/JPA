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

- Choose what vocabulary topics they want to practice 
(More vocabulary sets will be implemented down the line)
- Choose which Hiragana row they want to practice
- Choose which Katakana row they want to practice 
- Choose the input type (display Japanese characters, type english or vice versa)
- Choose if they want to be timed or not 
- Provide feedback based off score, timed performance and past attempts
- Run through a previously created problem set (Will be implemented in phase 2)

The user stories I have finished implementing for phase 1 are:

- Choose what vocabulary topics they want to practice 
(letting the user choose which types of X to add to Y)
(More vocabulary sets will be implemented down the line)
- Choose which Hiragana row they want to practice
- Choose the input type (display Japanese characters, type english or vice versa)
- Choose if they want to be timed or not 

- Phase 1 complete. Only minor bug remaining is that problemSet CAN contain duplicates. Will resolve later.

tons of bug in UI of project when creating a new problem set. Creating a 2nd problem set after already creating one just adds more questions to the
first problem set. Make sure we reset every parameter before the creation of a new problem set, including the list. Timer is also not showing if
we play through a problemset and select not to be timed, then select to be timed the 2nd time. 


