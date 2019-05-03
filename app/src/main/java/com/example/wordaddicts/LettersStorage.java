package com.example.wordaddicts;

import android.widget.TextView;

import java.util.Random;

/**
 * Created by Tammy Le 8/4/2019
 */


public class LettersStorage {

    public static final Random RANDOM = new Random();
    public static final String[] WORDS = {"LAPTOP", "SUNDAY", "GAME", "MARVELLOUS", "WHITE", "UPDATE", "FLOWER", "HOTEL", "TEACHER", "STRUCTURE", "YOGURT", "WONDER", "HOPE",
            "BEAUTIFUL", "UNUSUAL", "JUICE", "ORANGE", "STRAWBERRY", "CUP", "POP", "FLOP", "MOP", "UNIQUE"};
    public static final String[] WORDSLEVEL1 = {"ACT", "AGE", "ACE", "ADD", "BAD", "BAR", "BED", "BIN", "CAR", "CAT","DIE", "DIG", "DOG", "DRY", "EGO", "FAR", "HER", "HIM", "HUB",
    "LAY", "LAST", "LOW", "LIE", "MAD", "MAP", "ODD", "OFF", "PAD", "PAY", "PET", "PIG", "PEN", "PUT", "RED", "SAD", "SIN", "SIT", "SAY", "SEE", "WAX", "WIN", "YOU"};

    public static final String[] WORDSLEVEL2 = {"JAZZ", "FUZZ", "BUZZ", "QUIZ", "JACK", "JUMP", "JUNK", "COZY", "JOKE", "JOWL", "JIVE", "QUIP", "BOXY", "DOZY", "FLUX", "JAWS", "JEEP",
    "JOBS", "JUGS", "LAZY", "MAZE", "JURY", "QUAY", "BUCK", "FAUX", "JAGS", "YELL", "KICK", "LYNX", "QUID", "QUIN", "AQUA", "BECK", "BACK", "BUFF", "BUMP", "CUFF", "DOSE", "DOZE",
    "EXAM", "EXPO", "JAIL", "JEAN", "JOIN", "JOLT", "MOCK", "PACK", "PICK", "PUFF", "PUMP", "QUIT", "VAMP", "BOMB", "BULK", "CAMP", "CLUB", "COMB", "DUCK", "HACK", "HAWK", "HOAX",
    "JARS", "JETS", "LUCK", "WAVY", "WEEP", "WHIP", "WOMB", "ZERO", "BABY", "BANK", "CHEW", "CHIP", "CHOP", "COPY", "DECK", "DOCK", "KNOB", "KNOW", "NECK", "PINK", "WINK", "BARK",
    "BIKE", "BLEW", "BOWL", "CALF", "CALM", "CLAP", "CLAW", "CLIP", "CUBE", "CUBS", "DUNK", "ENVY", "FAKE", "FIVE", "FLAP", "FLAW", "FLEW", "FLOW", "FORK","FOWL", "HYPE", "LAMB", "LAMP",
    "MAKE","MARK", "MASK", "PEAK", "PLUG", "PORK", "PUBS", "ROCK", "RACK", "SICK", "SKIP", "SOCK", "WEAK", "WEEK", "WOLF", "ROSE", "BODY", "BUSH", "BUSY", "CABS", "COME", "CAME",
    "CRAB", "CRAP", "CREW", "CROP", "EGGS", "FACE", "FACT", "FAME", "FLAG", "FORM", "FUND", "FURY", "GIVE", "GEEK", "GLOW", "GUMS", "HIGH", "HIVE", "HIKE", "HOOK", "MANY", "MICE"};

