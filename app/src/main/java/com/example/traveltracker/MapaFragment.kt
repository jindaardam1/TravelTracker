package com.example.traveltracker

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MapaFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter
    private lateinit var countries: List<Country>
    private lateinit var searchView: SearchView
    private var selectedCountryId: String? = null
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mapa, container, false)

        button = view.findViewById(R.id.button5)
        button.setOnClickListener {
            cambiarColorElementoSVG()
        }
        // Inicializar RecyclerView
        recyclerView = view.findViewById(R.id.paco)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Configurar datos para el RecyclerView
        countries = listOf(
            Country("Afganistán ", "\uD83C\uDDE6\uD83C\uDDEB","AF"),
            Country("Albania ", "\uD83C\uDDE6\uD83C\uDDF1", "AL"),
            Country("Argelia ", "\uD83C\uDDE9\uD83C\uDDFF", "DZ"),
            Country("Andorra ", "\uD83C\uDDE6\uD83C\uDDE9", "AD"),
            Country("Angola ", "\uD83C\uDDE6\uD83C\uDDF4", "AO"),
            Country("Antigua y Barbuda ", "\uD83C\uDDE6\uD83C\uDDEC","AG"),
            Country("Argentina ", "\uD83C\uDDE6\uD83C\uDDF7", "AR"),
            Country("Armenia ", "\uD83C\uDDE6\uD83C\uDDF2", "AM"),
            Country("Australia ", "\uD83C\uDDE6\uD83C\uDDFA", "AU"),
            Country("Austria ", "\uD83C\uDDE6\uD83C\uDDF9", "AT"),
            Country("Bahamas ", "\uD83C\uDDE7\uD83C\uDDF8", "BS"),
            Country("Baréin ", "\uD83C\uDDE7\uD83C\uDDEA", "BH"),
            Country("Bangladés ", "\uD83C\uDDE7\uD83C\uDDE9", "BD"),
            Country("Barbados ", "\uD83C\uDDE7\uD83C\uDDE7", "BB"),
            Country("Bielorrusia ", "\uD83C\uDDE7\uD83C\uDDFE", "BY"),
            Country("Bélgica ", "\uD83C\uDDE7\uD83C\uDDED", "BE"),
            Country("Belice ", "\uD83C\uDDE7\uD83C\uDDFF", "BZ"),
            Country("Benín ", "\uD83C\uDDE7\uD83C\uDDEF", "BJ"),
            Country("Bután ", "\uD83C\uDDE7\uD83C\uDDF9", "BT"),
            Country("Bolivia ", "\uD83C\uDDE7\uD83C\uDDF4", "BO"),
            Country("Bosnia y Herzegovina ", "\uD83C\uDDE7\uD83C\uDDEE", "BA"),
            Country("Botsuana ", "\uD83C\uDDE7\uD83C\uDDFC", "BW"),
            Country("Brasil ", "\uD83C\uDDE7\uD83C\uDDF7", "BR"),
            Country("Brunéi ", "\uD83C\uDDE7\uD83C\uDDF3", "BN"),
            Country("Bulgaria ", "\uD83C\uDDE7\uD83C\uDDEC", "BG"),
            Country("Burkina Faso ", "\uD83C\uDDE7\uD83C\uDDEB", "BF"),
            Country("Burundi ", "\uD83C\uDDE7\uD83C\uDDEC", "BI"),
            Country("Cabo Verde ", "\uD83C\uDDE8\uD83C\uDDFB", "CV"),
            Country("Camboya ", "\uD83C\uDDF0\uD83C\uDDED", "KH"),
            Country("Camerún ", "\uD83C\uDDE8\uD83C\uDDF2", "CM"),
            Country("Chad ", "\uD83C\uDDE8\uD83C\uDDEC", "TD"),
            Country("Chile ", "\uD83C\uDDE8\uD83C\uDDF1", "CL"),
            Country("China ", "\uD83C\uDDE8\uD83C\uDDF3", "CN"),
            Country("Colombia ", "\uD83C\uDDE8\uD83C\uDDF4", "CO"),
            Country("Comoras ", "\uD83C\uDDE8\uD83C\uDDF2", "KM"),
            Country("Congo ", "\uD83C\uDDE8\uD83C\uDDEE", "CG"),
            Country("Costa Rica ", "\uD83C\uDDE8\uD83C\uDDF7", "CR"),
            Country("Croacia ", "\uD83C\uDDE8\uD83C\uDDF7", "HR"),
            Country("Cuba ", "\uD83C\uDDE8\uD83C\uDDFA", "Cu"),
            Country("Chipre ", "\uD83C\uDDE8\uD83C\uDDFE", "CY"),
            Country("República Checa ", "\uD83C\uDDE8\uD83C\uDDEC", "CZ"),
            Country("Dinamarca ", "\uD83C\uDDE9\uD83C\uDDF0", "DK"),
            Country("Yibuti ", "\uD83C\uDDE9\uD83C\uDDEE", "DJ"),
            Country("Dominica ", "\uD83C\uDDE9\uD83C\uDDF2", "DM"),
            Country("República Dominicana ", "\uD83C\uDDE9\uD83C\uDDF4", "DO"),
            Country("Ecuador ", "\uD83C\uDDEA\uD83C\uDDE8", "EC"),
            Country("Egipto ", "\uD83C\uDDEA\uD83C\uDDEC", "EG"),
            Country("El Salvador ", "\uD83C\uDDEA\uD83C\uDDF8", "SV"),
            Country("Guinea Ecuatorial ", "\uD83C\uDDEC\uD83C\uDDEA", "GQ"),
            Country("Eritrea ", "\uD83C\uDDEA\uD83C\uDDF7", "ER"),
            Country("Estonia ", "\uD83C\uDDEA\uD83C\uDDEA", "EE"),
            Country("Eswatini ", "\uD83C\uDDEA\uD83C\uDDFC", "SZ"),
            Country("Etiopía ", "\uD83C\uDDEA\uD83C\uDDF9", "ET"),
            Country("Fiyi ", "\uD83C\uDDEB\uD83C\uDDF3", "FJ"),
            Country("Finlandia ", "\uD83C\uDDEB\uD83C\uDDEE", "FI"),
            Country("Gabón ", "\uD83C\uDDEC\uD83C\uDDE6", "GA"),
            Country("Gambia ", "\uD83C\uDDEC\uD83C\uDDF2", "GM"),
            Country("Georgia ", "\uD83C\uDDEC\uD83C\uDDEA", "GE"),
            Country("Ghana ", "\uD83C\uDDEC\uD83C\uDDED", "GH"),
            Country("Grecia ", "\uD83C\uDDEC\uD83C\uDDF7", "GR"),
            Country("Granada ", "\uD83C\uDDEC\uD83C\uDDF9", "GD"),
            Country("Guatemala ", "\uD83C\uDDEC\uD83C\uDDF9", "GT"),
            Country("Guinea ", "\uD83C\uDDEC\uD83C\uDDF3", "GN"),
            Country("Guinea-Bisáu ", "\uD83C\uDDEC\uD83C\uDDEE", "GW"),
            Country("Guyana ", "\uD83C\uDDEC\uD83C\uDDFE", "GY"),
            Country("Haití ", "\uD83C\uDDED\uD83C\uDDF9", "HT"),
            Country("Honduras ", "\uD83C\uDDED\uD83C\uDDF3", "HN"),
            Country("Hungría ", "\uD83C\uDDED\uD83C\uDDFA", "HU"),
            Country("Islandia ", "\uD83C\uDDEE\uD83C\uDDF8", "IS"),
            Country("India ", "\uD83C\uDDEE\uD83C\uDDF3", "IN"),
            Country("Indonesia ", "\uD83C\uDDEE\uD83C\uDDE9", "ID"),
            Country("Irán ", "\uD83C\uDDEE\uD83C\uDDF7", "IR"),
            Country("Iraq ", "\uD83C\uDDEE\uD83C\uDDF6", "IQ"),
            Country("Irlanda ", "\uD83C\uDDEE\uD83C\uDDEA", "IE"),
            Country("Israel ", "\uD83C\uDDEE\uD83C\uDDF1", "IL"),
            Country("Costa de Marfil ", "\uD83C\uDDEE\uD83C\uDDE9", "CI"),
            Country("Jamaica ", "\uD83C\uDDEF\uD83C\uDDF2", "JM"),
            Country("Japón ", "\uD83C\uDDEF\uD83C\uDDF5", "JP"),
            Country("Jordania ", "\uD83C\uDDEF\uD83C\uDDF5", "JO"),
            Country("Kazajistán ", "\uD83C\uDDF0\uD83C\uDDFF", "KZ"),
            Country("Kenia ", "\uD83C\uDDF0\uD83C\uDDEA", "KE"),
            Country("Kiribati ", "\uD83C\uDDF0\uD83C\uDDEE", "KI"),
            Country("Kuwait ", "\uD83C\uDDF0\uD83C\uDDFC", "KW"),
            Country("Kirguistán ", "\uD83C\uDDF0\uD83C\uDDEC", "KG"),
            Country("Laos ", "\uD83C\uDDF1\uD83C\uDDE6", "LA"),
            Country("Letonia ", "\uD83C\uDDF1\uD83C\uDDED", "LV"),
            Country("Líbano ", "\uD83C\uDDF1\uD83C\uDDE7", "LB"),
            Country("Lesoto ", "\uD83C\uDDF1\uD83C\uDDE7", "LS"),
            Country("Liberia ", "\uD83C\uDDF1\uD83C\uDDE7", "LR"),
            Country("Libia ", "\uD83C\uDDF1\uD83C\uDDEE", "LY"),
            Country("Liechtenstein ", "\uD83C\uDDF1\uD83C\uDDEE", "LI"),
            Country("Lituania ", "\uD83C\uDDF1\uD83C\uDDF9", "LT"),
            Country("Luxemburgo ", "\uD83C\uDDF1\uD83C\uDDF8", "LU"),
            Country("Macedonia del Norte ", "\uD83C\uDDF2\uD83C\uDDE6", "MK"),
            Country("Madagascar ", "\uD83C\uDDF2\uD83C\uDDEC", "MG"),
            Country("Malaui ", "\uD83C\uDDF2\uD83C\uDDFC", "MW"),
            Country("Malasia ", "\uD83C\uDDF2\uD83C\uDDFE", "MY"),
            Country("Maldivas ", "\uD83C\uDDF2\uD83C\uDDFB", "MV"),
            Country("Malí ", "\uD83C\uDDF2\uD83C\uDDF1", "ML"),
            Country("Malta ", "\uD83C\uDDF2\uD83C\uDDF9", "MT"),
            Country("Islas Marshall ", "\uD83C\uDDF2\uD83C\uDDF5", "MH"),
            Country("Mauritania ", "\uD83C\uDDF2\uD83C\uDDF7", "MR"),
            Country("México ", "\uD83C\uDDF2\uD83C\uDDFD", "MX"),
            Country("Micronesia ", "\uD83C\uDDEB\uD83C\uDDEE", "FM"),
            Country("Moldavia ", "\uD83C\uDDF2\uD83C\uDDE9", "MD"),
            Country("Mónaco ", "\uD83C\uDDF2\uD83C\uDDE8","MC"),
            Country("Mongolia ", "\uD83C\uDDF2\uD83C\uDDF3", "MN"),
            Country("Montenegro ", "\uD83C\uDDF2\uD83C\uDDEA" , "ME"),
            Country("Marruecos ", "\uD83C\uDDF2\uD83C\uDDEC" , "MA"),
            Country("Mozambique ", "\uD83C\uDDF2\uD83C\uDDFF" , "MZ"),
            Country("Myanmar ", "\uD83C\uDDF2\uD83C\uDDF2", "MM"),
            Country("Namibia ", "\uD83C\uDDF3\uD83C\uDDE6", "NA"),
            Country("Nauru ", "\uD83C\uDDF3\uD83C\uDDF7", "NR"),
            Country("Nepal ", "\uD83C\uDDF3\uD83C\uDDF5", "NP"),
            Country("Países Bajos ", "\uD83C\uDDF3\uD83C\uDDF1", "NL"),
            Country("Nueva Zelanda ", "\uD83C\uDDF3\uD83C\uDDFF", "NZ"),
            Country("Nicaragua ", "\uD83C\uDDEB\uD83C\uDDF2", "NI"),
            Country("Níger ", "\uD83C\uDDF3\uD83C\uDDEC", "NE"),
            Country("Nigeria ", "\uD83C\uDDF3\uD83C\uDDEC", "NG"),
            Country("Noruega ", "\uD83C\uDDF3\uD83C\uDDF4", "NO"),
            Country("Omán ", "\uD83C\uDDF4\uD83C\uDDF2", "OM"),
            Country("Pakistán ", "\uD83C\uDDF5\uD83C\uDDE6", "PK"),
            Country("Palaos ", "\uD83C\uDDF5\uD83C\uDDE6", "PW"),
            Country("Panamá ", "\uD83C\uDDF5\uD83C\uDDE6", "PA"),
            Country("Papúa Nueva Guinea ", "\uD83C\uDDF5\uD83C\uDDEC","PG"),
            Country("Paraguay ", "\uD83C\uDDF5\uD83C\uDDFE", "PY"),
            Country("Perú ", "\uD83C\uDDF5\uD83C\uDDEA", "PE"),
            Country("Filipinas ", "\uD83C\uDDF5\uD83C\uDDED", "PH"),
            Country("Polonia ", "\uD83C\uDDF5\uD83C\uDDF1", "PL"),
            Country("Portugal ", "\uD83C\uDDF5\uD83C\uDDF9", "PT"),
            Country("Catar ", "\uD83C\uDDF6\uD83C\uDDE6", "QA"),
            Country("Rumania ", "\uD83C\uDDF7\uD83C\uDDF4", "RO"),
            Country("Rusia ", "\uD83C\uDDF7\uD83C\uDDFA", "RU"),
            Country("Ruanda ", "\uD83C\uDDF7\uD83C\uDDFC", "RW"),
            Country("San Cristóbal y Nieves ", "\uD83C\uDDF0\uD83C\uDDF3", "KN"),
            Country("Santa Lucía ", "\uD83C\uDDF1\uD83C\uDDE8", "LC"),
            Country("Alemania ", "\uD83C\uDDE9\uD83C\uDDEA", "DE"),
            Country("Arabia Saudita ", "\uD83C\uDDF8\uD83C\uDDE6", "SA"),
            Country("Bielorrusia ", "\uD83C\uDDE7\uD83C\uDDFE", "BY"),
            Country("Birmania ", "\uD83C\uDDF2\uD83C\uDDF2" , "MM"),
            Country("Canadá ", "\uD83C\uDDE8\uD83C\uDDE6", "CA"),
            Country("Ciudad del Vaticano ", "\uD83C\uDDFB\uD83C\uDDE6", "VA"),
            Country("Corea del Norte ", "\uD83C\uDDF0\uD83C\uDDF5", "KP"),
            Country("Corea del Sur ", "\uD83C\uDDF0\uD83C\uDDF7", "KR"),
            Country("Emiratos Árabes Unidos ", "\uD83C\uDDE6\uD83C\uDDEA", "AE"),
            Country("Eslovaquia ", "\uD83C\uDDF8\uD83C\uDDF0", "SK"),
            Country("Eslovenia ", "\uD83C\uDDF8\uD83C\uDDEE", "SI"),
            Country("Estados Unidos", "\uD83C\uDDFA\uD83C\uDDF8", "US"),
            Country("Francia ", "\uD83C\uDDEB\uD83C\uDDF7", "FR"),
            Country("Irak ", "\uD83C\uDDEE\uD83C\uDDF6", "IQ"),
            Country("Italia ", "\uD83C\uDDEE\uD83C\uDDF9", "IT"),
            Country("Reino Unido ", "\uD83C\uDDEC\uD83C\uDDE7", "GB"),
            Country("República del Congo ", "\uD83C\uDDE8\uD83C\uDDE9", "CG"),
            Country("República Democrática del Congo ", "\uD83C\uDDE8\uD83C\uDDE9", "CD"),
            Country("República Sudafricana ", "\uD83C\uDDFF\uD83C\uDDE6", "ZA"),
            Country("Samoa ", "\uD83C\uDDFC\uD83C\uDDF8", "WS"),
            Country("San Marino ", "\uD83C\uDDF8\uD83C\uDDF2", "SM"),
            Country("San Vicente y las Granadinas ", "\uD83C\uDDFB\uD83C\uDDE8", "VC"),
            Country("Santo Tomé y Príncipe ", "\uD83C\uDDF8\uD83C\uDDF9", "ST"),
            Country("Serbia ", "\uD83C\uDDF7\uD83C\uDDF8", "SR"),
            Country("Seychelles ", "\uD83C\uDDF8\uD83C\uDDE8", "SC"),
            Country("Singapur ", "\uD83C\uDDF8\uD83C\uDDEC", "SG"),
            Country("Somalia ", "\uD83C\uDDF8\uD83C\uDDF4", "SO"),
            Country("Sri Lanka ", "\uD83C\uDDF1\uD83C\uDDF0", "LK"),
            Country("Suazilandia ", "\uD83C\uDDF8\uD83C\uDDFF", "SZ"),
            Country("Sudán ", "\uD83C\uDDF8\uD83C\uDDE9", "SD"),
            Country("Sudán del Sur ", "\uD83C\uDDF8\uD83C\uDDF8", "SS"),
            Country("Suecia ", "\uD83C\uDDF8\uD83C\uDDEA", "SE"),
            Country("Suiza ", "\uD83C\uDDE8\uD83C\uDDED", "CH"),
            Country("Surinam ", "\uD83C\uDDF8\uD83C\uDDF7", "SR"),
            Country("Tailandia ", "\uD83C\uDDF9\uD83C\uDDED", "TH"),
            Country("Tanzania ", "\uD83C\uDDF9\uD83C\uDDFF", "TZ"),
            Country("Timor Oriental ", "\uD83C\uDDF9\uD83C\uDDF4", "TL"),
            Country("Togo ", "\uD83C\uDDF9\uD83C\uDDEC", "TG"),
            Country("Tonga ", "\uD83C\uDDF9\uD83C\uDDF4", "TO"),
            Country("Trinidad y Tobago ", "\uD83C\uDDF9\uD83C\uDDF9", "TT"),
            Country("Turkmenistán ", "\uD83C\uDDF9\uD83C\uDDF2", "TM"),
            Country("Tuvalu ", "\uD83C\uDDF9\uD83C\uDDFB", "TV"),
            Country("Ucrania ", "\uD83C\uDDFA\uD83C\uDDE6", "UA"),
            Country("Uganda ", "\uD83C\uDDFA\uD83C\uDDEC", "UG"),
            Country("Uruguay ", "\uD83C\uDDFA\uD83C\uDDFE", "UY"),
            Country("Uzbekistán ", "\uD83C\uDDFA\uD83C\uDDFF", "UZ"),
            Country("Vanuatu ", "\uD83C\uDDFB\uD83C\uDDFA", "VU"),
            Country("Venezuela ", "\uD83C\uDDFB\uD83C\uDDEA", "VE"),
            Country("Vietnam ", "\uD83C\uDDFB\uD83C\uDDF3", "VN"),
            Country("Yemen ", "\uD83C\uDDFE\uD83C\uDDEA", "YE"),
            Country("Zambia ", "\uD83C\uDDFF\uD83C\uDDF2", "ZM"),
            Country("Zimbabue ", "\uD83C\uDDFF\uD83C\uDDFC", "ZW"),
            Country("Kosovo ", "\uD83C\uDDFD\uD83C\uDDF0", "XK"),
            Country("Taiwán ", "\uD83C\uDDF9\uD83C\uDDFC", "TW"),
            Country("Sahara Occidental ", "\uD83C\uDDEA\uD83C\uDDF8", "EH"),
            Country("Santa Sede ", "\uD83C\uDDFB\uD83C\uDDE6", "VA"),
            Country("Palestina ", "\uD83C\uDDF5\uD83C\uDDF8", "PS"),
            Country("España ", "\uD83C\uDDEA\uD83C\uDDF8", "ES"),
            // Agregar más países según sea necesario
        )

        // Inicializar adaptador y asignarlo al RecyclerView
        adapter = CountryAdapter(countries){ countryId ->
            // Aquí puedes hacer lo que quieras con el ID del país seleccionado
            selectedCountryId = countryId
            Log.d("MapaFragment", "Selected Country ID: $selectedCountryId") // Registro en Logcat
        }
        recyclerView.adapter = adapter

        // Inicializar SearchView
        searchView = view.findViewById(R.id.searchView)

        // Configurar el Listener para el SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Acción a realizar cuando se envía una consulta de búsqueda (si es necesario)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Filtrar los datos del RecyclerView según el texto de búsqueda
                adapter.filter.filter(newText)

                // Hacer visible/invisible el RecyclerView según si hay texto en el SearchView
                recyclerView.visibility = if (newText.isNullOrEmpty()) View.GONE else View.VISIBLE

                return false
            }

        })

        return view
    }
    private fun cambiarColorElementoSVG() {
        
    }





    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
