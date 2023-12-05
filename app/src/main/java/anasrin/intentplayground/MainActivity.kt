package anasrin.intentplayground

import java.lang.Exception
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.view.View
import android.widget.TextView
import android.widget.Spinner
import android.widget.AdapterView
import android.widget.EditText
import android.widget.CheckBox
import android.widget.Button
import android.widget.Toast
import anasrin.intentplayground.R

class MainActivity : AppCompatActivity() {
    companion object {
        private const val LOG_DEBUG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: Implement preset
        val textPreset = findViewById<TextView>(R.id.textPreset)
        textPreset.setVisibility(View.GONE)
        val spinnerPreset = findViewById<Spinner>(R.id.spinnerPreset)
        spinnerPreset.setVisibility(View.GONE)

        val editTextAction = findViewById<EditText>(R.id.editTextAction)

        val spinnerAction = findViewById<Spinner>(R.id.spinnerAction)
        spinnerAction.setSelection(47) // MAIN
        spinnerAction.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val s = spinnerAction.selectedItem.toString()
                val action = "android.intent.action.$s"
                editTextAction.setText(action)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val editTextCategory = findViewById<EditText>(R.id.editTextCategory)

        val spinnerCategory = findViewById<Spinner>(R.id.spinnerCategory)
        spinnerCategory.setSelection(26) // LAUNCHER
        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val s = spinnerCategory.selectedItem.toString()
                val category = "android.intent.category.$s"
                editTextCategory.setText(category)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val checkBoxUseCategory = findViewById<CheckBox>(R.id.checkBoxUseCategory)
        checkBoxUseCategory.setOnCheckedChangeListener { _, isChecked ->
          editTextCategory.isEnabled = isChecked
          spinnerCategory.isEnabled = isChecked
        }

        val editTextPackageName = findViewById<EditText>(R.id.editTextPackageName)
        val editTextClassName = findViewById<EditText>(R.id.editTextClassName)

        val checkBoxUsePackage = findViewById<CheckBox>(R.id.checkBoxUsePackage)
        checkBoxUsePackage.setOnCheckedChangeListener { _, isChecked ->
            editTextPackageName.isEnabled = isChecked
            editTextClassName.isEnabled = isChecked
        }

        val buttonRun = findViewById<Button>(R.id.buttonRun)
        buttonRun.setOnClickListener {
            val action = editTextAction.text.toString()
            val category = editTextCategory.text.toString()
            val packageName = editTextPackageName.text.toString()
            val className = editTextClassName.text.toString()

            // Log.d(LOG_DEBUG, "SPINNER_ACTION: $action")
            // Log.d(LOG_DEBUG, "SPINNER_CATEGORY: $category")
            // Log.d(LOG_DEBUG, "TEXT_PACKAGE_NAME: $packageName")
            // Log.d(LOG_DEBUG, "TEXT_CLASS_NAME: $className")

            val intent = Intent()
            intent.setAction(action)
            if (checkBoxUseCategory.isChecked()){
                intent.addCategory(category)
            }
            if (checkBoxUsePackage.isChecked()){
                intent.setClassName(packageName, className)
            }
            // TODO: Implement `putExtra` and `type`
            try {
                startActivity(intent)
            } catch (err: Exception) {
                Toast.makeText(this@MainActivity, "Intent Failed To Start", Toast.LENGTH_LONG).show();
            }
        }
    }
}