    public static final String[] WORDSLEVEL3 = {"JAZZY", "FUZZY", "MUZZY", "FIZZY", "DIZZY", "PIZZA", "QUICK", "ZAPPY", "ZIPPY", "JACKS", "JUMPS", "JERKY", "JUICY", "CRAZY", "FIELD",
    "BLAZE", "CHUCK", "JAPAN", "JELLY", "JERKS", "JOKER", "JOKES", "JUDGE", "JUICE", "CHECK", "CHICK", "ENJOY", "EJECT", "FROZE", "JEEPS", "JOLTY", "MAJOR", "MAZES", "PICKY", "PUPPY",
    "QUERY", "QUILL", "UNBOX", "WACKY", "ZEBRA", "BLOCK", "BLACK", "BOXED", "BUCKS", "CHAMP", "CHIMP", "CHEVY", "CHUNK", "CLICK", "CLOCK", "CLUMP", "COMFY", "DOZEN", "DUCK", "EQUAL",
    "HUBBY", "INBOX", "JELLS", "KICKS", "LUCKY", "MIXED", "MOMMY", "POPPY", "QUEEN", "QUILT", "SQUAD", "SQUID", "YUMMY", "BOXER", "BOXES", "BRICK", "CHALK", "CHEWY", "CLIMB", "CRACK",
    "CURVY", "EXACT", "EXAMS", "FOXES", "HAPPY", "HIPPY", "HOBBY", "JADES", "JEANS", "JOINT", "JOLTS", "MILKY", "PACKS", "QUIET", "QUOTE", "REMIX", "SAVVY", "SMASH", "TOXIC", "WRECK",
    "ZONES", "BAGGY", "BENCH", "BICEP", "BLANK", "BLINK", "BOMBS", "BULKS", "CHEEK", "COMBO", "COUCH", "DUCKS", "FANCY", "FINCH", "FLAWY", "FOGGY", "HACKS", "HAWKS", "MARVY", "NAPPY",
    "PEAKY", "PERKY", "SEIZE", "SIZES", "SMOKY", "SPUNK", "TACKY", "THUMB", "TUMMY", "VODKA", "WAVEY", "WIGGY", "AWFUL", "BAKED", "BANKS", "BEACH", "BEEFY", "BLOWN", "BUDDY", "BULLY",
    "CATCH", "CHARM", "CHEAP", "CHIPS", "CIVIL", "CLERK", "CLOAK", "COACH", "COUGH", "DETOX", "EMPTY", "FETCH", "FIFTY", "FIFTH", "FLAME", "FUNNY", "HEAVY", "HUNKS", "KNIFE", "MAGIC",
    };
    /**
     randomWord:

     function that choose a Random word from an array of words above

     Returns:
     return the pseudorandom word

     */
    public static String randomWord(){
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }

    /**
     shuffleWord:

     function that shuffles the chosen word

     Parameters:

     word - a string storing a word

     Returns:
     if word is not empty and the length is not space => converting word to a new character array
     then, shuffling the word

     */
    //randomWord function for specific levels
    public static String randomWordLevel1() {return WORDSLEVEL1[RANDOM.nextInt(WORDSLEVEL1.length)];}
    public static String randomWordLevel2() {return WORDSLEVEL2[RANDOM.nextInt(WORDSLEVEL2.length)];}
    public static String randomWordLevel3() {return WORDSLEVEL3[RANDOM.nextInt(WORDSLEVEL3.length)];}

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

    /**
     compareWords (added by Phong 8/4/2019)
     function compare two given words

     parameters
     two words that need to be compared

     returns a boolean value indicating if two given words are match
     */
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

    //hint function added by Phong (16/4/2019)
    //parameters : givenWord is what the right word is
    //             hintField is what already revealed
    //             coin that the player has
    //return : void
    // functionality: give hint accordingly
    public void giveHint(String givenWord, TextView hintField, int coin)
    {
        //if there is not enough coins, there will be no hint given
        if(coin <= 0)
        {
            return;
        }
        String hintRevealed = hintField.getText().toString(); // get the string from the hint field
        char[] a = givenWord.toCharArray(); // turn the givenWord into an array of chars
        char[] b = hintRevealed.toCharArray(); // turn what on the hint field into an array of char
        char[] c = new char[b.length +1 ]; // a new array which has the length of (b+1)



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

}
