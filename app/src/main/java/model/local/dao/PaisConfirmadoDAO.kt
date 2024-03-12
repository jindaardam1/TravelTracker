package model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.local.entity.PaisConfirmado

/**
 * Data Access Object (DAO) para la entidad PaisConfirmado en la base de datos local.
 *
 * Permite realizar operaciones CRUD (Create, Read, Update, Delete) en la tabla de paises_confirmados.
 */
@Dao
interface PaisConfirmadoDAO {

    /**
     * Verifica si un país específico ha sido confirmado.
     *
     * @param codPais Código del país a verificar.
     * @return true si el país ha sido confirmado, false de lo contrario.
     */
    @Query("SELECT COUNT(pc.cod_confirmado) FROM estado_paises ep LEFT JOIN paises_confirmados pc ON ep.cod_pais = pc.cod_pais WHERE ep.nombre_pais = :codPais")
    fun esConfirmado(codPais: String): Boolean {
        return (esConfirmadoCount(codPais) > 0)
    }

    /**
     * Obtiene el número de veces que un país ha sido confirmado.
     *
     * @param codPais Código del país a contar.
     * @return Número de confirmaciones del país.
     */
    @Query("SELECT COUNT(pc.cod_confirmado) FROM estado_paises ep LEFT JOIN paises_confirmados pc ON ep.cod_pais = pc.cod_pais WHERE ep.nombre_pais = :codPais")
    fun esConfirmadoCount(codPais: String): Int

    /**
     * Inserta o actualiza un registro de PaisConfirmado en la base de datos.
     *
     * @param paisConfirmado PaisConfirmado a insertar o actualizar.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaisConfirmado(paisConfirmado: PaisConfirmado)
}
