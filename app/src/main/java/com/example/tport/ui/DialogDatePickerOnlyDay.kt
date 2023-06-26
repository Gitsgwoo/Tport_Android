import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.DatePicker

class DialogDatePickerOnlyDay(
    context: Context?,
    callBack: OnDateSetListener?, year: Int, monthOfYear: Int,
    dayOfMonth: Int
) :
    DatePickerDialog(context!!, callBack, year, monthOfYear, dayOfMonth) {
    private var TITLE: String? = null

    init {

        // 일, 월 선택 스피너 제거
        try {
            val datePickerDialogFields = DatePickerDialog::class.java.declaredFields
            for (datePickerDialogField in datePickerDialogFields) {
                if (datePickerDialogField.name == "mDatePicker") {
                    datePickerDialogField.isAccessible = true
                    val datePicker = datePickerDialogField[this] as DatePicker
                    val datePickerFields = datePickerDialogField.type
                        .declaredFields
                    for (datePickerField in datePickerFields) {
                        if ("mYearPicker" == datePickerField.name || "mYearSpinner" == datePickerField.name) {
                            datePickerField.isAccessible = true
                            var dayPicker = Any()
                            dayPicker = datePickerField[datePicker] as Any
                            (dayPicker as View).visibility = View.GONE
                        }
                    }
                }
            }
        } catch (ex: Exception) {
        }
        if (TITLE != null) setTitle(TITLE)
    }

    fun setFixedTitle(title: String?) {
        TITLE = title
        setTitle(TITLE)
    }

    override fun onDateChanged(view: DatePicker, year: Int, month: Int, day: Int) {
        super.onDateChanged(view, year, month, day)
        if (TITLE != null) setTitle(TITLE)
    }
}