package com.santiagogarciav.blocdenotas

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.OutputStream

private fun OutputStream.append(s: String) {

}

class MainActivity : AppCompatActivity() {

    private lateinit var saveBtn: Button
    private lateinit var showBtn: Button
    private lateinit var clearBtn: Button
    private lateinit var titleTxt: TextView
    private lateinit var noteTxt:TextView
    private lateinit var sharedPreferences: SharedPreferences
//    static final int READ_BLOCK_SIZE=100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        saveBtn=findViewById(R.id.save_button)
        showBtn=findViewById(R.id.show_button)
        clearBtn=findViewById(R.id.clear_button)
        titleTxt=findViewById(R.id.title_edit_text)
        noteTxt=findViewById(R.id.note_edit_text_multiLine)

        sharedPreferences = getSharedPreferences("Notes", Context.MODE_PRIVATE)

        saveBtn.setOnClickListener {
            val title = titleTxt.text.toString()
            val content = noteTxt.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString(title, content)
                editor.apply()
                Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, ingresa un título y contenido", Toast.LENGTH_SHORT).show()
            }
        }

        showBtn.setOnClickListener {
            val title = titleTxt.text.toString()
            val content = sharedPreferences.getString(title, "")

            if (content.isNullOrEmpty()) {
                Toast.makeText(this, "La nota no existe", Toast.LENGTH_SHORT).show()
            } else {
                noteTxt.setText(content)
            }
        }

        clearBtn.setOnClickListener {
//            titleTxt.text.clear()
//            noteTxt.text.clear()
            noteTxt.setText("")
            titleTxt.setText("")
        }

    }

}

private fun CharSequence.clear() {
    TODO("Not yet implemented")
}
