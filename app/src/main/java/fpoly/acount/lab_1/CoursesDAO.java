package fpoly.acount.lab_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CoursesDAO {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public CoursesDAO(Context context){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long addCourses(Courses courses){
        ContentValues values = new ContentValues();
        values.put("id", courses.getId());
        values.put("title", courses.getTitle());
        values.put("content", courses.getContent());
        values.put("date", courses.getDate());
        values.put("type", courses.getType());
        long check = database.insert("Courses", null, values);
        return check;
    }

    public long deleteCourses(int id){
        long check = database.delete("Courses", "id=? ", new String[]{String.valueOf(id)});
        return check;
    }

    public long updateCourses(Courses courses){
        ContentValues values = new ContentValues();
        values.put("id", courses.getId());
        values.put("title", courses.getTitle());
        values.put("content", courses.getContent());
        values.put("date", courses.getDate());
        values.put("type", courses.getType());
        long check = database.update("Courses", values, "id=? ", new String[]{String.valueOf(courses.getId())});
        return check;
    }

    public ArrayList<Courses> getListCourses(){
        ArrayList<Courses> lst = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM Courses", null);

            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    lst.add(new Courses(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(4)));
                }while (cursor.moveToNext());
            }
        }catch (Exception e){

            Log.e("TAG", e.getMessage());
        }
        return lst;
    }
}
