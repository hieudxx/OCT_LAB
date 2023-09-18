package hieudx.fpoly.contentprovider_client

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val uri0001 = Uri.parse("content://hieudx.fpoly.contentprovider_src/name")
        val c = contentResolver.query(uri0001, null, null, null, null)

//        c!!.moveToFirst()
//        while (!c.isAfterLast) {
//            Log.d("zzzz", "dòng dữ liệu id = " + c.getInt(0) + ",name = " + c.getString(1))
//            c.moveToNext()
//        }
//        c.close()

        if (c != null) {
            if (c.moveToFirst()) {
                while (!c.isAfterLast) {
                    Log.d("zzzz", "dòng dữ liệu id = " + c.getInt(0) + ",name = " + c.getString(1))
                    c.moveToNext()
                }
            } else {
                Log.d("zzzz", "Không có dữ liệu trả về")
            }
            c.close()
        } else {
            Log.d("zzzz", "Truy vấn không thành công")
        }
    }
}