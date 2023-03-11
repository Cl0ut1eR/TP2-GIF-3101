package ca.ulaval.ima.tp2.ui.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.R

class AboutFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_about, container, false)
        val textView: TextView = root.findViewById(R.id.text_about)

        val inputStream = resources.openRawResource(R.raw.a_propos)
        val text = inputStream.bufferedReader().use { it.readText() }

        textView.text = text

        return root
    }
}