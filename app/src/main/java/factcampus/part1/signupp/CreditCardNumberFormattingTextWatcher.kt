package factcampus.part1.signupp

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import kotlin.collections.joinToString
import kotlin.text.chunked
import kotlin.text.replace


class CreditCardNumberFormattingTextWatcher : TextWatcher {
    private var current = ""
    private var listener : OnCreditCardNumberChangedListener? = null


    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        listener?.onCreditNumberChanged(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable) {
        if (s.toString() != current) {
            val userInput = s.toString().replace(nonDigits, "")
            if (userInput.length <= 16) {
                current = userInput.chunked(4).joinToString(" ")
                s.filters = arrayOfNulls<InputFilter>(0)
            }
            s.replace(0, s.length, current, 0, current.length)
        }
    }

    companion object {

        private val nonDigits = Regex("[^\\d]")
    }
    interface OnCreditCardNumberChangedListener {
        fun onCreditNumberChanged(cardNumber: String)

    }
        fun setOnCreditCardNumberChangedListener(listener: OnCreditCardNumberChangedListener){
            this.listener = listener


        }

    }


