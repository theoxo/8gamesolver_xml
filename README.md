# 8gamesolver_xml
Modified version of the 8gamesolver to generate a full xml tree of states.

**HOW TO USE**
I am researching how to implement a custom parser in Haskell, but for now you're gonna have to make do with the following procedure:

1. Launch "xmllint --shell 8gamesolver.xml" or similar in your terminal
2. In xmllint, run "cat //node[.//@form=\<state>]/@form" where \<state> is the board you want to reach. So if you want to go from the "solved" configuration to the one that (reading the entries from top to bottom, left to right) reads "13564\*87" (where \* denotes the empty slot), then "13564\*87" is your \<state>. This should return a list with all the board states all the way from root ("1235678\*") up until your requested one, so that you can see which steps to take in which order to reach it!

That's it! Sadly not as streamlined as I'd want it to be (yet), but that might improve at some distant point in the future.


TODO: ~~Clean up the code;~~ ~~add to this readme~~(?).
