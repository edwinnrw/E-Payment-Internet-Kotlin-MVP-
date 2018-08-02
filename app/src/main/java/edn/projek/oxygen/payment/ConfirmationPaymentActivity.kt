package edn.projek.oxygen.payment

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import edn.projek.oxygen.R
import edn.projek.oxygen.home.MainActivity
import edn.projek.oxygen.model.Invoice
import edn.projek.oxygen.payment.presenter.ConfirmCotract
import edn.projek.oxygen.payment.presenter.ConfirmPaymentPresenter
import kotlinx.android.synthetic.main.activity_confirmation_payment.*
import kotlinx.android.synthetic.main.custom_dialog.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ConfirmationPaymentActivity : AppCompatActivity(), View.OnClickListener, ConfirmCotract.View {


    lateinit var presenter: ConfirmPaymentPresenter
    lateinit var progress: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_payment)
        presenter= ConfirmPaymentPresenter(this,this)
        custNameConfirm.text = intent.getStringExtra("name")
        billConfirm.text = intent.getStringExtra("bill")
        cardNumber.text = intent.getStringExtra("nocredit")
        var formatTransDate = SimpleDateFormat("EEE, d MMM yyyy, HH:mm", Locale.getDefault())
        var currentTime = Date()
        transDateTime.text = formatTransDate.format(currentTime)
        btnCancel.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)
        progress = ProgressDialog(this)
        progress.setMessage("Loading...")

    }

    override fun onBackPressed() {
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnCancel -> startActivity(Intent(this, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            R.id.btnSubmit -> presenter.requestPayment(cardNumber.text.toString(),
                    intent.getStringExtra("id_billing"), billConfirm.text.toString())
        }

    }

    override fun showProgress() {
        progress.show()
    }

    override fun dismisProgress() {
        progress.dismiss()
    }

    override fun requestFailed(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()

    }

    override fun requestSuccess(message: String, data: Invoice) {
        createDialog(message, data)
    }

    fun createDialog(message: String, data:Invoice){
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog)
        var transStatus = dialog.findViewById(R.id.transStatus) as TextView
        var amount = dialog.findViewById(R.id.amount) as TextView
        var card = dialog.findViewById(R.id.card_number) as TextView
        var invoice = dialog.findViewById(R.id.invoice) as TextView
        var icon= dialog.findViewById(R.id.icon_status) as ImageView
        var btnoke= dialog.findViewById(R.id.btnoke) as Button
        transStatus.text = message
        icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_check))
        amount.text = billConfirm.text
        card.text = data.no_kartu_kredit
        invoice.text = data.invoice
        btnoke.setOnClickListener({view ->
            startActivity(Intent(this, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        })
        dialog.show()

    }




}
