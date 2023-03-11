package ca.ulaval.ima.tp2.ui.form

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.Profil
import ca.ulaval.ima.tp2.R
import ca.ulaval.ima.tp2.ui.about.ProfileFragment
import java.util.*


class FormFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        var birthDate = Calendar.getInstance().apply { set(2000, 4, 26) }

        val root = inflater.inflate(R.layout.fragment_form, container, false)
        val dateinput = root.findViewById<EditText>(R.id.bday_text)
        val btnSend = root.findViewById<Button>(R.id.btnSubmit)
        val txtName = root.findViewById<TextView>(R.id.textValueName)
        val txtFamilyName = root.findViewById<TextView>(R.id.text_FamilyName)
        val male = root.findViewById<RadioButton>(R.id.rb_sex_male)
        val female = root.findViewById<RadioButton>(R.id.rb_sex_female)
        val other = root.findViewById<RadioButton>(R.id.rb_sex_other)

        val programSelection = root.findViewById<Spinner>(R.id.programSelector)



        dateinput.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(root.context,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    // Handle the selected date here
                    dateinput.setText("$dayOfMonth/${month + 1}/$year")
                    birthDate = Calendar.getInstance().apply { set(year, month, dayOfMonth) }
                }, year, month, dayOfMonth)
            datePickerDialog.show()
        }

        btnSend.setOnClickListener(){
            if(txtName.text.toString()!="" && txtFamilyName.text.toString() != "" && dateinput.text.toString() != "") {
                var gender = "other"
                if(male.isChecked){
                    gender = "Masculin"
                }else if(female.isChecked){
                    gender = "Féminin"
                }
                println(txtName.text.toString())
                println(txtFamilyName.text.toString())
                println(birthDate.toString())
                println(gender)
                println(programSelection.selectedItem.toString())

                val myProfil = Profil(
                    txtName.text.toString(),
                    txtFamilyName.text.toString(),
                    birthDate,
                    gender,
                    programSelection.selectedItem.toString()
                    )
                val fragment = ProfileFragment() // Replace with the fragment you want to navigate to
                val bundle = Bundle()
                bundle.putParcelable("profile", myProfil)
                fragment.arguments = bundle
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host_fragment, fragment)
                transaction.addToBackStack(null)
                transaction.commit()

            }
        }


        return root
    }
}