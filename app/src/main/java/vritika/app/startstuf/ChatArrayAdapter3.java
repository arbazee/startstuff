package vritika.app.startstuf;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ChatArrayAdapter3  extends ArrayAdapter<ChatMessage3> {

    private TextView chatText,nametext;
    public List<ChatMessage3> chatMessageList = new ArrayList<ChatMessage3>();
    private Context context;

    @Override
    public void add(ChatMessage3 object)
    {
        chatMessageList.add(object);
        super.add(object);
    }


    public ChatArrayAdapter3(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }


    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage3 getItem(int index) {
        return this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {



        ChatMessage3 chatMessageObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (chatMessageObj.left) {
            row = inflater.inflate(R.layout.forumnotifyright, parent, false);
        }else{
            row = inflater.inflate(R.layout.forumnotifyleft, parent, false);
        }
        chatText =(TextView)row.findViewById(R.id.msgr);
        chatText.setMovementMethod(LinkMovementMethod.getInstance());
        chatText.setText(chatMessageObj.message);
        nametext=(TextView)row.findViewById(R.id.name);
        nametext.setText(chatMessageObj.from);

        return row;
    }
}
