package fpoly.acount.lab_1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.acount.lab_1.DAO.CoursesDAO;
import fpoly.acount.lab_1.Lab1Main;
import fpoly.acount.lab_1.R;
import fpoly.acount.lab_1.model.Courses;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Courses> lst;
    CoursesDAO coursesDAO;

    public CoursesAdapter(Context context, ArrayList<Courses> lst) {
        this.context = context;
        this.lst = lst;
        coursesDAO = new CoursesDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_rec, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvContent.setText(lst.get(position).getContent());
        holder.tvDate.setText(lst.get(position).getDate());
        if (lst.get(position).getType() == 0){
            holder.chkbox.setChecked(false);
            holder.tvContent.setPaintFlags(holder.tvContent.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }else {
            holder.chkbox.setChecked(true);
            holder.tvContent.setPaintFlags(holder.tvContent.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        holder.chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int id = lst.get(holder.getAdapterPosition()).getId();
                boolean chk_result = coursesDAO.updateStatus(id, holder.chkbox.isChecked());
                if (chk_result){
                    lst.clear();
                    lst = coursesDAO.getListCourses();
                    notifyDataSetChanged();
                }
            }
        });

        holder.imgDelete.setOnClickListener(view -> {
            int id = lst.get(holder.getAdapterPosition()).getId();
            boolean check = coursesDAO.removeTodo(id);

            if (check){
                Toast.makeText(context, "Remove Successfully", Toast.LENGTH_SHORT).show();
                lst.clear();

                lst = coursesDAO.getListCourses();
                notifyItemRemoved(holder.getAdapterPosition());
            }else {
                Toast.makeText(context, "Remove Failed", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgEdit.setOnClickListener(view -> {
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            LayoutInflater inflater1 = ((Activity)context).getLayoutInflater();
//            View view1 = inflater1.inflate(R.layout.layout_dialog, null);
//            builder.setView(view1);
//            final EditText ediTd = view1.findViewById(R.id.ediId1);
//            final EditText txtContent = view1.findViewById(R.id.txtContent1);
//            final EditText txtTitle = view1.findViewById(R.id.txtTitle1);
//            final EditText txtDate = view1.findViewById(R.id.txtDate1);
//            final EditText txtType = view1.findViewById(R.id.txtType1);
//
//            ediTd.setText(String.valueOf(lst.get(position).getId()));
//
//            txtTitle.setText(lst.get(position).getTitle());
//            txtContent.setText(lst.get(position).getContent());
//            txtDate.setText(lst.get(position).getDate());
//            txtType.setText(String.valueOf(lst.get(position).getType()));
//            builder.setTitle("Update form");
//            builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int position) {
//                    Courses courses = new Courses();
//                    courses.setId(Integer.parseInt(ediTd.getText().toString()));
//                    courses.setTitle(txtTitle.getText().toString());
//                    courses.setContent(txtContent.getText().toString());
//                    courses.setDate(txtDate.getText().toString());
//                    courses.setType(Integer.parseInt(txtType.getText().toString()));
//                    coursesDAO.updateCourses(courses);
//                    lst.set(position, courses);
//                    notifyItemChanged(holder.getAdapterPosition());
//                }
//            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
            int id = lst.get(holder.getAdapterPosition()).getId();
            boolean chk_result = coursesDAO.updateStatus(id, holder.chkbox.isChecked());
            if (chk_result){
                Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show();
                lst.clear();
                lst = coursesDAO.getListCourses();
                notifyDataSetChanged();
            }else {
                Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvContent, tvDate, tvTitle;
        ImageView imgEdit, imgDelete;
        CheckBox chkbox;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvContent = itemView.findViewById(R.id.item_text);
            imgEdit = itemView.findViewById(R.id.item_edit);
            imgDelete = itemView.findViewById(R.id.item_delete);
            chkbox = itemView.findViewById(R.id.item_chk);
            tvDate = itemView.findViewById(R.id.item_date);
            tvTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
