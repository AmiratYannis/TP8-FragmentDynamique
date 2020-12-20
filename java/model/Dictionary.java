package model;

import android.util.Log;

import com.example.scrabbleamiratyannis.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
    private static String[] wordList;

    public Dictionary(InputStream is) throws IOException,FileNotFoundException {
        //File f=new File(filename);
        //InputStream is=getRessources().openRawRessource(R.raw.frutf8);
        InputStreamReader isr=new InputStreamReader(is);

        BufferedReader lecteur=new BufferedReader(isr);
        String line="";
        int cpt=0;
        wordList=new String[Integer.parseInt(lecteur.readLine())];
        //lecteur=new BufferedReader(isr);
        while((line=lecteur.readLine())!=null){
            wordList[cpt]=line;
            cpt++;
            //wordList=new String[cpt+1];
        }
       // Arrays.sort(wordList);


    }

    public boolean isValidWord(String word){
        if(Arrays.binarySearch(wordList,word)<0){
            return  false;
        }
        else{
            return true;
        }
    }

    public static boolean mayBeComposed(String word,char[] letters){
        word=replaceFrenchCaracter(word);


        for(int i=0;i<letters.length;++i){
            if(letters[i]=='*'){
                continue;
            }
            if(word.indexOf(letters[i])==-1){
                return false;
            }
        }
        return true;
    }

    public static LinkedList<Character> letterUsed(String word,char[] letters){
        LinkedList<Character> list=new LinkedList<Character>();
        boolean letterUsed=false;
       int  cpt=0;
        word=word.toLowerCase();

        for(int i=0;i<word.length();++i){
            boolean verif=containsLetter(letters,word.charAt(i));
            if(verif){
                if(!list.contains(word.charAt(i))){
                    if(posLetters(letters,word.charAt(i))<list.size()){
                        list.add(posLetters(letters,word.charAt(i)),word.charAt(i));
                    }
                    else{
                        list.add(word.charAt(i));
                    }

                    //cpt++;
                }else{
                    letterUsed=true;
                }

            }

        }
        if(letterUsed){
            list.add('*');
        }
        return list;
    }

    public static String replaceFrenchCaracter(String s){
        String newString="";
        s=s.toLowerCase();
        for(int i=0;i<s.length();++i){
            if(s.charAt(i)=='à'||s.charAt(i)=='â'||s.charAt(i)=='ä'){
                newString+='a';
            }
            else if(s.charAt(i)=='ç'){
                newString+='c';
            }
            else if(s.charAt(i)=='é'||s.charAt(i)=='è'||s.charAt(i)=='ê'||s.charAt(i)=='ë'){
                newString+='e';
            }
            else if(s.charAt(i)=='î'||s.charAt(i)=='ï'){
                newString+='i';
            }
            else if(s.charAt(i)=='ô'||s.charAt(i)=='ö'){
                newString+='o';
            }
            else if(s.charAt(i)=='ù'||s.charAt(i)=='ü'||s.charAt(i)=='û'){
                newString+='u';
            }
            else if(s.charAt(i)=='œ'){
                newString+="oe";
            }
            else if(s.charAt(i)=='æ'){
                newString+="ae";
            }
            else {
                newString += s.charAt(i);
            }

        }
        return newString;
    }

    public static List<String> getWordsThatCanBeComposed(char[] letters){
        List<String> words=new ArrayList<String>();
        for(int i=0;i<wordList.length;++i){
            if(mayBeComposed(wordList[i],letters)){
                words.add(wordList[i]);
            }
        }
        return words;
    }

    public static char[] getCompoisition(String word,char[] letters){
            List<Character> letter=letterUsed(word,letters);

            char[] array=new char[letter.size()];
            int i=0;
            for(Character c:letter){

                array[i]=c;
                i++;
            }
            return array;

    }

    public static String convertCharArrayToString(char[] letters){
        String s="[";
        for(int i=0;i<letters.length;++i){
            if(i<letters.length-1){
                s+=" "+letters[i]+",";
            }
            else{
                s+=letters[i]+" ";
            }
        }
        s+="]";
        return  s;
    }

    public static Data[] getDataList(char[] letter){
        List<String> words=getWordsThatCanBeComposed(letter);
        Data[] dataList=new Data[words.size()];
        Log.i("taille", String.valueOf(words.size()));
        for(int i=0;i<words.size();++i){

            Data data=new Data(words.get(i),convertCharArrayToString(getCompoisition(words.get(i),letter)));
            dataList[i]=data;
        }
        return  dataList;
    }

    public static String[] resultToArray(char[] letters){
        Data[] dataList=getDataList(letters);
        ScrabbleComparator sc=new ScrabbleComparator(letters);
        Arrays.sort(dataList,sc);
        String[] result=new String[dataList.length+1];

        for(int i=0;i<dataList.length+1;++i){
            if(i==0){
                result[i]=dataList.length+" word(s) found";
            }
            else{
                ScrabbleComparator comp=new ScrabbleComparator(letters);
                int value=comp.wordValue(dataList[i-1].getCompose());
                result[i]="- "+dataList[i-1].getWords()+" ("+dataList[i-1].getCompose()+" "+value+")";
            }

        }
        return result;
    }
    public String toString(){
        String s="";
        /*for(int i=0;i<wordList.length;++i){
            s+=wordList[i]+"\n";
        }*/
        s+=wordList[0]+"\n";
       s+=wordList[1]+"\n";
       s+=wordList[2]+"\n";
        return s;
    }


    public static boolean containsLetter(char[] letters, char c){
        for (char car:letters){
                if(car==c){
                    return true;
                }
        }
        return false;
    }

    public static int posLetters(char[] letters,char c){
        for (int i=0;i<letters.length;++i){
            if(letters[i]==c){
                return i;
            }
        }
        return -1;
    }
}
