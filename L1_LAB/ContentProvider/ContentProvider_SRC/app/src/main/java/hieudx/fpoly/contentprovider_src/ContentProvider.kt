package hieudx.fpoly.contentprovider_src

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

open class ContentProvider : ContentProvider() {

    val AUTHORITY = "hieudx.fpoly.contentprovider_src"
    var TABLE_NAME = "demo_contentProvider"
    var uriMatcher: UriMatcher? = null
    var dao: dao? = null
    var cursor: Cursor? = null

    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/demo_contentProvider")

    private lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {

        //khởi tạo các giá trị
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher?.addURI(AUTHORITY, TABLE_NAME, 1)
        uriMatcher?.addURI(AUTHORITY, TABLE_NAME + "/#", 2)

        //khởi tạo DAO
        dao = context?.let { dao(it) }
        return false

//        context?.let {
//            val dbHelper = DBHelper(it)
//            db = dbHelper.writableDatabase
//        }
//        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {

        val code_uri_matcher = uriMatcher?.match(uri)
        when (code_uri_matcher) {
            1 -> cursor =
                dao?.providerSelectAll(projection, selection, selectionArgs, sortOrder)

            2 -> {
                val strWhere = "id = " + uri.pathSegments[1]
                cursor =
                    dao?.providerSelectAll(projection, strWhere, selectionArgs, sortOrder)
            }
        }
        return cursor

//        return db.query(
//            DBHelper.TABLE_NAME,
//            projection,
//            selection,
//            selectionArgs,
//            null,
//            null,
//            sortOrder
//        )
    }

    override fun getType(p0: Uri): String? {
        return null
    }

    override fun insert(p0: Uri, values: ContentValues?): Uri? {
        val id = db.insert(DBHelper.TABLE_NAME, null, values)
        return Uri.withAppendedPath(CONTENT_URI, id.toString())
    }

    override fun delete(p0: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return db.delete(
            DBHelper.TABLE_NAME,
            selection,
            selectionArgs
        )
    }

    override fun update(
        p0: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return db.update(
            DBHelper.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
    }

}