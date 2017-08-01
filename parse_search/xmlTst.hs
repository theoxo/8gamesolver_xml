{-
  theoxo 01/08/2017
  
  ... just wanted to add that so that the code looks important
  it's really not
  doesn't even work
  currently
-}


import Text.XML.Light.Input
import Text.XML.Light.Cursor
import Text.XML.Light.Types

--- IO monad ---->>>>> computation ---->>> IO?
main = do
  src <- readFile "8gamesolver.xml"
  main'' src
    
--- playing around with code from stackoverflow to understand Text.XML.Light    
{-  
main' src = print contents
  where contents = parseXML src
        nodes    = concatMap (findElements $ simpleName "node") (onlyElems contents)
        attributes = map (findAttr $ simpleName "form") nodes
        simpleName s = QName s Nothing Nothing
-}

--- functionaaaaal programmiiiinngg!!!!!! (*heavy metal guitar riffs*)
main'' src = 
  print (show (findChild hasForm crsr))
  where contents = parseXML src
        crsr = extC (fromForest contents)

--- extract Cursor from Maybe
extC :: (Maybe Cursor) -> Cursor
extC (Just c) = c

--- match "form" attribute value (currently just to this hardcoded one)
hasForm :: Cursor -> Bool
hasForm (Cur current lefts rights parents) = 
  (fetchFirstAttr current) == "12345678*"
  

--- fetch value of closest attribute from content
fetchFirstAttr :: Content -> String
fetchFirstAttr (Elem elem) = fetchFirstAttr' elem

--- helper function #1
--- Text.XML.Light is kind of weird. Why Elem -> Element -> Attr? not very clear.
fetchFirstAttr' (Element n as cs l) = attrStr (head as)

--- second helper for fetchFirstAttr; extract value of Attr as String
attrStr :: Attr -> String
attrStr (Attr k v) = v
