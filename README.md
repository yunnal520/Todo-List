# My Personal Project： Bullet Journal 

## Proposal

The project that I will be working on this term will be a bullet journal. The expected users will be mainly students, 
but it is open for anybody to use. The main goal of this project is to help students (of all ages) manage their school 
work. I look forward to working on this project because bullet journaling is something that I am interested in. Normal 
journaling uses up a lot of paper and ink, and it is my goal to provide a digital platform for journaling.

The digital bullet journal will include:
- A todo list: List out tasks that need to be completed and check off things that have been completed.
- An area for journal entries: A place for someone to write about their day and/or reflect on what they have done.


## User Stories
- As a user, I want to be able to add a task to my todo list
- As a user, I want to be able to view my bullet journal
- As a user, I want to be able to add a page to my bullet journal
- As a user, I want to be able to see the number of incomplete and number of completed tasks on my to-do list
- As a user, I want to be able to add a journal entry and a todo list to my bullet journal page

## Phase 2
- As a user, I want to be able to add a task to my previous bullet journal page
- As a user, I want to be able to edit the journal from the previous page
- As a user, I want to be able to save my bullet journal to file 
- As a user, I want to be able to be able to load my bullet journal from file 

## Phase 3
- GUI :')

## Phase 4: Task 2
Sample of events printed in console: 

Thu Nov 25 15:42:54 PST 2021

Added page: 1

Thu Nov 25 15:43:00 PST 2021

Added task: Math hw(Dec 4)

Thu Nov 25 15:43:00 PST 2021

Added to to-do list: To-do List for Today

Thu Nov 25 15:43:11 PST 2021

Added journal entry: Today is a rainy day :'(

## Phase 4: Task 3
Refactoring to improve design:

Break up the BulletJournalGui class so that it is more organized
   1. Separate the GUI functionalities for different panels, there are multiple panels being made in the code
   that can be separated because they are for different features. For example, the panel for previous page, can be made
   into one class and the panel for new page can be made into anther class. 
   2. A lot of repeating code in BulletJournalGui. In particular the options presented to the user after the "Previous Page"
   and "New Page" buttons are pressed. What can be done to remove the duplication is to extract the method and call that method
   from inside the original methods instead (i.e. create a method to be called in the original method). In such way, code clones
   would be replaced with a call to a new method, thus reducing code duplication