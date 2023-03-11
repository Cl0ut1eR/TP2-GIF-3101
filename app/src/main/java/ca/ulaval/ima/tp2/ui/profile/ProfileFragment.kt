package ca.ulaval.ima.tp2.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.Profil
import ca.ulaval.ima.tp2.R
import java.util.*

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        val textView: TextView = root.findViewById(R.id.textDescription)
        val textname = root.findViewById<TextView>(R.id.textName)
        val textLastname = root.findViewById<TextView>(R.id.textLastName)
        val textdate = root.findViewById<TextView>(R.id.textBDay)
        val textgender = root.findViewById<TextView>(R.id.textGenderValue)
        val textprogram = root.findViewById<TextView>(R.id.textProgramValue)


        val inputStream = resources.openRawResource(R.raw.ma_description)
        val text = inputStream.bufferedReader().use { it.readText() }


        textView.text = text

        val myProfil = arguments?.getParcelable<Profil>("profile")

        if(myProfil != null){
            val year = myProfil.birthDate.get(Calendar.YEAR)
            val month = myProfil.birthDate.get(Calendar.MONTH)
            val day = myProfil.birthDate.get(Calendar.DAY_OF_MONTH)
            textname.text = myProfil.firstName
            textLastname.text = myProfil.lastName
            textgender.text = myProfil.gender
            textprogram.text = myProfil.program
            textdate.text = year.toString() + " "+ month.toString()+" "+day.toString()
        }else{
            println("is null")
        }

        return root
    }
}