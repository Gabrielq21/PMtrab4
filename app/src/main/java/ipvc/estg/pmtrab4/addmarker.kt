package ipvc.estg.pmtrab4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import ipvc.estg.pmtrab4.Login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class addmarker : AppCompatActivity() {

    private lateinit var createmarkerView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmarker)
        var tipo = String();
        val sharedPref: SharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        var username:String? = sharedPref.getString("automatic_login_username", null)
        var foto="/URL/HELLO"
        val lat= intent.getStringExtra(EXTRA_LAT).toString()
        val lon= intent.getStringExtra(EXTRA_LON).toString()
        createmarkerView = findViewById(R.id.marker_texto)
        val texto = createmarkerView.text
        val types = resources.getStringArray(R.array.Types)

        val spinner = findViewById<Spinner>(R.id.spinner1)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, types)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    tipo=types[position]
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }
                val button = findViewById<Button>(R.id.button_save)
                button.setOnClickListener {
            val request = ServiceBuilder.buildService(LoginEndPoints::class.java)
            val call = request.create(
                username.toString(),
                tipo,
                texto,
                lat,
                lon,
                foto

        )
        call.enqueue(object : Callback<TicketOutputPost> {
            override fun onResponse(call: Call<TicketOutputPost>, response: Response<TicketOutputPost>)
            {
                if (response.isSuccessful) {
                    val c: TicketOutputPost = response.body()!!
                    if (c.success) {
                        Toast.makeText(this@addmarker,R.string.markercorrectlabel,Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@addmarker, MapActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else Toast.makeText(this@addmarker,R.string.markerincorrectlabel,Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<TicketOutputPost>, t: Throwable) {
                Toast.makeText(this@addmarker, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
        finish()

    }

    }
    companion object {
        const val EXTRA_LAT = "com.example.android.wordlistsql.LAT"
        const val EXTRA_LON = "com.example.android.wordlistsql.LON"
    }
}