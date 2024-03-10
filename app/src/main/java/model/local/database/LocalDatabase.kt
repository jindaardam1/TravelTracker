package model.local.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import model.local.dao.EstadoPaisDAO
import model.local.dao.PaisConfirmadoDAO
import model.local.entity.EstadoPais
import model.local.entity.PaisConfirmado

@Database(entities = [EstadoPais::class, PaisConfirmado::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun estadoPaisDao(): EstadoPaisDAO
    abstract fun paisConfirmadoDAO(): PaisConfirmadoDAO

    companion object {
        private const val DB = "TravelTracker.db"
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        DB
                    )
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Log.i("Callback", "Callback llamado")
                                primerasInsercionesNecesarias(context)
                            }
                        })
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

        @OptIn(DelicateCoroutinesApi::class)
        private fun primerasInsercionesNecesarias(context: Context) {

            val codigoPaises = arrayOf(
                "AF", "AX", "AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG",
                "AR", "AM", "AW", "AU", "AT", "AZ", "BS", "BH", "BD", "BB",
                "BY", "BE", "BZ", "BJ", "BM", "BT", "BO", "BA", "BW", "BV",
                "BR", "IO", "BN", "BG", "BF", "BI", "KH", "CM", "CA", "CV",
                "KY", "CF", "TD", "CL", "CN", "CX", "CC", "CO", "KM", "CG",
                "CD", "CK", "CR", "CI", "HR", "CU", "CY", "CZ", "DK", "DJ",
                "DM", "DO", "EC", "EG", "SV", "GQ", "ER", "EE", "ET", "FK",
                "FO", "FJ", "FI", "FR", "GF", "PF", "TF", "GA", "GM", "GE",
                "DE", "GH", "GI", "GR", "GL", "GD", "GP", "GU", "GT", "GG",
                "GN", "GW", "GY", "HT", "HM", "VA", "HN", "HK", "HU", "IS",
                "IN", "ID", "IR", "IQ", "IE", "IM", "IL", "IT", "JM", "JP",
                "JE", "JO", "KZ", "KE", "KI", "KP", "KR", "KW", "KG", "LA",
                "LV", "LB", "LS", "LR", "LY", "LI", "LT", "LU", "MO", "MK",
                "MG", "MW", "MY", "MV", "ML", "MT", "MH", "MQ", "MR", "MU",
                "YT", "MX", "FM", "MD", "MC", "MN", "ME", "MS", "MA", "MZ",
                "MM", "NA", "NR", "NP", "NL", "AN", "NC", "NZ", "NI", "NE",
                "NG", "NU", "NF", "MP", "NO", "OM", "PK", "PW", "PS", "PA",
                "PG", "PY", "PE", "PH", "PN", "PL", "PT", "PR", "QA", "RE",
                "RO", "RU", "RW", "SH", "KN", "LC", "PM", "VC", "WS", "SM",
                "ST", "SA", "SN", "RS", "SC", "SL", "SG", "SK", "SI", "SB",
                "SO", "ZA", "GS", "ES", "LK", "SD", "SR", "SJ", "SZ", "SE",
                "CH", "SY", "TW", "TJ", "TZ", "TH", "TL", "TG", "TK", "TO",
                "TT", "TN", "TR", "TM", "TC", "TV", "UG", "UA", "AE", "GB",
                "US", "UM", "UY", "UZ", "VU", "VE", "VN", "VG", "VI", "WF",
                "EH", "YE", "ZM", "ZW"
            )

            GlobalScope.launch(Dispatchers.IO) {

                val database = getInstance(context)
                val estadoPaisDao = database.estadoPaisDao()

                var c = 0

                codigoPaises.forEach { codigo ->
                    estadoPaisDao.insert(EstadoPais(nombrePais = codigo, haEstado = false, visitando = false, ultimaModificacion = ""))
                    c++
                }

                Log.i("Inserts", "Inserts creados con éxito. ($c)")
            }
        }
    }
}