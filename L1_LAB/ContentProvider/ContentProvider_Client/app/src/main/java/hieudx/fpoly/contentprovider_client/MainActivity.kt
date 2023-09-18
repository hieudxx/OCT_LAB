package hieudx.fpoly.contentprovider_client

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader

class MainActivity : AppCompatActivity(),LoaderManager.LoaderCallbacks<Cursor> {
    private lateinit var lv: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var edName: EditText
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lv = findViewById(R.id.lv)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        lv.adapter = adapter
        edName = findViewById(R.id.edName)
        btnAdd = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val name = edName.text.toString()
            insertData(name)
        }
        supportLoaderManager.initLoader(0, null, this)
    }

    private fun insertData(name: String) {
        val values = ContentValues()
        values.put("name", name)
        contentResolver.insert(Uri.parse("content://hieudx.fpoly.contentprovider_src/name"), values)
        edName.text.clear()
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val projection = arrayOf("name")
        return CursorLoader(
            this,
            Uri.parse("content://hieudx.fpoly.contentprovider_src/name"),
            projection,
            null,
            null,
            null
        )
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapter.clear()
    }

    @SuppressLint("Range")
    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapter.clear()
        data?.let {
            while (it.moveToNext()) {
                val name = it.getString(it.getColumnIndex("name"))
                adapter.add(name)
            }
        }
    }
}