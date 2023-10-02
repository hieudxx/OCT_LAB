package hieudx.fpoly.workmanager

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class WorkProvider() : ContentProvider() {

    val AUTHORITY = "hieudx.fpoly.workmanager"
    var TABLE_NAME = "work"
    var uriMatcher: UriMatcher? = null
    var dao: WorkDao? = null
    var cursor: Cursor? = null

    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/work")

    private lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {

        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher?.addURI(AUTHORITY, TABLE_NAME, 1)
        uriMatcher?.addURI(AUTHORITY, TABLE_NAME + "/#", 2)

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
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        cursor?.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
//        return when (uriMatcher?.match(uri)) {
//            WORKS -> "vnd.android.cursor.dir/vnd.$AUTHORITY.$TABLE_NAME"
//            WORK_ID -> "vnd.android.cursor.item/vnd.$AUTHORITY.$TABLE_NAME"
//            else -> throw IllegalArgumentException("Unknown URI: $uri")
//        }
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