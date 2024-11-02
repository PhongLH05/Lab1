package fpoly.acount.lab_1;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Lab1Main extends AppCompatActivity {
    EditText txtTitle, txtContent, txtDate, txtType, txtId;
    Button btnAdd, btnUpdate, btnDelete;
    ListView lvDemo;
    ArrayList<String> lst = new ArrayList<>();
    ArrayList<Courses> lstKH = new ArrayList<>();
    CoursesDAO coursesDAO;
    Context context = this;

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
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        lvDemo = findViewById(R.id.LvDemo);

        coursesDAO = new CoursesDAO(context);
        lst.clear();
        lstKH.clear();

        lstKH = coursesDAO.getListCourses();
        for (Courses courses : lstKH){
            lst.add(courses.getId() + "-" + courses.getTitle() + "-" + courses.getContent() + "-" + courses.getDate() + "-" + courses.getType());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lst);
        lvDemo.setAdapter(adapter);
        btnAdd.setOnClickListener(view -> {
            Courses courses = new Courses();
            courses.setTitle(txtTitle.getText().toString());
            courses.setContent(txtContent.getText().toString());
            courses.setDate(txtDate.getText().toString());
            courses.setType(Integer.parseInt(txtType.getText().toString()));
            coursesDAO.addCourses(courses);
            lstKH.clear();
            lst.clear();

            lstKH = coursesDAO.getListCourses();
            for (Courses course : lstKH){
                lst.add(course.getId() + "-" + course.getTitle() + "-" + course.getContent() + "-" + course.getDate() + "-" + course.getType());
            }
            adapter.notifyDataSetChanged();
        });

        btnAdd.setOnClickListener(view -> {
            Courses courses = new Courses();
            courses.setId(Integer.parseInt(txtId.getText().toString()));
            courses.setTitle(txtTitle.getText().toString());
            courses.setContent(txtContent.getText().toString());
            courses.setDate(txtDate.getText().toString());
            courses.setType(Integer.parseInt(txtType.getText().toString()));
            coursesDAO.addCourses(courses);
            lstKH.clear();
            lst.clear();
            lstKH = coursesDAO.getListCourses();
            for (Courses course : lstKH){
                lst.add(course.getId() + "-" + course.getTitle() + "-" + course.getContent() + "-" + course.getDate() + "-" + course.getType());
            }
            adapter.notifyDataSetChanged();
        });

        btnDelete.setOnClickListener(view -> {
            int id = Integer.parseInt(txtId.getText().toString());
            coursesDAO.deleteCourses(id);
            lstKH.clear();
            lst.clear();
            lstKH = coursesDAO.getListCourses();
            for (Courses course : lstKH){
                lst.add(course.getId() + "-" + course.getTitle() + "-" + course.getContent() + "-" + course.getDate() + "-" + course.getType());
            }
            adapter.notifyDataSetChanged();
        });

        btnUpdate.setOnClickListener(view -> {
            Courses courses = new Courses();
            courses.setId(Integer.parseInt(txtId.getText().toString()));
            courses.setTitle(txtTitle.getText().toString());
            courses.setContent(txtContent.getText().toString());
            courses.setDate(txtDate.getText().toString());
//            courses.setType(Integer.parseInt(txtType.getText().toString()));
            coursesDAO.updateCourses(courses);

            lstKH.clear();
            lst.clear();
            lstKH = coursesDAO.getListCourses();
            for (Courses course : lstKH){
                lst.add(course.getId() + "-" + course.getTitle() + "-" + course.getContent() + "-" + course.getDate() + "-" + course.getType());
            }
            adapter.notifyDataSetChanged();
        });
    }
}
