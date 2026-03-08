package com.a1gym.manager.ui.payments

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.a1gym.manager.R
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
            binding.tvPaymentMethod.text = payment.method
            binding.tvAmount.text = "₹${payment.amount}"

            // Set icon tint and background based on payment method
            val (iconBgColor, iconTintColor) = when (payment.method.uppercase()) {
                "UPI" -> Pair(Color.parseColor("#E8F5E9"), Color.parseColor("#4CAF50"))
                "CASH" -> Pair(Color.parseColor("#FFF3E0"), Color.parseColor("#FF9800"))
                "ONLINE" -> Pair(Color.parseColor("#E8EAF6"), Color.parseColor("#2D3B8F"))
                else -> Pair(Color.parseColor("#F3F4F6"), Color.parseColor("#6B7280"))
            }

            val bg = binding.vIconBg.background as? GradientDrawable
            if (bg != null) {
                bg.setColor(iconBgColor)
            } else {
                val newBg = GradientDrawable()
                newBg.shape = GradientDrawable.OVAL
                newBg.setColor(iconBgColor)
                binding.vIconBg.background = newBg
            }
            binding.ivPaymentIcon.setColorFilter(iconTintColor)
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
