package vritika.app.startstuf;


public class ChatMessage3 {
    public boolean left;
    public String message,from;

    public ChatMessage3(String message,String from, String side) {
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