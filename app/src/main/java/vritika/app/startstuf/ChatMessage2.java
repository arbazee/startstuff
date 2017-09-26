package vritika.app.startstuf;

public class ChatMessage2 {
    public boolean left;
    public String message,from;

    public ChatMessage2(String message,String from, String side) {
        super();
        if(side.equals("left"))
            left=true;
        else
            left=false;
        this.left = left;
        this.message = message;
        this.from=from;
    }

}