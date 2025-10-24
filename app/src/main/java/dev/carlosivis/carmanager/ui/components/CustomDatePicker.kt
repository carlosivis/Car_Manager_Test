package dev.carlosivis.carmanager.ui.components

import android.text.format.DateFormat
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.carlosivis.carmanager.R
import java.util.Calendar
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit,
    initialSelectedDate: Long? = null,
    showModeToggle: Boolean = true
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDate
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let { millis ->
                    val calendar = Calendar.getInstance().apply {
                        timeInMillis = millis
                    }
                    val date = Date(millis)
                    val formattedDate = DateFormat.format("dd/MM/yyyy", date).toString()
                    onDateSelected(formattedDate)
                }
                onDismiss()
            }) {
                Text(text = stringResource(R.string.confirm_button))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.cancel_button))
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = showModeToggle
        )
    }

}