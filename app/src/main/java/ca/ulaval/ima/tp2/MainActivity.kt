package ca.ulaval.ima.tp2

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import ca.ulaval.ima.tp2.ui.about.AbacusFragment
import ca.ulaval.ima.tp2.ui.about.ProfileFragment
import ca.ulaval.ima.tp2.ui.about.AboutFragment
import ca.ulaval.ima.tp2.ui.internet_status.InternetStatusFragment
import ca.ulaval.ima.tp2.ui.form.FormFragment
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, AboutFragment())
        transaction.commit()

    }

    override fun onBackPressed() {
        val drawer = findViewById<View?>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.nav_InternetStatus) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, InternetStatusFragment())
            transaction.commit()
        } else if (id == R.id.nav_about) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, AboutFragment())
            transaction.commit()
        } else if (id == R.id.nav_abacus) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, AbacusFragment())
            transaction.commit()
        } else if (id == R.id.nav_form) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, FormFragment())
        transaction.commit()
        }else if (id == R.id.nav_profile) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, ProfileFragment())
        transaction.commit()
        }
        val drawer = findViewById<View?>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true

    }

}
class Profil(var firstName: String, var lastName: String, var birthDate: Calendar, var gender: String, var program: String) :
    Parcelable {
    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Profil>{
            override fun createFromParcel(parcel: Parcel) = Profil(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Profil>(size)
        }
    }
    constructor(parcel: Parcel) : this(
        firstName = parcel.readString() ?: "",
        lastName = parcel.readString() ?: "",
        birthDate = parcel.readSerializable() as Calendar,
        gender = parcel.readString() ?: "",
        program = parcel.readString() ?: "",
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeSerializable(birthDate)
        parcel.writeString(gender)
        parcel.writeString(program)
    }
}