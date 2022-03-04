# `Report for assignment 4 - Group 8`

## `Project`

Name: Twilio-Java

URL: https://github.com/DD2480-Group-8/twilio-java

Twilio is a SaaS with the purpose of helping companies connect with their customers. It can be used to build customer support systems such as chat bots, managing incoming phone calls, etc. The Twilio-java project is the Java SDK to use Twilio in Java development.

## `Onboarding experience`

### `Did you choose a new project or continue on the previous one?`

We chose a different project this time around, since the project used in assignment 3 was both inactive and had very few issues, and even fewer that interested us. Therefore, a few hours were spent trying to find a project with an issue that felt interesting while being at a reasonable level of difficulty.

### `If you changed the project, how did your experience differ from before?`

The onboarding of this project, as compared to the previous one was drastically different, the project used in assignment 3 didn't have any build instructions at all, whereas the twilio project had plenty of instructions, such as the supported versions of JDK, the fact that it used maven, and instructions on how to compile the project on your own. Further, since it is a service, it had some instructions on how to use the API, which is good to get some knowledge of where the entry point is.

## `Effort spent`

For each team member, how much time was spent in

1. plenary discussions/meetings;

2. discussions within parts of the group;

3. reading documentation;

4. configuration and setup;

5. analyzing code/output;

6. writing documentation;

7. writing code;

8. running code?

For setting up tools and libraries (step 4), enumerate all dependencies
you took care of and where you spent your time, if that time exceeds
30 minutes.

## `Overview of issue(s) and work done.`

Title: "Java library should not use singletons"

URL: https://github.com/twilio/twilio-java/issues/650

In its' current version, the Twilio SDK is initialized using Twilio.init() and is a global singleton, while the issue at hand suggests implementing it in the standard java way, i.e. new Twilio() and giving the user a non-singleton object. This would allow for multiple uses and configurations in a single production environment, and also conforms more to java standards. 

`Scope (functionality and code affected).`   
Since the singleton instance is accessed through static methods all over the code base, it is a massive refactoring, something that we noticed as worked progressed rather than instantly. When changing one thing, a ton of classes referencing static methods in the twilio class break.

## `Requirements for the new feature or requirements affected by functionality being refactored`

Requirement: Enable multiple instances of the Twilio API separately while still maintaining all of the features of the existing version.  

The refactoring concerns the user-facing API, with the new feature of being able to use multiple instances of twilio separately, while still having all functionality remain intact. In other words, the users should be able to invoke:  
Twilio tw1 = new Twilio("usr1", "pwd1")  
Twilio tw2 = new Twilio("usr2", "pwd2")  
and use these two separately, while still maintaining all of the existing features.
This multitenant use would also finally solve the thread safety concerns raised in the [issue referenced by ours](https://github.com/twilio/twilio-java/issues/430).

This means that current tests should of course still pass with the new initialization, and that new tests can be added to test that the multiple uses are separate from eachother.

Issue: Implement a TwilioAPI interface and refactor the Twilio.java file and all of it's usages to make use of non-static methods as well as the new initializer.

Optional (point 3): trace tests to requirements.

## `Code changes`

### `Patch`

(copy your changes or the add git command to show them)

git diff ...

Optional (point 4): the patch is clean.

Optional (point 5): considered for acceptance (passes all automated checks).

## `Test results`

Overall results with link to a copy or excerpt of the logs (before/after
refactoring).

## `UML class diagram and its description`

### `Key changes/classes affected`

Optional (point 1): Architectural overview.

Optional (point 2): relation to design pattern(s).

## `Overall experience`

What are your main take-aways from this project? What did you learn?

### `Essence`
How did you grow as a team, using the Essence standard to evaluate yourself?

Optional (point 6): How would you put your work in context with best software engineering practice?

Optional (point 7): Is there something special you want to mention here?
