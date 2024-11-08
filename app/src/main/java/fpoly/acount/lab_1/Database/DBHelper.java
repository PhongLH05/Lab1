package fpoly.acount.lab_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context, "ToDoDB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Courses (\n" +
                "  id interger PRIMARY KEY,\n" +
                "  title text,\n" +
                "  content text,\n" +
                "  date text,\n" +
                "  type interger\n" +
                ")";
        sqLiteDatabase.execSQL(sql);

        String insertData = "INSERT INTO Courses VALUES (1, 'Hoc Java', 'Co ban', '12/12/2024', 0),\n" +
                "(2, 'Hoc Kotlin', 'Co ban', '12/12/2024', 0),\n" +
                "(3, 'Hoc C++', 'Co ban', '12/12/2024', 0)";
        sqLiteDatabase.execSQL(insertData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Courses");
            onCreate(sqLiteDatabase);
        }

    }
}
