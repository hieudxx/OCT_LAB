package hieudx.fpoly.workmanager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import hieudx.fpoly.workmanager.model.Work

class WorkDao(context: Context) {
    // SELECT * FROM TABLE : lấy toàn bộ data
// SELECT * FROM TABLE WHERE ĐK: lấy những data nào thỏa mãn ĐK

// INSERT INTO TABLE("","","",....) VALUES ('','','',...)

//  DELETE FROM TABLE WHERE ĐK userId=1: xóa dòng có thuộc tính userId=1 ra khỏi cột userId từ bảng TABLE

    //  UPDATE TABLE SET username="" WHERE userId=4 : cập nhật dòng có userId=4 tại cột userId trong bảng TABLE thành dữ liệu mới có trong username=""
    private val dbHelper: DBHelper

    init {
        dbHelper = DBHelper(context)
    }

    fun insert(name: String, time: String): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("${DBHelper.COLUMN_NAME}", name)
            put("${DBHelper.COLUMN_TIME}", time)
        }
        val check = db.insert("${DBHelper.TABLE_NAME}", null, values)
        db.close()
        return check != -1L
    }

    fun deleteWork(id: Int): Int {
        val db = dbHelper.writableDatabase
        val check: Int =
            db.delete("${DBHelper.TABLE_NAME}", "${DBHelper.COLUMN_ID}=?", arrayOf(id.toString()))
        if (check == -1) {
            return 0
        }
        db.close()
        return 1

    }

    fun getAllTasks(): ArrayList<Work> {
        val list = ArrayList<Work>()
        val db = dbHelper.readableDatabase
//        val selectQuery =
        val c: Cursor? = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_NAME}", null)
        c?.let {
            if (it.moveToFirst()) {
                do {
                    list.add(Work(it.getInt(0), it.getString(1), it.getString(2)))
                } while (it.moveToNext())
            }
            it.close()
        }
        return list
    }

    fun getAllProvider(
        colums: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        oderBy: String?
    ): Cursor? {
        val db = dbHelper.readableDatabase
        return db?.query(DBHelper.TABLE_NAME, colums, selection, selectionArgs, null, null, oderBy)
    }

//                    val name = it.getString(it.getColumnIndex(DBHelper.COLUMN_NAME))
//                    val id = it.getInt(it.getColumnIndex(DBHelper.COLUMN_ID))
//                    val time = it.getString(it.getColumnIndex(DBHelper.COLUMN_TIME))
//                    work.add(Work(id, name, time).toString())
}