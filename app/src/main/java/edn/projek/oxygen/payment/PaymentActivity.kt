package edn.projek.oxygen.payment

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import edn.projek.oxygen.R
import edn.projek.oxygen.model.Bill
import edn.projek.oxygen.model.ChannelPayment
import edn.projek.oxygen.payment.presenter.PaymentContract
import edn.projek.oxygen.payment.presenter.PaymentPresenter
import kotlinx.android.synthetic.main.activity_payment.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PaymentActivity : AppCompatActivity(), PaymentContract.View, View.OnClickListener {



    lateinit var presenter: PaymentPresenter
    lateinit var progress: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        presenter = PaymentPresenter(this, this)
        progress = ProgressDialog(this)
        progress.setMessage("Loading...")
        progress.setCancelable(false)
        presenter.getDataTagihan()
        presenter.requestGetChannel()
        btnPayment.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        var dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setMessage("Apakah Anda Yakin")
        dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
            presenter.checkValidField(paymentMethodPay.selectedItem.toString(), no_creditcard.text.toString(),
                    edt_cvv.text.toString(), exp_date_month.selectedItem.toString(),
                    exp_date_year.selectedItem.toString())
        })
        dialog.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->

        })
        dialog.show()
    }

    override fun setDataBill(data: HashMap<String, String>) {
        var bill: Bill = intent.getSerializableExtra("data") as Bill
        noCustPay.text = data.getValue("no_customer")
        nameCustPay.text = data.getValue("name")
        packageCusPay.text = bill.`package`
        billingIdPay.text = bill.id_billing
        var dateBillDate: Date = SimpleDateFormat("yyyy-MM-dd").parse(bill.bill_date)
        billingDatePay.text = SimpleDateFormat("dd MMM yyyy").format(dateBillDate)
        var dueDateBill = SimpleDateFormat("yyyy-MM-dd").parse(bill.due_date)
        dueDatePay.text = SimpleDateFormat("dd MMM yyyy").format(dueDateBill)
        totalBill.text = String.format(Locale.US, "Rp. %s ,-",
                NumberFormat.getNumberInstance(Locale.US).format(bill.total_bill))
        statusPay.text = bill.status.toUpperCase()

    }

    override fun setSpinnerChannel(data: List<ChannelPayment>) {
        var list = ArrayList<String>()
        list.add("Choose Channel")
        for (index in data.indices) {
            list.add(data[index].nama_payment)
        }
        var dataAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        paymentMethodPay.adapter = dataAdapter
        paymentMethodPay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var item = p0?.getItemAtPosition(p2)
                if (item.toString().contentEquals("Credit Card")) {
                    form_pembayaran.visibility = View.VISIBLE
                } else {
                    form_pembayaran.visibility = View.GONE
                }
            }

        }


    }

    override fun requestSuccess() {
        var intent = Intent(this, ConfirmationPaymentActivity::class.java)
        intent.putExtra("name",nameCustPay.text.toString())
        intent.putExtra("bill", totalBill.text.toString())
        intent.putExtra("nocredit", no_creditcard.text.toString())
        intent.putExtra("id_billing", billingIdPay.text.toString())
        startActivity(intent)

    }

    override fun requestFailed(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()

    }

    override fun validFailed(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    override fun validSuccess() {
        presenter.requestCheckCard(exp_date_month.selectedItem.toString(),exp_date_year.selectedItem.toString(),
                edt_cvv.text.toString(), no_creditcard.text.toString())
    }

    override fun showProgress() {
        progress.show()

    }

    override fun dismissProgress() {
        progress.dismiss()
    }

}
