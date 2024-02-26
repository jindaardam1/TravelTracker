import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "paises_confirmados",
    foreignKeys = [ForeignKey(
        entity = EstadoPais::class,
        parentColumns = ["cod_pais"],
        childColumns = ["cod_pais"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PaisConfirmado(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_confirmado")
    val codConfirmado: Int = 0,

    @ColumnInfo(name = "foto")
    val foto: ByteArray,

    @ColumnInfo(name = "cod_pais")
    val codPais: Int
)
