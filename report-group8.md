# `Report for assignment 3 - Group 8.`
 
## **`Project`**
 
Name: java-algorithms-implementation 
URL: https://github.com/DD2480-Group-8/java-algorithms-implementation
 
The project aims to implement a number of algorithms and data structures in java. Only standard java libraries are used to achieve this, which means there are no dependencies other than the provided JUnit .jar.
 
## **`Onboarding experience`**
 
As this project only uses standard java libraries, no external components were needed to run it other than the provided JUnit .jar. The only external tool needed was Apache Ant, a widely used build tool. There was no explicit documentation on how to build and run the project to be found, but this was not a big issue. Some members of the group had some issues running and/or testing the code, but this seems to be an issue with IntelliJ rather than with the project. It was solved by specifying the correct project structure, after which the /main/ and /test/ files were detected with no issues.
 
## **`Complexity`**
 
### `What are your results for ten complex functions?`
* Did all methods (tools vs. manual count) get the same result? 
 * Only one function had the same CC when counting by hand as presented by lizard. This could be due to the fact that lizard counts in a different way than we did, or that they go 'deeper' than we were able to. Lizard themselves state "This tool actually calculates how complex the code 'looks' rather than how complex the code real 'is'", which could explain why the numbers deviate.
* Are the results clear?
 * From Lizard, yes the output is quite easily readable and the functions are noted with classfile an line-numbers as well, which makes it easy to navigate to them.
 
| Function                               | LoC | CC (Tool) | CC (By hand) |
| -------------------------------------- | --- | --------- | ------------ |
| FloydWarshall-getAllPairsShortestPaths | 52  | 16        | 13           |
| BTree-combined                         | 82  | 23        | --           |
| BinaryHeapArray-heapDown               | 46  | 41        | 41           |
| BTree-validateNode                     | 59  | 22        | –            |
| PatriciaTrie-addSequence               | 76  | 19        | 17           |
| BinarySearchTree-getDFS                | 60  | 23        | --           |
| Multiplication-multiplyUsingFFT        | 84  | 21        | 20           |
| BinaryHeapTree-heapDown                | 62  | 42        | --           |
| BinarySearchTree-replaceNodeWithNode   | 45  | 22        | 20           |
| Matrix-add                             | 35  | 16        | --           |
 
### `Are the functions just complex, or also long?`
 
In general, complexity does not seem to be very closely correlated to the number of lines of code. The function with the highest complexity, BinaryHeapArray-heapDown, only has 46 lines of code, but 41 CC. Multiplication-multiplyUsingFFT consists of 84 LoC, but has a CC of only 21. However, all of the functions we picked are still pretty long in terms of LoC, with the smallest consisting of 46 lines.
We do not think that the functions we've chosen are boiled down to the essential complexity of the problem, but rather that they are not optimized in terms of their cyclomatic complexity.
 
### `What is the purpose of the functions?`
 
#### **FloydWarshall-getAllPairsShortestPaths**
This function runs the Floyd Warshall algorithm on a graph, which entails finding the shortest paths between all pairs of nodes. Since one function contains the entire algorithm, this requires a lot of branches which leads to the high CC.
 
#### **BTree-combined**
This function merges two nodes in a B-tree (binary tree with more than 2 children per node). To do this you need to compare a lot of keys, check for null values, and the amount of children further down in the tree. This means a lot of branching, which explains the high CC.
 
#### **BinaryHeapArray-heapDown**
heapDown(int index) is called when a node in the BTree is removed. This function is called on the node that should be removed and then called recursively so that the heapArray keeps its properties. To verify this a lot of if/else statements are needed, which is related to the high CC.
 
#### **BTree-validateNode**
validateNode(Node<T> node) is used when deleting a node from the BTree, to validate that the tree keeps its invariants. This function is called on the root node, and then recursively on each of the children. To validate the tree a lot of if/else statements are needed, which is related to the high CC.
 
