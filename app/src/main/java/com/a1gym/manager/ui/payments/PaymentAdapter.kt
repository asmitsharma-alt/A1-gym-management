package com.a1gym.manager.ui.payments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.a1gym.manager.data.entity.Payment
import com.a1gym.manager.databinding.ItemPaymentBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PaymentAdapter : ListAdapter<Payment, PaymentAdapter.PaymentViewHolder>(PaymentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding = ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PaymentViewHolder(private val binding: ItemPaymentBinding) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())

        fun bind(payment: Payment) {
            binding.tvInvoiceNumber.text = payment.invoiceNumber
            binding.tvPaymentDate.text = dateFormat.format(Date(payment.paymentDate))
            binding.tvPaymentMethod.text = "Method: ${payment.method}"
            binding.tvAmount.text = "₹${payment.amount}"
        }
    }
}

class PaymentDiffCallback : DiffUtil.ItemCallback<Payment>() {
    override fun areItemsTheSame(oldItem: Payment, newItem: Payment): Boolean {
        return oldItem.paymentId == newItem.paymentId
    }

    override fun areContentsTheSame(oldItem: Payment, newItem: Payment): Boolean {
        return oldItem == newItem
    }
}
