package ca.ulaval.ima.tp2.ui.internet_status

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.R


class InternetStatusFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_internet_status, container, false)
        val btnCheckStatus = root.findViewById<Button>(R.id.checkInternetStatus_button)
        val imgStatus = root.findViewById<ImageView>(R.id.imageView_indicator_internetStatus)
        val lblConnection = root.findViewById<TextView>(R.id.text_internetStatus)

        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager




        btnCheckStatus.setOnClickListener(){
            val activeNetwork = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected()) {
                imgStatus.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN)
                when (activeNetwork.type) {
                    ConnectivityManager.TYPE_WIFI -> {
                        println("network wifi")
                        lblConnection.text = "WIFI"
                    }
                    ConnectivityManager.TYPE_MOBILE -> {
                        var type = "network unknown"
                        when (activeNetwork.subtype) {
                            TelephonyManager.NETWORK_TYPE_GPRS,
                            TelephonyManager.NETWORK_TYPE_CDMA,
                            TelephonyManager.NETWORK_TYPE_1xRTT,
                            TelephonyManager.NETWORK_TYPE_IDEN -> {
                                type = "2G"
                            }
                            TelephonyManager.NETWORK_TYPE_EDGE ->{
                                type = "2G EDGE"
                            }
                            TelephonyManager.NETWORK_TYPE_UMTS,
                            TelephonyManager.NETWORK_TYPE_EVDO_0,
                            TelephonyManager.NETWORK_TYPE_EVDO_A,
                            TelephonyManager.NETWORK_TYPE_HSDPA,
                            TelephonyManager.NETWORK_TYPE_HSUPA,
                            TelephonyManager.NETWORK_TYPE_HSPA,
                            TelephonyManager.NETWORK_TYPE_EVDO_B,
                            TelephonyManager.NETWORK_TYPE_EHRPD,
                            TelephonyManager.NETWORK_TYPE_HSPAP -> {
                                type = "3G"
                            }
                            TelephonyManager.NETWORK_TYPE_IWLAN -> {
                                type = "4G"
                            }
                            TelephonyManager.NETWORK_TYPE_LTE->{
                                type = "4G LTE"
                            }
                            TelephonyManager.NETWORK_TYPE_NR -> {
                                type = "5G"
                            }
                        }
                        lblConnection.text = type
                    }
                }
            }
            else{
                imgStatus.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
                lblConnection.text = "Non connecté"
            }
        }

        return root
    }
}