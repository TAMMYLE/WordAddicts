# WORD ADDICTS

## App Platform: Android

## Link to GitHub of WordAddicts Project : [GitHub Pages] (https://github.com/TAMMYLE/WordAddicts.git)

## *Project Overview*

You want to have something interesting, yet a little brain twisting for the morning you wake up your brain or you need something in your lunch break to refresh from morning shift, this game get you covered. You will discover the beauty of words, have fun with letters and immerse in the world of vocabulary.

- Student 1: Phong Le - 216324041 
- Student 2: Tammy Le - 216404524

## *LetterStorage class*

* Function randomWord() *that choose a Random word from an array of words*.

Parameters: no parameters needed for this function

Returns:
     return a String contains pseudorandom word
     
```js
public static String randomWord(){
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }
```

* Function shuffleWord() *function that shuffles the chosen word*

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
* Function compareWords() *function compare two given words*

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

* Function giveHint() *review a letter each time the function is called*

     * Parameters : 

          - **givenWord** is what the right word is
          - **hintField** is what already revealed
          - **coin** that the player has

     * Return : void

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

## *Main class*

* Function renewWord() *is to renew the word that player has to guess*

    * parameters: no parameters needed
    
    * return: void
    
```js
public void renewWord() {
        givenWord = availableWords.randomWord();
        shuffedWord = availableWords.shuffleWord(givenWord);
        shuffedLetters.setText(shuffedWord);
    }
```

* Event check is triggered when player clicks check button

     *if the answer is correct --> score plus ten*

     *set the Score field with extra score*

     *clear the input*

     *renew the given word*

     *the if condition is triggered when user guess the right word (guessWord == givenWord)*

```js
checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessWord = textInput.getText().toString();
                //display a message according to the result
                if(availableWords.compareWords(givenWord, guessWord))
                {                                       
                    score += 10;                        //if the answer is correct, score plus ten
                    result.setText("Score: " + score);  //set the Score field with extra score
                    textInput.setText("");              //clear the input
                    renewWord();                        
                }
                else
                {
                    result.setText("Score" + score);

                    //When player gets a wrong word -> input will be vibrated
                   
                    Animation vibrate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vibrate);
                    textInput.startAnimation(vibrate);
                }
            }
        });
```
* Method editCoin() *take the current amount of coin and put it into the SharedPreferences*

     * Parameters: current number of coin in Integer form

     * Return: void, the function just edit the coin in the SharedPreference, doesn't return anything

```js
public void editCoin(int coin)
    {
        SharedPreferences preferences = getSharedPreferences("COIN_PREFS", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("coin", coin);
        editor.apply();
    }
```
* Method retrieveCoin() *retrieve the amount of coin currently holding in the SharedPreference*

     * Parameters: no parameters needed

     * Return: integer contains the number of coins.

```js
public int retrieveCoin()
    {
        int coin1;
        SharedPreferences preferences = getSharedPreferences("COIN_PREFS", 0);

        coin1 = preferences.getInt("coin", 0);
        return coin1;
    }
```

## *Timer class*

* Function setTimer: is used to initialize values for the timer
     * parameters: no parameters needed for this funtion
     *  returns : void
     *  const: time --> set the time to 10 which will be multiplied by 1000 to make the timer to 10 seconds
 
 ```js
     private void setTimer(){

        //initialize the time variable
        int time = 10;

        //calculate the totaltime
        totalTimeCountInMilliseconds =  time * 1000;

        //set the maximum time of the progress bar timer
        mProgressBar1.setMax( time * 1000);
    }
```
* Function startTimer: is used to start counting down the timer
     * parameters: no parameters needed for this function
     * returns : void
 
 ```js
 private void startTimer() {

        //call renewWord function once the timer is on
        renewWord();
        countDownTimer = new MyCountDown(totalTimeCountInMilliseconds, 50);
        countDownTimer.start();
    }
```

* Function plusTime: is used to give extra time whenever player gets correct word
     *   parameters: no parameters needed for this function
     *   returns : void

```js
private void plusTime(){

        if(totalTimeCountInMilliseconds < 10000)
        {
            countDownTimer.cancel();
            countDownTimer = new MyCountDown(totalTimeCountInMilliseconds + 3000, 50);
        }
        else
        {
            countDownTimer.cancel();
            countDownTimer = new MyCountDown(10000, 50);
        }

        countDownTimer.start();


    }
```

* Start Timer Event: initialize the click listeners for start button
     * sound is played
     * setTimer() function is called
     * set the visibility of all the needed views
     * start the timer by calling startTimer()

```js
buttonStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //play sound
                newgame.start();
                
                //call setTimer function
                setTimer();
                
                score = 0;
                scoreTextView.setText("Score: " + score);
                leveltextview.setText("I");
                
                //set the visibility of all needed views
                buttonStartTime.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                textViewShuffle.setVisibility(View.VISIBLE);//show the word that user needs to guess
                scoreTextView.setVisibility(View.VISIBLE);// show the score when the game starts
                leveltextview.setVisibility(View.VISIBLE);// show the level when the game starts
                timerCoin.setVisibility(View.VISIBLE);//show the coin when the game starts
                buttonBuyTime.setVisibility(View.VISIBLE);// show the buyTime button when the game starts
                textViewInput.setVisibility(View.VISIBLE);//show the textInput

                //call startTimer function
                startTimer();

                //set the visibility of the main progress bar
                mProgressBar1.setVisibility(View.VISIBLE);



            }
        });
```








                 

     



