package com.example.sqlite_me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdpater extends RecyclerView.Adapter<StudentAdpater.StudentViewholder> {

    private Context context;
    private ArrayList<Student> list;

    public StudentAdpater(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public StudentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_layout,parent,false);
        return (new StudentViewholder(view));

    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewholder holder, int position) {
        Student student = list.get(position);
        holder.tname.setText(student.getName());
        holder.tsid.setText(student.getSid());
        holder.tbranch.setText(student.getBranch());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StudentViewholder extends RecyclerView.ViewHolder
    {
        TextView tname,tsid,tbranch;

        public StudentViewholder(@NonNull View itemView) {
            super(itemView);
            tname = itemView.findViewById(R.id.tv_student_name);
            tsid = itemView.findViewById(R.id.tv_student_id);
            tbranch = itemView.findViewById(R.id.tv_student_branch);

        }
    }



}