#### **PatriciaTrie-addSequence**
A trie is used to store strings (i.e. arrays of chars) in a tree, and the purpose of addSequence is to add a new string to the trie. With some refactoring the CC of this function could easily be lowered.
 
#### **BinarySearchTree-getDFS**
Returns the binary search tree in order by traversing it using DFS. With some refactoring the CC of this function could easily be lowered.
 
#### **Multiplication-multiplyUsingFFT**
The purpose of this function is to do multiplication using fast fourier transform. Much of the CC of this function comes from handling the input strings, which should probably be its own function.
 
 
#### **BinaryHeapTree-heapDown**
heapDown(Node<T> nodeToHeapDown) is used to remove a node in the BinaryHeapTree. This function is called with the node to be removed and is then called recursively keep the structure of the tree after removal.
 
#### **BinarySearchTree-replaceNodeWithNode**
This function replaces a node in a Binary Search Tree with another node. Much of the branching here comes from edge cases involving null values.
 
#### **Matrix-add**
This function performs matrix addition of 2 matrices.
 
### `Are exceptions taken into account in the given measurements?`
 
In the 10 functions that we chose, no exceptions are raised or handled. This makes it difficult to say if and how our tool (lizard) takes exceptions into account. If we did encounter exceptions, however, we would considered them another branch, which will increase the cyclomatic complexity, simply because the code will have more paths to take. A try/catch could be considered a decision point, and introducing one could also be considered as reducing the number of exit points (if crashing can be considered an exit.)
 
### `Is the documentation clear w.r.t. all the possible outcomes?`
 
As for the documentation, it was definitely lacking in many places. Some functions had some comments describing the larger branches, but this was generally not enough. Other functions lacked any documentation.
 
## **`Refactoring`**
 
Plan for refactoring complex code:
Estimated impact of refactoring (lower CC, but other drawbacks?).
Carried out refactoring (optional, P+):
git diff …
 
### **FloydWarshall-getAllPairsShortestPaths**
 
This function consists of three parts:
 
First off, there are three for loops in the function, two of which are nested, which are used only to convert a Graph object to an adjacency list.
 
This conversion is followed by the algorithm itself. It consists of a triple for loop which updates the values of the adjacency list to signify the shortest paths between nodes. This should definitely be its own function taking an adjacency list as an argument and either returning the result or mutating the argument itself. This would both make unit testing easier and increase the flexibility of the code.
 
The last part of the code serves a similar purpose as the first one. It converts the result of the algorithm to a mapping between edge objects and their associated weight. This requires the Graph used in part one of the code.
 
As the first and third parts of the code work in conjunction there is an argument for keeping them where they are. This allows you to easily construct a mapping between edges in an existing Graph object. However, this is a specific application of the Floyd-Warshall-algorithm, and the algorithm itself should definitely not be in the same function.
 
 
 
### **BinaryHeapArray-heapDown**
 
The high complexity is due to the fact that there are many different cases that need to be tested. Children to the parent node need to be checked if they are all lesser or all greater, as well as check if they are not invalid in some sort.
 
There are multiple if-statements in the function BinaryHeapArray:heapDown. The function could be refactored to move all of the bigger if statements into help-functions. This would lead to heapDown reducing the cyclomatic complexity by a lot, since the if-statements are the things that increase the CC value as it is now. But this will lead to that the help-function will be higher than the original function. The if-statement in that would be moved would be the one starting with:
  ```Java
  if ((type == Type.MIN && left != null && right != null && value.compareTo(left) > 0 && value.compareTo(right) > 0)
      || (type == Type.MAX && left != null && right != null && value.compareTo(left) < 0 && value.compareTo(right) < 0)) {
          ...
  }
  ```
  As the array is a class variable, only the leftIndex, rightIndex and the root value need to be sent as parameters.
 
