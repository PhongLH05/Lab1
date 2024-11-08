package fpoly.acount.lab_1;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.acount.lab_1.Adapter.CoursesAdapter;
import fpoly.acount.lab_1.DAO.CoursesDAO;
import fpoly.acount.lab_1.model.Courses;


public class Lab1Main extends AppCompatActivity {
    EditText txtTitle, txtContent, txtDate, txtType, txtId;
    Button btnAdd;
    RecyclerView rcvDanhSach;
    ArrayList<String> lst = new ArrayList<>();
    ArrayList<Courses> lstKH = new ArrayList<>();
    CoursesDAO coursesDAO;
    Context context = this;
    CoursesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab1_main);

        txtContent = findViewById(R.id.txtContent);
        txtId = findViewById(R.id.ediId);
        txtTitle = findViewById(R.id.txtTitle);
        txtDate = findViewById(R.id.txtDate);
        txtType = findViewById(R.id.txtType);
        btnAdd = findViewById(R.id.btnAdd);
        rcvDanhSach = findViewById(R.id.rcvDanhSach);

        coursesDAO = new CoursesDAO(context);
        lstKH = coursesDAO.getListCourses();
        adapter = new CoursesAdapter(this, lstKH);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvDanhSach.setLayoutManager(linearLayoutManager);
        rcvDanhSach.setAdapter(adapter);

//        btnAdd.setOnClickListener(view -> {
//            Courses courses = new Courses();
//            courses.setTitle(txtTitle.getText().toString());
//            courses.setContent(txtContent.getText().toString());
//            courses.setDate(txtDate.getText().toString());
//            courses.setType(Integer.parseInt(txtType.getText().toString()));
//            coursesDAO.addCourses(courses);
//            lstKH.clear();
//            lst.clear();
//
//            lstKH = coursesDAO.getListCourses();
//            for (Courses course : lstKH){
//                lst.add(course.getId() + "-" + course.getTitle() + "-" + course.getContent() + "-" + course.getDate() + "-" + course.getType());
//            }
//            adapter.notifyDataSetChanged();
//        });

    }
}
