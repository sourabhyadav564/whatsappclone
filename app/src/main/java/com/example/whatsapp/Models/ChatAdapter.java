package com.example.whatsapp.Models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MessageModels> message;
    Context context;
    String recId;

    int RECEIVER_VIEW_TYPE = 1;
    int SENDER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<MessageModels> message, Context context) {
        this.message = message;
        this.context = context;
    }

    public ChatAdapter(ArrayList<MessageModels> message, Context context, String recId) {
        this.message = message;
        this.context = context;
        this.recId = recId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewholder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_receiver,parent,false);
            return new ReceiverViewholder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (message.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else
            return RECEIVER_VIEW_TYPE;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModels messageModels = message.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Do you want to delete this Message?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String senderRoom = FirebaseAuth.getInstance().getUid() + recId;

                                database.getReference().child("chats")
                                        .child(senderRoom).child(messageModels.messageId)
                                        .setValue(null);
                            }
                        })
                        .setNegativeButton("No",null).show();

                return false;
            }
        });

        if (holder.getClass() == SenderViewholder.class){
            ((SenderViewholder)holder).senderMessage.setText(messageModels.message);
        }
        else {
            ((ReceiverViewholder)holder).receiverMessage.setText(messageModels.message);
        }
    }

    @Override
    public int getItemCount() {
        return message.size();
    }

    public class ReceiverViewholder extends RecyclerView.ViewHolder {
        TextView receiverMessage, receiverTime;

        public ReceiverViewholder(@NonNull View itemView) {
            super(itemView);

            receiverMessage = itemView.findViewById(R.id.receiverText);
            receiverTime = itemView.findViewById(R.id.receiverTime);
        }
    }

    public class SenderViewholder extends RecyclerView.ViewHolder {
        TextView senderMessage, senderTime;
        public SenderViewholder(@NonNull View itemView) {
            super(itemView);

            senderMessage = itemView.findViewById(R.id.senderText);
            senderTime = itemView.findViewById(R.id.senderTime);
        }
    }


}
