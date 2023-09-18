package hieudx.fpoly.contentprovider_src

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class dao {
    private var DBHelper: DBHelper? = null
    private var db: SQLiteDatabase? = null

    constructor(context: Context) {
        DBHelper = DBHelper(context)
        db = DBHelper?.readableDatabase
    }

    fun providerSelectAll(
        colums: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        oderBy: String?
    ): Cursor? {
        val c: Cursor? =
            db?.query("demo_contentProvider", colums, selection, selectionArgs, null, null, oderBy)
        return c
    }
}