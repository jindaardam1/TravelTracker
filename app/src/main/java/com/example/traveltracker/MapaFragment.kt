package com.example.traveltracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
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

        // Inicializar RecyclerView
        recyclerView = view.findViewById(R.id.paco)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Configurar datos para el RecyclerView
        countries = listOf(
            Country("Afganistán ", "\uD83C\uDDE6\uD83C\uDDEB"),
            Country("Albania ", "\uD83C\uDDE6\uD83C\uDDF1"),
            Country("Argelia ", "\uD83C\uDDE9\uD83C\uDDFF"),
            Country("Andorra ", "\uD83C\uDDE6\uD83C\uDDE9"),
            Country("Angola ", "\uD83C\uDDE6\uD83C\uDDF4"),
            Country("Antigua y Barbuda ", "\uD83C\uDDE6\uD83C\uDDEC"),
            Country("Argentina ", "\uD83C\uDDE6\uD83C\uDDF7"),
            Country("Armenia ", "\uD83C\uDDE6\uD83C\uDDF2"),
            Country("Australia ", "\uD83C\uDDE6\uD83C\uDDFA"),
            Country("Austria ", "\uD83C\uDDE6\uD83C\uDDF9"),
            Country("Bahamas ", "\uD83C\uDDE7\uD83C\uDDF8"),
            Country("Baréin ", "\uD83C\uDDE7\uD83C\uDDEA"),
            Country("Bangladés ", "\uD83C\uDDE7\uD83C\uDDE9"),
            Country("Barbados ", "\uD83C\uDDE7\uD83C\uDDE7"),
            Country("Bielorrusia ", "\uD83C\uDDE7\uD83C\uDDFE"),
            Country("Bélgica ", "\uD83C\uDDE7\uD83C\uDDED"),
            Country("Belice ", "\uD83C\uDDE7\uD83C\uDDFF"),
            Country("Benín ", "\uD83C\uDDE7\uD83C\uDDEF"),
            Country("Bután ", "\uD83C\uDDE7\uD83C\uDDF9"),
            Country("Bolivia ", "\uD83C\uDDE7\uD83C\uDDF4"),
            Country("Bosnia y Herzegovina ", "\uD83C\uDDE7\uD83C\uDDEE"),
            Country("Botsuana ", "\uD83C\uDDE7\uD83C\uDDFC"),
            Country("Brasil ", "\uD83C\uDDE7\uD83C\uDDF7"),
            Country("Brunéi ", "\uD83C\uDDE7\uD83C\uDDF3"),
            Country("Bulgaria ", "\uD83C\uDDE7\uD83C\uDDEC"),
            Country("Burkina Faso ", "\uD83C\uDDE7\uD83C\uDDEB"),
            Country("Burundi ", "\uD83C\uDDE7\uD83C\uDDEC"),
            Country("Cabo Verde ", "\uD83C\uDDE8\uD83C\uDDFB"),
            Country("Camboya ", "\uD83C\uDDF0\uD83C\uDDED"),
            Country("Camerún ", "\uD83C\uDDE8\uD83C\uDDF2"),
            Country("Chad ", "\uD83C\uDDE8\uD83C\uDDEC"),
            Country("Chile ", "\uD83C\uDDE8\uD83C\uDDF1"),
            Country("China ", "\uD83C\uDDE8\uD83C\uDDF3"),
            Country("Colombia ", "\uD83C\uDDE8\uD83C\uDDF4"),
            Country("Comoras ", "\uD83C\uDDE8\uD83C\uDDF2"),
            Country("Congo ", "\uD83C\uDDE8\uD83C\uDDEE"),
            Country("Costa Rica ", "\uD83C\uDDE8\uD83C\uDDF7"),
            Country("Croacia ", "\uD83C\uDDE8\uD83C\uDDF7"),
            Country("Cuba ", "\uD83C\uDDE8\uD83C\uDDFA"),
            Country("Chipre ", "\uD83C\uDDE8\uD83C\uDDFE"),
            Country("República Checa ", "\uD83C\uDDE8\uD83C\uDDEC"),
            Country("Dinamarca ", "\uD83C\uDDE9\uD83C\uDDF0"),
            Country("Yibuti ", "\uD83C\uDDE9\uD83C\uDDEE"),
            Country("Dominica ", "\uD83C\uDDE9\uD83C\uDDF2"),
            Country("República Dominicana ", "\uD83C\uDDE9\uD83C\uDDF4"),
            Country("Ecuador ", "\uD83C\uDDEA\uD83C\uDDE8"),
            Country("Egipto ", "\uD83C\uDDEA\uD83C\uDDEC"),
            Country("El Salvador ", "\uD83C\uDDEA\uD83C\uDDF8"),
            Country("Guinea Ecuatorial ", "\uD83C\uDDEC\uD83C\uDDEA"),
            Country("Eritrea ", "\uD83C\uDDEA\uD83C\uDDF7"),
            Country("Estonia ", "\uD83C\uDDEA\uD83C\uDDEA"),
            Country("Eswatini ", "\uD83C\uDDEA\uD83C\uDDFC"),
            Country("Etiopía ", "\uD83C\uDDEA\uD83C\uDDF9"),
            Country("Fiyi ", "\uD83C\uDDEB\uD83C\uDDF3"),
            Country("Finlandia ", "\uD83C\uDDEB\uD83C\uDDEE"),
            Country("Gabón ", "\uD83C\uDDEC\uD83C\uDDE6"),
            Country("Gambia ", "\uD83C\uDDEC\uD83C\uDDF2"),
            Country("Georgia ", "\uD83C\uDDEC\uD83C\uDDEA"),
            Country("Ghana ", "\uD83C\uDDEC\uD83C\uDDED"),
            Country("Grecia ", "\uD83C\uDDEC\uD83C\uDDF7"),
            Country("Granada ", "\uD83C\uDDEC\uD83C\uDDF9"),
            Country("Guatemala ", "\uD83C\uDDEC\uD83C\uDDF9"),
            Country("Guinea ", "\uD83C\uDDEC\uD83C\uDDF3"),
            Country("Guinea-Bisáu ", "\uD83C\uDDEC\uD83C\uDDEE"),
            Country("Guyana ", "\uD83C\uDDEC\uD83C\uDDFE"),
            Country("Haití ", "\uD83C\uDDED\uD83C\uDDF9"),
            Country("Honduras ", "\uD83C\uDDED\uD83C\uDDF3"),
            Country("Hungría ", "\uD83C\uDDED\uD83C\uDDFA"),
            Country("Islandia ", "\uD83C\uDDEE\uD83C\uDDF8"),
            Country("India ", "\uD83C\uDDEE\uD83C\uDDF3"),
            Country("Indonesia ", "\uD83C\uDDEE\uD83C\uDDE9"),
            Country("Irán ", "\uD83C\uDDEE\uD83C\uDDF7"),
            Country("Iraq ", "\uD83C\uDDEE\uD83C\uDDF6"),
            Country("Irlanda ", "\uD83C\uDDEE\uD83C\uDDEA"),
            Country("Israel ", "\uD83C\uDDEE\uD83C\uDDF1"),
            Country("Costa de Marfil ", "\uD83C\uDDEE\uD83C\uDDE9"),
            Country("Jamaica ", "\uD83C\uDDEF\uD83C\uDDF2"),
            Country("Japón ", "\uD83C\uDDEF\uD83C\uDDF5"),
            Country("Jordania ", "\uD83C\uDDEF\uD83C\uDDF5"),
            Country("Kazajistán ", "\uD83C\uDDF0\uD83C\uDDFF"),
            Country("Kenia ", "\uD83C\uDDF0\uD83C\uDDEA"),
            Country("Kiribati ", "\uD83C\uDDF0\uD83C\uDDEE"),
            Country("Kuwait ", "\uD83C\uDDF0\uD83C\uDDFC"),
            Country("Kirguistán ", "\uD83C\uDDF0\uD83C\uDDEC"),
            Country("Laos ", "\uD83C\uDDF1\uD83C\uDDE6"),
            Country("Letonia ", "\uD83C\uDDF1\uD83C\uDDED"),
            Country("Líbano ", "\uD83C\uDDF1\uD83C\uDDE7"),
            Country("Lesoto ", "\uD83C\uDDF1\uD83C\uDDE7"),
            Country("Liberia ", "\uD83C\uDDF1\uD83C\uDDE7"),
            Country("Libia ", "\uD83C\uDDF1\uD83C\uDDEE"),
            Country("Liechtenstein ", "\uD83C\uDDF1\uD83C\uDDEE"),
            Country("Lituania ", "\uD83C\uDDF1\uD83C\uDDF9"),
            Country("Luxemburgo ", "\uD83C\uDDF1\uD83C\uDDF8"),
            Country("Macedonia del Norte ", "\uD83C\uDDF2\uD83C\uDDE6"),
            Country("Madagascar ", "\uD83C\uDDF2\uD83C\uDDEC"),
            Country("Malaui ", "\uD83C\uDDF2\uD83C\uDDFC"),
            Country("Malasia ", "\uD83C\uDDF2\uD83C\uDDFE"),
            Country("Maldivas ", "\uD83C\uDDF2\uD83C\uDDFB"),
            Country("Malí ", "\uD83C\uDDF2\uD83C\uDDF1"),
            Country("Malta ", "\uD83C\uDDF2\uD83C\uDDF9"),
            Country("Islas Marshall ", "\uD83C\uDDF2\uD83C\uDDF5"),
            Country("Mauritania ", "\uD83C\uDDF2\uD83C\uDDF7"),
            Country("México ", "\uD83C\uDDF2\uD83C\uDDFD"),
            Country("Micronesia ", "\uD83C\uDDEB\uD83C\uDDEE"),
            Country("Moldavia ", "\uD83C\uDDF2\uD83C\uDDE9"),
            Country("Mónaco ", "\uD83C\uDDF2\uD83C\uDDE8"),
            Country("Mongolia ", "\uD83C\uDDF2\uD83C\uDDF3"),
            Country("Montenegro ", "\uD83C\uDDF2\uD83C\uDDEA"),
            Country("Marruecos ", "\uD83C\uDDF2\uD83C\uDDEC"),
            Country("Mozambique ", "\uD83C\uDDF2\uD83C\uDDFF"),
            Country("Myanmar ", "\uD83C\uDDF2\uD83C\uDDF2"),
            Country("Namibia ", "\uD83C\uDDF3\uD83C\uDDE6"),
            Country("Nauru ", "\uD83C\uDDF3\uD83C\uDDF7"),
            Country("Nepal ", "\uD83C\uDDF3\uD83C\uDDF5"),
            Country("Países Bajos ", "\uD83C\uDDF3\uD83C\uDDF1"),
            Country("Nueva Zelanda ", "\uD83C\uDDF3\uD83C\uDDFF"),
            Country("Nicaragua ", "\uD83C\uDDEB\uD83C\uDDF2"),
            Country("Níger ", "\uD83C\uDDF3\uD83C\uDDEC"),
            Country("Nigeria ", "\uD83C\uDDF3\uD83C\uDDEC"),
            Country("Noruega ", "\uD83C\uDDF3\uD83C\uDDF4"),
            Country("Omán ", "\uD83C\uDDF4\uD83C\uDDF2"),
            Country("Pakistán ", "\uD83C\uDDF5\uD83C\uDDE6"),
            Country("Palaos ", "\uD83C\uDDF5\uD83C\uDDE6"),
            Country("Panamá ", "\uD83C\uDDF5\uD83C\uDDE6"),
            Country("Papúa Nueva Guinea ", "\uD83C\uDDF5\uD83C\uDDEC"),
            Country("Paraguay ", "\uD83C\uDDF5\uD83C\uDDFE"),
            Country("Perú ", "\uD83C\uDDF5\uD83C\uDDEA"),
            Country("Filipinas ", "\uD83C\uDDF5\uD83C\uDDED"),
            Country("Polonia ", "\uD83C\uDDF5\uD83C\uDDF1"),
            Country("Portugal ", "\uD83C\uDDF5\uD83C\uDDF9"),
            Country("Catar ", "\uD83C\uDDF6\uD83C\uDDE6"),
            Country("Rumania ", "\uD83C\uDDF7\uD83C\uDDF4"),
            Country("Rusia ", "\uD83C\uDDF7\uD83C\uDDFA"),
            Country("Ruanda ", "\uD83C\uDDF7\uD83C\uDDFC"),
            Country("San Cristóbal y Nieves ", "\uD83C\uDDF0\uD83C\uDDF3"),
            Country("Santa Lucía ", "\uD83C\uDDF1\uD83C\uDDE8"),
            Country("Alemania ", "\uD83C\uDDE9\uD83C\uDDEA"),
            Country("Arabia Saudita ", "\uD83C\uDDF8\uD83C\uDDE6"),
            Country("Bielorrusia ", "\uD83C\uDDE7\uD83C\uDDFE"),
            Country("Birmania ", "\uD83C\uDDF2\uD83C\uDDF2"),
            Country("Canadá ", "\uD83C\uDDE8\uD83C\uDDE6"),
            Country("Ciudad del Vaticano ", "\uD83C\uDDFB\uD83C\uDDE6"),
            Country("Corea del Norte ", "\uD83C\uDDF0\uD83C\uDDF5"),
            Country("Corea del Sur ", "\uD83C\uDDF0\uD83C\uDDF7"),
            Country("Emiratos Árabes Unidos ", "\uD83C\uDDE6\uD83C\uDDEA"),
            Country("Eslovaquia ", "\uD83C\uDDF8\uD83C\uDDF0"),
            Country("Eslovenia ", "\uD83C\uDDF8\uD83C\uDDEE"),
            Country("Estados Unidos", "\uD83C\uDDFA\uD83C\uDDF8"),
            Country("Francia ", "\uD83C\uDDEB\uD83C\uDDF7"),
            Country("Irak ", "\uD83C\uDDEE\uD83C\uDDF6"),
            Country("Italia ", "\uD83C\uDDEE\uD83C\uDDF9"),
            Country("Reino Unido ", "\uD83C\uDDEC\uD83C\uDDE7"),
            Country("República del Congo ", "\uD83C\uDDE8\uD83C\uDDE9"),
            Country("República Democrática del Congo ", "\uD83C\uDDE8\uD83C\uDDE9"),
            Country("República Sudafricana ", "\uD83C\uDDFF\uD83C\uDDE6"),
            Country("Samoa ", "\uD83C\uDDFC\uD83C\uDDF8"),
            Country("San Marino ", "\uD83C\uDDF8\uD83C\uDDF2"),
            Country("San Vicente y las Granadinas ", "\uD83C\uDDFB\uD83C\uDDE8"),
            Country("Santo Tomé y Príncipe ", "\uD83C\uDDF8\uD83C\uDDF9"),
            Country("Serbia ", "\uD83C\uDDF7\uD83C\uDDF8"),
            Country("Seychelles ", "\uD83C\uDDF8\uD83C\uDDE8"),
            Country("Singapur ", "\uD83C\uDDF8\uD83C\uDDEC"),
            Country("Somalia ", "\uD83C\uDDF8\uD83C\uDDF4"),
            Country("Sri Lanka ", "\uD83C\uDDF1\uD83C\uDDF0"),
            Country("Suazilandia ", "\uD83C\uDDF8\uD83C\uDDFF"),
            Country("Sudán ", "\uD83C\uDDF8\uD83C\uDDE9"),
            Country("Sudán del Sur ", "\uD83C\uDDF8\uD83C\uDDF8"),
            Country("Suecia ", "\uD83C\uDDF8\uD83C\uDDEA"),
            Country("Suiza ", "\uD83C\uDDE8\uD83C\uDDED"),
            Country("Surinam ", "\uD83C\uDDF8\uD83C\uDDF7"),
            Country("Tailandia ", "\uD83C\uDDF9\uD83C\uDDED"),
            Country("Tanzania ", "\uD83C\uDDF9\uD83C\uDDFF"),
            Country("Timor Oriental ", "\uD83C\uDDF9\uD83C\uDDF4"),
            Country("Togo ", "\uD83C\uDDF9\uD83C\uDDEC"),
            Country("Tonga ", "\uD83C\uDDF9\uD83C\uDDF4"),
            Country("Trinidad y Tobago ", "\uD83C\uDDF9\uD83C\uDDF9"),
            Country("Turkmenistán ", "\uD83C\uDDF9\uD83C\uDDF2"),
            Country("Tuvalu ", "\uD83C\uDDF9\uD83C\uDDFB"),
            Country("Ucrania ", "\uD83C\uDDFA\uD83C\uDDE6"),
            Country("Uganda ", "\uD83C\uDDFA\uD83C\uDDEC"),
            Country("Uruguay ", "\uD83C\uDDFA\uD83C\uDDFE"),
            Country("Uzbekistán ", "\uD83C\uDDFA\uD83C\uDDFF"),
            Country("Vanuatu ", "\uD83C\uDDFB\uD83C\uDDFA"),
            Country("Venezuela ", "\uD83C\uDDFB\uD83C\uDDEA"),
            Country("Vietnam ", "\uD83C\uDDFB\uD83C\uDDF3"),
            Country("Yemen ", "\uD83C\uDDFE\uD83C\uDDEA"),
            Country("Zambia ", "\uD83C\uDDFF\uD83C\uDDF2"),
            Country("Zimbabue ", "\uD83C\uDDFF\uD83C\uDDFC"),
            Country("Kosovo ", "\uD83C\uDDFD\uD83C\uDDF0"),
            Country("Taiwán ", "\uD83C\uDDF9\uD83C\uDDFC"),
            Country("Sahara Occidental ", "\uD83C\uDDEA\uD83C\uDDF8"),
            Country("Santa Sede ", "\uD83C\uDDFB\uD83C\uDDE6"),
            Country("Palestina ", "\uD83C\uDDF5\uD83C\uDDF8"),
            Country("España ", "\uD83C\uDDEA\uD83C\uDDF8"),
            // Agregar más países según sea necesario
        )

        // Inicializar adaptador y asignarlo al RecyclerView
        adapter = CountryAdapter(countries)
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
