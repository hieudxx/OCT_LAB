package hieudx.fpoly.workmanager

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class WorkProvider() : ContentProvider() {

    private val AUTHORITY = "hieudx.fpoly.workmanager"
    private var TABLE_NAME = "work"
    private var uriMatcher: UriMatcher? = null
    private var dao: WorkDao? = null
    private var cursor: Cursor? = null

    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/work")

    private lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        val dbHelper = context?.let { DBHelper(it) }
        db = dbHelper?.readableDatabase!!

        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher?.addURI(AUTHORITY, TABLE_NAME, 1)
        uriMatcher?.addURI(AUTHORITY, "$TABLE_NAME/#", 2)

        dao = context?.let { WorkDao(it) }
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        when (uriMatcher?.match(uri)) {
            1 -> cursor = dao?.getAllProvider(projection, selection, selectionArgs, sortOrder)
//            1 -> cursor =
//                db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)

            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        cursor?.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher?.match(uri)) {
            1 -> "vnd.android.cursor.dir/$AUTHORITY.work"
            2 -> "vnd.android.cursor.item/$AUTHORITY.work"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        return null
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
}