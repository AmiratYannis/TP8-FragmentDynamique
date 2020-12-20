package model;

public class Data {
    private String words;
    private String compose;
    public Data(String word,String compose){
        this.words=word;
        this.compose=compose;
    }

    public String getWords(){
        return words;
    }
    public String getCompose(){
        return compose;
    }

}
