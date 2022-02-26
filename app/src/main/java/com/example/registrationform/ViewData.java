package com.example.registrationform;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ViewData extends RecyclerView.Adapter<ViewData.viewHolder> {

    Context context;
    List<UserDatabase> userList;

    public ViewData() {

    }

    public ViewData(Context context, List<UserDatabase> userData) {
        this.context = context;
        this.userList = userData;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_one, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String username = userList.get(position).getUsername();
        String email_ID = userList.get(position).getEmail();
        holder.setData(username,email_ID);

    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView txt1, txt2;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            txt1 = itemView.findViewById(R.id.user);
            txt2 = itemView.findViewById(R.id.mail);
        }

        public void setData(String username, String email_id) {
            txt1.setText(username);
            txt2.setText(email_id);
        }
    }
}

//@NonNull

//    @Override
//    public int getCount() {
//
//        return userList.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return userList.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        if (inflater == null) {
//            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        if (view == null) {
//            view = inflater.inflate(R.layout.ui_one, null);
//        }
//
///*        txt1 = view.findViewById(R.id.user);
//        txt1 = view.findViewById(R.id.fname);
//        txt1 = view.findViewById(R.id.phone);
//        txt1 = view.findViewById(R.id.mail);
//        txt1 = view.findViewById(R.id.pass);
//        txt1 = view.findViewById(R.id.cf);
//        */
//
//
//        MyViewHolder myViewHolder = new MyViewHolder(view);
//        myViewHolder.txt1.setText(userList.get(i).getUsername());
//        myViewHolder.txt4.setText(userList.get(i).getEmail());
//
//
////        myViewHolder.txt2.setText(userList.get(i).getFullName());
////        myViewHolder.txt3.setText(userList.get(i).getPhoneNumber());
//
////        myViewHolder.txt5.setText(userList.get(i).getPassword());
////        myViewHolder.txt6.setText(userList.get(i).getConfirmPassword());
//
//        return view;
//    }
