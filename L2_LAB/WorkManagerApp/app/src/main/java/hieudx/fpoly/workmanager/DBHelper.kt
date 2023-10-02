package hieudx.fpoly.workmanager

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import hieudx.fpoly.workmanager.model.Work

class DBHelper(context: Context) :
//factory: dùng để tạo các đối tượng con trỏ, hoặc null cho mặc định
// version: số phiên bản của csdl (bắt đầu từ 1)
// nếu csdl cũ hơn, onUpgrade đc gọi
// nếu csdl mới hơn, onDowngrade đc gọi
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "WORK.DB"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "work"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_TIME = "time"
    }

    override fun onCreate(db: SQLiteDatabase?) {
//        val createTable =
        db?.execSQL("create table $TABLE_NAME( $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_TIME TEXT )")
        db?.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_TIME) VALUES ('Đi học','2/10/2023')")
        db?.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_TIME) VALUES ('Đi đá bóng','3/11/2023')")
        db?.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_TIME) VALUES ('Đi xem phim','4/12/2023')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p1 != p2) {
            val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
            db?.execSQL(dropTable)
            onCreate(db)
        }

    }

    fun updateWork(work: Work) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, work.name)
            put(COLUMN_TIME, work.time)
        }
        db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(work.id.toString()))
        db.close()
    }
}