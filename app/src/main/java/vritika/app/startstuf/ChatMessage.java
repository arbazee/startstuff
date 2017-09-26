package vritika.app.startstuf;


public class ChatMessage {
    public boolean left;
    public String message;

    public ChatMessage(String message, String side) {
        super();
        if(side.equals("left"))
            left=true;
        else
            left=false;

        this.left = left;
        this.message = message;
    }
    public String getMessagelist(){
        return message;
    }
}