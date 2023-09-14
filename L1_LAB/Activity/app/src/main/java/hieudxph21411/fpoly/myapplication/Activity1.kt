package hieudxph21411.fpoly.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly

class Activity1 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)

        var edId: EditText = findViewById(R.id.edId)
        var edName: EditText = findViewById(R.id.edName)
        val btnSend: Button = findViewById(R.id.btnSend)

        val intent = Intent(this, Activity2::class.java)
        val bundle = Bundle()
        btnSend.setOnClickListener {
            if (edId.text.isNotEmpty() && edId.text.isDigitsOnly()) {
                bundle.putInt("id", edId.text.toString().toInt())
                bundle.putString("name", edName.text.toString())
                intent.putExtras(bundle)

                startActivity(intent)

            } else {
                Toast.makeText(this, "Hãy nhập đúng giá trị", Toast.LENGTH_SHORT).show()
            }

        }


    }
}