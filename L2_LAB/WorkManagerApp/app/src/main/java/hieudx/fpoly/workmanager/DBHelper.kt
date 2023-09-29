package hieudx.fpoly.workmanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "work_db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "work"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "create table $TABLE_NAME( $COLUMN_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT )"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}