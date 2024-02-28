package model.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.entity.PaisConfirmado

interface PaisConfirmadoDAO {
    @Query("SELECT COUNT(pc.cod_confirmado) FROM estado_paises ep LEFT JOIN paises_confirmados pc ON ep.cod_pais = pc.cod_pais WHERE ep.nombre_pais = :codPais")
    fun esConfirmado(codPais: String): Boolean {
        return (esConfirmadoCount(codPais) > 0)
    }

    @Query("SELECT COUNT(pc.cod_confirmado) FROM estado_paises ep LEFT JOIN paises_confirmados pc ON ep.cod_pais = pc.cod_pais WHERE ep.nombre_pais = :codPais")
    fun esConfirmadoCount(codPais: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaisConfirmado(paisConfirmado: PaisConfirmado)
}
