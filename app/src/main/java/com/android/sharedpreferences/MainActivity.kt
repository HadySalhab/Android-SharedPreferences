package com.android.sharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch

const val SHARED_PREFERENCES_NAME = "com.android.sharedpreferences.SHARED_PREFERENCES"

class MainActivity : AppCompatActivity() {

    companion object {
        val NAME_EDIT_TEXT = "name_edit_text"
        val RADIO_BUTTON_SELECTION = "radio_button_Selection"
        val SWITCH_IS_ENABLED = "switch_is_enabled"
    }


    private lateinit var nameEditText: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonNone: RadioButton
    private lateinit var radioButtonAny: RadioButton
    private lateinit var radioButtonWifi: RadioButton
    private lateinit var switchCharging: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.name_edit_text)
        radioGroup = findViewById(R.id.radioGroup_network)
        radioButtonNone = findViewById(R.id.radioButton_None)
        radioButtonAny = findViewById(R.id.radioButton_Any)
        radioButtonWifi = findViewById(R.id.radioButton_Wifi)
        switchCharging = findViewById(R.id.switch_charging)

        restoreSharedPreferences()

    }

    fun restoreSharedPreferences() {
        val preference = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        switchCharging.isChecked = preference.getBoolean(SWITCH_IS_ENABLED, false)
        nameEditText.setText( preference.getString(NAME_EDIT_TEXT,"") )
        radioGroup.check(preference.getInt(RADIO_BUTTON_SELECTION,R.id.radioButton_None))

    }


    override fun onPause() {
        super.onPause()

        val editor = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
        editor.apply {
            putBoolean(SWITCH_IS_ENABLED, switchCharging.isChecked)
            putString(NAME_EDIT_TEXT, nameEditText.text.toString())
            putInt(RADIO_BUTTON_SELECTION, radioGroup.checkedRadioButtonId)
            apply()
        }


    }


}
