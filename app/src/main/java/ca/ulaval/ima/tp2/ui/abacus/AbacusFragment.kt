package ca.ulaval.ima.tp2.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.R

class AbacusFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_abacus, container, false)
        val seekBar1 = root.findViewById<SeekBar>(R.id.seekBar1)
        val lblValue1 = root.findViewById<TextView>(R.id.lblValueSlider1)
        val seekBar2 = root.findViewById<SeekBar>(R.id.seekBar2)
        val lblValue2 = root.findViewById<TextView>(R.id.lblValueSlider2)
        val seekBar3 = root.findViewById<SeekBar>(R.id.seekBar3)
        val lblValue3 = root.findViewById<TextView>(R.id.lblValueSlider3)

        //seekBar1.min = 1 //requires api 26
        //seekBar2.min = 2 //requires api 26

        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // This method will be called when the user drags the SeekBar thumb
                // You can perform any actions you want here, based on the new progress value
                if(seekBar1.progress < 1){
                    seekBar1.progress = 1
                }
                lblValue1.text = seekBar1.progress.toString()
                var result = seekBar1.progress * seekBar2.progress
                seekBar3.progress = result
                lblValue3.text = result.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // This method will be called when the user first touches the SeekBar thumb
                // You can perform any actions you want here, such as pausing or stopping playback
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // This method will be called when the user releases the SeekBar thumb
                // You can perform any actions you want here, such as resuming or starting playback
            }
        })

        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // This method will be called when the user drags the SeekBar thumb
                // You can perform any actions you want here, based on the new progress value
                if(seekBar2.progress < 2){
                    seekBar2.progress = 2
                }
                lblValue2.text = seekBar2.progress.toString()
                var result = seekBar1.progress * seekBar2.progress
                seekBar3.progress = result
                lblValue3.text = result.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // This method will be called when the user first touches the SeekBar thumb
                // You can perform any actions you want here, such as pausing or stopping playback
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // This method will be called when the user releases the SeekBar thumb
                // You can perform any actions you want here, such as resuming or starting playback
            }
        })

        seekBar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // This method will be called when the user drags the SeekBar thumb
                // You can perform any actions you want here, based on the new progress value
                var result = seekBar1.progress * seekBar2.progress
                seekBar3.progress = result
                lblValue3.text = result.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // This method will be called when the user first touches the SeekBar thumb
                // You can perform any actions you want here, such as pausing or stopping playback
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // This method will be called when the user releases the SeekBar thumb
                // You can perform any actions you want here, such as resuming or starting playback
            }
        })

        return root
    }
}