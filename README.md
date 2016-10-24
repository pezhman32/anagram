## Another Solution to Kaufland Exercise
Github: https://github.com/pezhman32/kaufland-exercise
<br />
Please find out [TASK.md](./TASK.md) to read the task and requirements.

### How to run the project:
Using latest executable jar file:<br />
`javar -jar ./exercise-jar-with-dependencies.jar "./sample-1.txt"`

Or build the project using Maven:<br />
`./mvnw install`<br />
`java -jar ./target/exercise-jar-with-dependencies.jar "./sample-1.txt"`

### Project structure:
`Model.Anagram` Object to hold anagrams and check if another word fits there.<br />
`Service.AnagramParserService` Reads a file and outputs its anagrams.<br />
`Application` where program starts!.<br />
`AnagramParserServiceTest` unit test to cover AnagramParserService.<br />

I've used Maven so we can add any library (like JUnit that which is currently used) and scale the project more easily.
Also **Java 8** is required to build the program.

### Design/Solution:
By using service and model layers, everything is isolated/reusable and we can cover it via unit tests.

There is a POJO called `Anagram` which contains List<String> as anagrams and can check if another new word fits into 
that list as another anagram or not. This is done by converting each word into a int[26] array and compare them together
as O(n) which is acceptable.

Program reads input file line by line using NIO and Stream<> so it's memory efficient and big files can be accepted as input.
Parsing every line, We get a word and check our list to find a suitable existing anagram (we check only first word in 
each Anagram object) if found, we'll add it to Anagram.anagrams otherwise we create new Anagram with one word inside.

After reading file finished, calling getAnagrams() on the service will result in removing Anagrams with only one word 
inside and others are printed on the screen.

By using larger data sets we are not worry about reading the file (since we are doing this line by line) also finding anagrams
from our existing list is efficient however the problem is `AnagramParserServiceImpl.anagrams` array list. It'll getting 
larger and larger. One solution is to save it onto disk on every `N` line parsed.<br />
So when we read new items we have to fetch part of the list (in fixed amount) into memory and look for existing anagram.

Also this solution does not cover any character other than 26 english chars. this can easily extended by editing 
`Anagram.doesFit()` method.


#### @Author
pezhman32@gmail.com
<br />
Syype: pezhman128
<br />
LinkedIn: http://linkedin.com/in/pezhman32