Simon refactored BinaryHeapArray:heapDown to reduce its CC from 41 to 4. The commit can be seen [here](https://github.com/DD2480-Group-8/java-algorithms-implementation/commit/6f860e542bbe2d54af00206099c673d1b9fa497f) and the updated code [here](https://github.com/DD2480-Group-8/java-algorithms-implementation/blob/6f860e542bbe2d54af00206099c673d1b9fa497f/src/com/jwetherell/algorithms/data_structures/BinaryHeap.java#L186). This followed the plan stated before for the BinaryHeapArray:heapDown function.
 
 
### **PatriciaTrie-addSequence**
There are a few redundant if statements in the addSequence function. Simply removing these decreases CC from 19 to 15, causing it to not even raise warnings in lizard.
They are redundant since the conditionals are
```Java
if (parent != null)
```
but the parent is always assigned when those checks are made inside the predicate
```Java
if (node.string != null && indexIntoParent < node.string.length)
```
In this implementation of a PatriciaTrie, the root is always a “null string”, meaning it doesn’t have any length, to account for words starting on different letters to be added into the trie. Any inserted sequence then, will have a parent, in which case the check for parent not being null is redundant and can be safely removed while decreasing complexity.
 
 
Another way to reduce complexity would be to move out different units of the function to make it more concise and clear. A common convention is to have <ins>one function do one thing</ins>, however in this case we can easily see that it is doing multiple:
1. Calculating at what node the sequence belong, in the for loop at line 77
2. Actually adding it to the trie in the large if-statement, beginning at line 118
 
These two parts can be transformed into smaller units.
 
 
### **Multiplication-multiplyUsingFFT**
 
As the method uses string representation for the integers, a lot of the recurring checks for negative numbers are done throughout the entire multiplication class. This means that there should exist a util method that can be used throughout the class to avoid code duplication. There is also a check for the string representation of the integer 0. Which should also be refactored to util method for reuse.
 
e.g:
```Java
if (a.equals("0") || b.equals("0")) {
   return "0";
}
```
```Java
boolean negative = false;
if ((a.charAt(0) == '-' && b.charAt(0) != '-') || (a.charAt(0) != '-' && b.charAt(0) == '-')) {
   negative = true;
}
```
 
 
Since the method signature specifies that the result should be returned as a string, the method also has a lot of calculations to convert it back to a string from the resulting multiplication integer. This is a clear case to use another util method or helper method to take care of this part. This leaves the specifics of the multiplication method to be focused on instead of having all the calculations that may not be relevant to the method and even avoid having code duplication throughout the class.
 
e.g:
```Java
StringBuilder result = new StringBuilder();
if (negative) {
   CoverageMeasurer.visitedBranch(13);
   result.append('-');
}
```
 
### **Matrix-add**
The main problem with this function was the multiple checks for data type, for example float, double, BigInteger, etc. This increases the complexity unnecessarily because for every method call only one of these if-loops would be entered. In addition, the function does not take into account combinations of data types, such as adding a matrix of Integers with a matrix of Floats.
 
Hence, the function could perhaps be handled by following the Builder Pattern, and letting a separate function build the matrix before handling it in the main add() function. Most of the primitives can be widened to long and double, and BigInteger can be converted to BigDecimals. The builder tool can assign all types as these widened data types to reduce the load on the main add() function. Conversion to a wider data type can also alleviate the problem with number overflow. 
 
The drawback of this is the extra space necessary to store longs and doubles instead of just integers. 
 
 
## **`Coverage`**
 
### `Tools`
 
As an existing tool, we used the code coverage agent shipped with IntelliJ. It provided a really nice experience, since the setup is pretty much automatic, and no documentation is really needed to understand it.
 
### `Your own coverage tool`
 
The following command can be executed to show the difference in the ```issue/1``` branch. 
PatriciaTrie::addSequence function after the instrumentation has been added:
```
git diff f2d115704691e8e4f6cfe490231d0adb0c3ce7d0 042a7a1861919a13313d6d260744f5821a8f6b1a -- src/com/jwetherell/algorithms/data_structures/PatriciaTrie.java
```
BinaryHeapArray::heapDown function after the instrumentation has been added:
```
git diff f2d115704691e8e4f6cfe490231d0adb0c3ce7d0 2b98e1e77613820ee7ee7041dd4d99dab23d12fb -- src/com/jwetherell/algorithms/data_structures/BinaryHeap.java
```
KdTree::searchNode function after the instrumentation has been added:
```
git diff f2d115704691e8e4f6cfe490231d0adb0c3ce7d0 2b98e1e77613820ee7ee7041dd4d99dab23d12fb -- src/com/jwetherell/algorithms/data_structures/KdTree.java
```
Multiplication::MultiplyWithFFT function after the instrumentation has been added:
```
 git diff f2d115704691e8e4f6cfe490231d0adb0c3ce7d0 2b98e1e77613820ee7ee7041dd4d99dab23d12fb -- src/com/jwetherell/algorithms/mathematics/Multiplication.java
 
```
Matrix::Add function after the instrumentation has been added:
```
git diff f2d115704691e8e4f6cfe490231d0adb0c3ce7d0 9ec4e2a885fce9338acb9c435a1cba9841db0506 -- src/com/jwetherell/algorithms/data_structures/Matrix.java
```
**What kinds of constructs does your tool support, and how accurate is
its output?** 
Our tool support any kinds of constructs, based on what the user wants to measure. This is because the instrumentation is based on manual work, i.e. identifying what parts of the program the user wants to reach, and inserting measurement points at those places using
```Java
CoverageMeasurer.visitedBranch(branchNumber)
```
If the insertion has been done thoroughly, the output is quite accurate and gives a measure of how many of the measure points have been hit.
 
Example output could look like this:
```
-----------
Adding existing black...
-----------
61.5% of branches are covered.
The branches _not_ covered by the test class are:
[5, 13, 15, 16, 17, 18, 19, 20, 22, 24]
-----------
Adding duplicates...
-----------
69.2% of branches are covered.
The branches _not_ covered by the test class are:
[13, 15, 16, 17, 18, 19, 20, 24]
-----------
Testing with random data...
-----------
84.6% of branches are covered.
The branches _not_ covered by the test class are:
[13, 15, 18, 20]
```
 
### `Evaluation`
 
1. **How detailed is your coverage measurement?**
  Our coverage measurement checks that each predicate evaluates to both true and false when the test suite is executed. If any predicate does not evaluate to both of these, the corresponding branch will be returned as input, so as to let the user know what branches need to be reached with a new test case.
 
2. **What are the limitations of your own tool?** 
  A limitation of our own tool is that it assumes branches are counted and memasure points are inserted into the code manually. However, this also makes it quite dynamic, since exceptions, for example, can be included very easily if necessary. Another limitation is that one line statements such as ternary operators would not be counted, unless the code is rewritten into a more verbose if-statement that allows for multiple lines of code within the clauses.
 
3. **Are the results of your tool consistent with existing coverage tools?** 
  Yes, when measuring branch coverage using our tool, and the code coverage agent in IntelliJ, the results are the same.
 
## **`Coverage improvement`**
 
The following diffs are to be performed on the ```issue/2``` branch.
 
 
 
## Emil: 
The existing test used a lot of randomly generated data but always fail to cover branch 22, and almost always 23. Two tests were added to ensure that these branches are covered in each run of the test suite.  
 
Old coverage:
```
73.1% of branches are covered.
The branches _not_ covered by the test class are:
[13, 15, 18, 20, 21, 22, 23] 
```
 
New coverage:
```
84.6% of branches are covered.
The branches _not_ covered by the test class are:
[13, 15, 18, 20]
```
 
Test cases added: 

[testPatriciaTrieAddExistingBlack()](https://github.com/DD2480-Group-8/java-algorithms-implementation/blob/ab03b56b57937b4c94e0de433ac8c09171bcc8b0/test/com/jwetherell/algorithms/data_structures/test/PatriciaTreeTests.java#L53)  
[testPatriciaTrieAddDuplicates()](https://github.com/DD2480-Group-8/java-algorithms-implementation/blob/ab03b56b57937b4c94e0de433ac8c09171bcc8b0/test/com/jwetherell/algorithms/data_structures/test/PatriciaTreeTests.java#L71)

 
diff:
```
git diff a539981694257b3e736224073e9a8ef8bc89cff4 fe368b039d2c0cdf1f49463307f11fe6985f1c27
```
 
## Simon:
Old coverage:
```
94.1% (16/17) of branches are covered in BinaryHeapArray:heapDown.
Branch 11 not visited
```
 
New coverage:
```
100.0% (17/17) of branches are covered in BinaryHeapArray:heapDown.
```
 
Test cases added:
[testMinHeapRemoveWithDuplicates()](https://github.com/DD2480-Group-8/java-algorithms-implementation/blob/ab03b56b57937b4c94e0de433ac8c09171bcc8b0/test/com/jwetherell/algorithms/data_structures/test/BinaryHeapTests.java#L97)  
[testMaxHeapRemoveWithDuplicates()](https://github.com/DD2480-Group-8/java-algorithms-implementation/blob/ab03b56b57937b4c94e0de433ac8c09171bcc8b0/test/com/jwetherell/algorithms/data_structures/test/BinaryHeapTests.java#L113)  
[division()](https://github.com/DD2480-Group-8/java-algorithms-implementation/blob/ab03b56b57937b4c94e0de433ac8c09171bcc8b0/test/com/jwetherell/algorithms/mathematics/test/MathematicsTest.java#L229) (5 lines)  
[testArrayListSetAndToString()](https://github.com/DD2480-Group-8/java-algorithms-implementation/blob/ab03b56b57937b4c94e0de433ac8c09171bcc8b0/test/com/jwetherell/algorithms/data_structures/test/ListTests.java#L19)  

 
diff:
```
git diff e97279282f4b3567b9f92bf4ac372772d34244da a11a157e89b3ecb9cfda1d8376ce314882d41070 -- test/com/jwetherell/algorithms/mathematics/test/MathematicsTest.java test/com/jwetherell/algorithms/data_structures/test/ListTests.java test/com/jwetherell/algorithms/data_structures/test/BinaryHeapTests.java
```
 
## Viktor:
Old coverage:
```
80% of branches are covered.
```
 
New coverage:
```
93.33333333333333% of branches are covered.
```
 
Test cases added:  
testKdTree()  
added 2 new testcases within this function.  
[test case 1](https://github.com/DD2480-Group-8/java-algorithms-implementation/blob/ab03b56b57937b4c94e0de433ac8c09171bcc8b0/test/com/jwetherell/algorithms/data_structures/test/KdTreeTests.java#L49)  
[test case 2](https://github.com/DD2480-Group-8/java-algorithms-implementation/blob/ab03b56b57937b4c94e0de433ac8c09171bcc8b0/test/com/jwetherell/algorithms/data_structures/test/KdTreeTests.java#L55)  

 
diff:
```
git diff 19ec384ccc1a02f074641fd0928bc553c65791db 26dd4d207389d4d0b557560372081b7f99f09bc3 -- test/com/jwetherell/algorithms/data_structures/test/KdTreeTests.java
```
 
## Joakim:
Old coverage:
```
94.11764705882352%
```
 
New coverage:
```
100%
```
 
Test cases added:
[multiplication()](https://github.com/DD2480-Group-8/java-algorithms-implementation/blob/ab03b56b57937b4c94e0de433ac8c09171bcc8b0/test/com/jwetherell/algorithms/mathematics/test/MathematicsTest.java#L153)  
added 2 new testcases within this function.
 
diff:
```
git diff f2d115704691e8e4f6cfe490231d0adb0c3ce7d0 4fcfc839c0a4d2c42ad4c21c31af260cbbde9b8a -- test/com/jwetherell/algorithms/mathematics/test/MathematicsTest.java
```
 
## Jovan:
Old coverage:
```
40% of branches are covered.
```
 
New coverage:
```
100% of branches are covered.
```
 
Test cases added:  
testMatrixAddition()  
[added 6 new testcases within this function.](https://github.com/DD2480-Group-8/java-algorithms-implementation/blob/ab03b56b57937b4c94e0de433ac8c09171bcc8b0/test/com/jwetherell/algorithms/data_structures/test/MatrixTests.java#L106)  
  
diff:
```
git diff f2d115704691e8e4f6cfe490231d0adb0c3ce7d0 ab03b56b57937b4c94e0de433ac8c09171bcc8b0 -- test/com/jwetherell/algorithms/data_structures/test/MatrixTests.java

```
 
## **`Self-assessment: Way of working`**
 
| State      | Checklist | Fulfilled (yes/no/comments) |
| ----------- | ----------- | ----------- |
| Principles established      | Principles and constraints are committed to by the team. <br> Principles and constraints are agreed to by the stakeholders.<br> The tool needs of the work and its stakeholders are agreed. <br> A recommendation for the approach to be taken is available. <br>The context within which the team will operate is understood. <br> The constraints that apply to the selection, acquisition, and use of practices and tools are known. <br>     | Yes |
| Foundation Established   | The key practices and tools that form the foundation of the way-of-working areselected. <br> Enough practices for work to start are agreed to by the team. <br> All non-negotiable practices and tools have been identified. <br>The gaps that exist between the practices and tools that are needed and the practices and tools that are available have been analyzed and understood. <br> The capability gaps that exist between what is needed to execute the desired way of working and the capability levels of the team have been analyzed and understood. <br> The selected practices and tools have been integrated to form a usable way-of-working.       | Yes|
| In Use | The practices and tools are being used to do real work. <br> The use of the practices and tools selected are regularly inspected. <br> The practices and tools are being adapted to the team’s context. <br> The use of the practices and tools is supported by the team. <br> Procedures are in place to handle feedback on the team’s way of working. <br> The practices and tools support team communication and collaboration. | Yes |
| In Place | The practices and tools are being used by the whole team to perform their work. <br> All team members have access to the practices and tools required to do their work. <br> The whole team is involved in the inspection and adaptation of the way-of-working | Partly fulfilled |
| Working well | Team members are making progress as planned by using and adapting the way-of-working to suit their current context. <br> The team naturally applies the practices without thinking about them. <br> The tools naturally support the way that the team works. <br> The team continually tunes their use of the practices and tools. | Partly fulfilled |
| Retired | The team's way of working is no longer being used. <br> Lessons learned are shared for future use. | No |
 
 
<h2>
Summmary
</h2>
 
Current state of the group is between "In Place" and "Working well" as some of the criteriums are fulfilled for both of the states but not all of them. The first state "Principles Established" and "Foundation Established" have both been fulfilled since the first assignment and "Meet your group" assignment. The group has since then been improving the usage tools and practices of tools since the first assignment and has been improving for each assignment in the course. One of the points in the "In Use" state that still isn't completely fulfilled is "The use of practices and tools selected are regularly inspected.". The discussion of practices and tools are seldom discussed since the focus is more towards the task and they are seen as sufficient in their current state. The reason that the current state is between "In Place" and "Working well" is that we are not discussing the adaption and inspection of the way-of-working. This part can be improved with having some "retrospect sessions" after each assignment, which we haven't done yet. In the "Working well" state the team has improved in applying practices seamlessly and also that tools support the way that the team works, there are still some work to be done for the rest of the points in the state.
 
 
## **`Overall experience`**
 
The overall experience of this project has been good, although a lot more individual work compare to the two previous assignments. It's been interesting to dive into a completely unfamiliar codebase and try to get a grasp of how the software works. The project we chose doesn't really have a clear flow, it is rather a library to be imported into another project and used as stand alone methods. This has probably made it easier to understand since you only need to consider a single class to understand what it does - there is not any classes that depend on eachother, but there is a clear hierarchy through interfaces etc. We've learned a lot more about the structure of projects, since IntelliJ required some configuring, as well as experience of a new build tool, ant. Overall, the experience has been fun and has definitely given us more confidence in trying to contribute to OSS.


