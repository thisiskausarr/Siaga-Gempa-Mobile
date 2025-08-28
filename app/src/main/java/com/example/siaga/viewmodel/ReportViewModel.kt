package com.example.siaga.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siaga.repository.ReportsRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.first


class   ReportViewModel(
    private val repository: ReportsRepository = ReportsRepository()
) : ViewModel() {


    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "reported")
    fun getReported(context: Context)= viewModelScope.launch{
        reportUiState = reportUiState.copy(isReported= context.dataStore.data.first().get(intPreferencesKey("reported"))==1)
    }
    var reportUiState: ReportUiState by mutableStateOf(ReportUiState())
    fun CreateNewReport(context: Context)= viewModelScope.launch{
        val reported_time=System.currentTimeMillis().toInt()
       context.dataStore.edit { it->
           it[intPreferencesKey("reported")]=1
           it[intPreferencesKey("reported_time")]=reported_time
       }
        repository.createReport(reportUiState.location,reported_time.toLong()){it,report->
            reportUiState=reportUiState.copy(isError=it)
            reportUiState=reportUiState.copy(isReported=true)

        }
    }
    fun ResetReported(status:String,context: Context)= viewModelScope.launch{

        val reported_time=context.dataStore.data.first().get(intPreferencesKey("reported_time"))
        Log.d("reported_time",reported_time.toString())
        Log.d("status",status)
        if (reported_time != null) {
            repository.updateReportStatus(reported_time.toLong(),status)
            reportUiState = reportUiState.copy(isReported = false)
        }
        context.dataStore.edit { it->
            it[intPreferencesKey("reported")]=0
            it[intPreferencesKey("reported_time")]=0
        }
        reportUiState=reportUiState.copy(isReported=false)
    }
    fun ChangeLocation(location:LatLng){
        reportUiState=reportUiState.copy(location=location)
    }
}
data class ReportUiState(
    val isLoading: Boolean = false,
    val location:LatLng=LatLng(0.0,0.0),
    val error: String? = null,
    val isError: Boolean = false,
    val isReported: Boolean = false

)