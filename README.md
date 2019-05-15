# WORD ADDICTS

## App Platform: Android

## Link to GitHub of WordAddicts Project : [GitHub Pages] (https://github.com/TAMMYLE/WordAddicts.git)

## *Project Overview*

You want to have something interesting, yet a little brain twisting for the morning you wake up your brain or you need something in your lunch break to refresh from morning shift, this game get you covered. You will discover the beauty of words, have fun with letters and immerse in the world of vocabulary.

- Student 1: Phong Le - 216324041 
- Student 2: Tammy Le - 216404524

## *Main classes*

* Function randomWord() that choose a Random word from an array of words.
Parameters: no parameters needed for this function
Returns:
     return a String contains pseudorandom word
```js
public static String randomWord(){
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }
```

* Function shuffleWord() function that shuffles the chosen word
Parameters: word - a string storing a word
Returns:
     if word is not empty and the length is not space => converting word to a new character array
     then, shuffling the word
```js
public static String shuffleWord(String word) {

        if (word != null && !"".equals(word)){
        
            char a[] = word.toCharArray();

            for (int i = 0; i < a.length; i++){
                int j = RANDOM.nextInt(a.length);
                char tmp = a[i];
                a[i]= a[j];
                a[j] = tmp;
            }

            return new String(a);
        }

        return word;
    }
```
* Function compareWords() function compare two given words
Parameters: two words that need to be compared
Returns: a boolean value indicating if two given words are match
```js
public static Boolean compareWords(String word1, String word2){
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        char a[] = word1.toCharArray();
        char b[] = word2.toCharArray();

        boolean match = true;
        if(a.length != b.length || a.length == 0 || b.length == 0   )
        {
            match = false;
            return match;
        }

        for (int i = 0; i < a.length; i++)
        {
            if(a[i] != b[i])
                match = false;
            return match;

        }
        return match;
    }
```
* Function giveHint() review a letter each time the function is called
Parameters : 
- **givenWord** is what the right word is
- **hintField** is what already revealed
- **coin** that the player has
Return : void
```js
public void giveHint(String givenWord, TextView hintField, int coin)
    {
        //if there is not enough coins, there will be no hint given
        if(coin <= 0)
        {
            return;
        }
        // get the string from the hint field
        String hintRevealed = hintField.getText().toString(); 
        
        // turn the givenWord into an array of chars
        char[] a = givenWord.toCharArray(); 
        
        // turn what on the hint field into an array of char
        char[] b = hintRevealed.toCharArray(); 
        
        // a new array which has the length of (b+1)
        char[] c = new char[b.length +1 ]; 



        //if given word is longer than what hints already revealed than another letter
        //assign all elements of a to c.length to c
        if(a.length > b.length)
        {
            for (int i = 0; i<c.length; i++ )
            {
                c[i] = a[i];
            }
        }

        //take array c as new hint with one added letter
        hintRevealed = String.copyValueOf(c);
        hintField.setText(hintRevealed);
    }
```
     



