Simple class that given a text count the number of occurrences of a word or expression

# Examples
## No special behaviour

```
Counter cnt = new Counter();
Hashtable<String, Integer> retVal;
retVal = cnt.calculate(text);
```

## With list of words to be ignored

```
ArrayList<String> ignoreList = new ArrayList<>();
ignoreList.add(" test "); // Ignore word "test"
ignoreList.add("\\s+test\\s+"); // Ignore all the words that start with "test"
Counter cnt = new Counter();
Hashtable<String, Integer> retVal;
retVal = cnt.calculate(text);
```

## Merge word expressions

```
Hashtable<String, List<String>> mergeList = new Hashtable<String, List<String>>();
ArrayList<String> singleList = new ArrayList<String>();
singleList.add(" test "); // Merge word "test"
singleList.add("\\s+test\\s+"); // Merge all the words that start with "test"
mergeList.put("My unit tests", singleList);
ArrayList<String> ignoreList = new ArrayList<>();
Counter cnt = new Counter();
Hashtable<String, Integer> retVal;
retVal = cnt.calculate(text);
```
 
## Merge word expressions and ignore list

```
ArrayList<String> ignoreList = new ArrayList<>();
ignoreList.add(" test "); // Ignore word "test"
Hashtable<String, List<String>> mergeList = new Hashtable<String, List<String>>();
ArrayList<String> singleList = new ArrayList<String>();
singleList.add("\\s+test\\s+"); // Ignore all the words that start with "test"
mergeList.put("My unit tests", singleList);
Counter cnt = new Counter(ignoreList,mergeList );
Hashtable<String, Integer> retVal;
retVal = cnt.calculate(text);
```