package ipvc.estg.pmtrab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ipvc.estg.pmtrab4.Login.LoginEndPoints
import ipvc.estg.pmtrab4.Login.ServiceBuilder
import ipvc.estg.pmtrab4.dataclasse.Nota
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var edittituloView: EditText
private lateinit var editlatView: EditText
private lateinit var editlonView: EditText
private lateinit var edittipoView: EditText

class ticket : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)




        edittituloView = findViewById(R.id.edit_ticket)
        editlatView = findViewById(R.id.edit_lat)
        editlonView = findViewById(R.id.edit_lon)
        edittipoView = findViewById(R.id.edit_tipo)
        val intent = intent
        val id = intent.getStringExtra(ticket.EXTRA_MSG)
        var lat = intent.getStringExtra(ticket.EXTRA_LAT)
        var lon = intent.getStringExtra(ticket.EXTRA_LON)
        var call_id= id?.toInt()
        val request = ServiceBuilder.buildService(LoginEndPoints::class.java)
        val call = request.getNota(call_id!!)
        call.enqueue(object : Callback<List<Nota>> {
            override fun onResponse(call: Call<List<Nota>>, response: Response<List<Nota>>) {
                if (response.isSuccessful) {
                    val c = response.body()!!

                    for (note in c) {

                        edittituloView.setText(note.texto)
                        editlatView.setText(note.lat)
                        editlonView.setText(note.lon)
                        edittipoView.setText(note.username)
                    }
                }
            }

            override fun onFailure(call: Call<List<Nota>>, t: Throwable) {
                Toast.makeText(this@ticket, "Falhou", Toast.LENGTH_SHORT).show()
            }
        })


    }


    companion object {
        const val EXTRA_MSG = "com.example.android.wordlistsql.MSG"
        const val EXTRA_LAT = "com.example.android.wordlistsql.LAT"
        const val EXTRA_LON = "com.example.android.wordlistsql.LON"
    }
}