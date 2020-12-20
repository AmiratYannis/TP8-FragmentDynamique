package model;

import java.util.Comparator;

public class ScrabbleComparator implements Comparator<Data> {
    private char[] letters;

    public ScrabbleComparator(char[] letters){
        this.letters=letters;
    }

    public static int letterValue(char letter){
        if(letter=='*'){
            return 0;
        }
        else if(letter=='e'||letter=='a'||letter=='i'||letter=='n'||letter=='o'||letter=='r'||letter=='s'||letter=='t'||letter=='u'||letter=='l'){
            return 1;
        }
        else if(letter=='d'||letter=='m'||letter=='g'){
            return 2;
        }
        else if(letter=='b'||letter=='c'||letter=='p'){
            return 3;
        }
        else if(letter=='f'||letter=='h'||letter=='v'){
            return 4;
        }
        else if(letter=='j'||letter=='q'){
            return 8;
        }
        else{
            return 10;
        }

    }

    public static int lettersValue(char[] letters){
        int cpt=0;
        for(int i=0;i<letters.length;++i){
            cpt+=letterValue(letters[i]);
        }
        return cpt;
    }

    public int wordValue(String word){
        int cpt=0;
        for(int i=0;i<word.length();++i){
            if(word.charAt(i)=='['||word.charAt(i)==','||word.charAt(i)==']'||word.charAt(i)==' '){
                continue;
            }
            cpt+=letterValue(word.charAt(i));
        }
        return cpt;
    }


    @Override
    public int compare(Data o1, Data o2) {
        if(wordValue(o1.getCompose())!=wordValue(o2.getCompose())){
            return 1;
        }
        else{
            return 0;
        }
    }
}
