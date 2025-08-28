package com.example.siaga.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AlarmViewModel : ViewModel() {
   var alarmUiState by mutableStateOf(AlarmUiState())

   fun setAlarmOn(alarmOn: Boolean) {
      alarmUiState = alarmUiState.copy(alarmOn = alarmOn)
   }

   fun setAlarmNotification(alarmNotification: Boolean) {
      alarmUiState = alarmUiState.copy(alarmNotification = alarmNotification)
   }
}

data class AlarmUiState(
   val alarmOn: Boolean = false,
   val alarmNotification: Boolean = false
)