package com.a1gym.manager

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.FileProvider
import com.a1gym.manager.data.entity.Member
import com.a1gym.manager.R
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.text.NumberFormat
import java.util.Date
import java.util.Locale

object InvoiceHelper {

    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    fun shareViaWhatsApp(context: Context, member: Member) {
        val invoiceText = """
            🧾 *A1 Gym - Payment Receipt* 🧾
            --------------------------------
            *Name:* ${member.name}
            *Phone:* ${member.phone}
            *Payment Date:* ${dateFormat.format(Date())}
            
            *Plan:* ${member.planId}
            *Validity:* ${dateFormat.format(Date(member.startDate))} to ${dateFormat.format(Date(member.endDate))}
            
            *Amount Paid:* ₹${member.amount}
            *Payment Method:* ${member.paymentMethod}
            
            Thank you for choosing A1 Gym!
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.setPackage("com.whatsapp")
        intent.putExtra(Intent.EXTRA_TEXT, invoiceText)

        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
            // Fallback to normal share
            val fallbackIntent = Intent(Intent.ACTION_SEND)
            fallbackIntent.type = "text/plain"
            fallbackIntent.putExtra(Intent.EXTRA_TEXT, invoiceText)
            context.startActivity(Intent.createChooser(fallbackIntent, "Share Receipt"))
        }
    }

    fun generateAndSharePdf(context: Context, member: Member) {
        // Inflate the invoice XML layout
        val inflater = LayoutInflater.from(context)
        val invoiceView = inflater.inflate(R.layout.layout_invoice, null)

        // Find views
        val tvInvoiceNumber = invoiceView.findViewById<TextView>(R.id.tvInvoiceNumber)
        val tvInvoiceDate = invoiceView.findViewById<TextView>(R.id.tvInvoiceDate)
        val tvMemberName = invoiceView.findViewById<TextView>(R.id.tvMemberName)
        val tvMemberPhone = invoiceView.findViewById<TextView>(R.id.tvMemberPhone)
        val tvMemberAddress = invoiceView.findViewById<TextView>(R.id.tvMemberAddress)
        val tvMemberId = invoiceView.findViewById<TextView>(R.id.tvMemberId)
        
        val tvPlanName = invoiceView.findViewById<TextView>(R.id.tvPlanName)
        val tvPlanDuration = invoiceView.findViewById<TextView>(R.id.tvPlanDuration)
        val tvItemPrice = invoiceView.findViewById<TextView>(R.id.tvItemPrice)
        val tvItemTotal = invoiceView.findViewById<TextView>(R.id.tvItemTotal)
        
        val tvSubtotal = invoiceView.findViewById<TextView>(R.id.tvSubtotal)
        val tvTotalAmount = invoiceView.findViewById<TextView>(R.id.tvTotalAmount)
        
        val tvPaymentMethod = invoiceView.findViewById<TextView>(R.id.tvPaymentMethod)
        val tvTransactionId = invoiceView.findViewById<TextView>(R.id.tvTransactionId)
        val tvPaymentDate = invoiceView.findViewById<TextView>(R.id.tvPaymentDate)

        // Populate data
        val generatedInvoiceNo = "INV-" + SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault()).format(Date())
        tvInvoiceNumber.text = generatedInvoiceNo
        tvInvoiceDate.text = dateFormat.format(Date())

        tvMemberName.text = member.name
        tvMemberPhone.text = member.phone
        tvMemberAddress.text = member.address.ifEmpty { "N/A" }
        tvMemberId.text = "ID: MEM-${member.id}"

        val planName = when (member.planId) {
            1L -> "Gym Membership (1 Month Plan)"
            2L -> "Gym Membership (3 Month Plan)"
            3L -> "Gym Membership (6 Month Plan)"
            else -> "Gym Membership"
        }
        tvPlanName.text = planName
        tvPlanDuration.text = "Validity: ${dateFormat.format(Date(member.startDate))} → ${dateFormat.format(Date(member.endDate))}"
        
        val formattedAmount = "₹" + NumberFormat.getNumberInstance(Locale("en", "IN")).format(member.amount)
        
        tvItemPrice.text = formattedAmount
        tvItemTotal.text = formattedAmount
        tvSubtotal.text = formattedAmount
        tvTotalAmount.text = formattedAmount
        
        tvPaymentMethod.text = member.paymentMethod
        tvTransactionId.text = "N/A"
        tvPaymentDate.text = dateFormat.format(Date())

        // Measure & Layout the view (A4 size: 595 x 842 pt)
        val width = 595
        val height = 842
        
        // Android requires exactly measured specifications for an off-screen view hierarchy
        val measureWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY)
        val measureHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        
        invoiceView.measure(measureWidth, measureHeight)
        invoiceView.layout(0, 0, width, height)

        // Initialize PDF creation
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(width, height, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        // Draw the fully laid out view onto the PDF Canvas
        invoiceView.draw(page.canvas)

        pdfDocument.finishPage(page)

        // Save file
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "Receipt_${member.name.replace(" ", "_").trim()}.pdf")
        
        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(context, "PDF Generated Successfully", Toast.LENGTH_SHORT).show()
            
            // Share PDF
            val uri: Uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )
            
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "application/pdf"
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            context.startActivity(Intent.createChooser(shareIntent, "Share PDF Receipt"))
            
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error generating PDF: ${e.message}", Toast.LENGTH_SHORT).show()
        } finally {
            pdfDocument.close()
        }
    }
}
