package edn.projek.oxygen.home.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edn.projek.oxygen.R
import edn.projek.oxygen.model.Bill
import edn.projek.oxygen.payment.PaymentActivity
import kotlinx.android.synthetic.main.list_bill.view.*
import java.io.Serializable

class BillAdapter(val listData: List<Bill>,val context: Context) : RecyclerView.Adapter<BillAdapter.TagihanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagihanViewHolder {
        return TagihanViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_bill, parent, false))
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: TagihanViewHolder, position: Int) {
        holder.bindBill(listData[position])
        holder.itemView.setOnClickListener({view ->
            val intent  = Intent(context,PaymentActivity::class.java)
            intent.putExtra("data",listData[position])
            context.startActivity(intent)
        })
    }

    class TagihanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val namaPaket = view.txtNamaPaket
        private val dueDate = view.txtDueDate
        private val status = view.txtStatus

        fun bindBill(bill: Bill) {
            namaPaket.text = bill.`package`
            dueDate.text = bill.due_date
            status.text = bill.status
        }

    }
}