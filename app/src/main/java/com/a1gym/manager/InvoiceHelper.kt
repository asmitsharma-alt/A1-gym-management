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
import androidx.core.content.FileProvider
import com.a1gym.manager.data.entity.Member
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
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
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 size
        val page = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = page.canvas
        val paint = Paint()

        // Draw Header
        paint.color = Color.BLACK
        paint.textSize = 32f
        paint.isFakeBoldText = true
        canvas.drawText("A1 Gym - Payment Receipt", 50f, 80f, paint)

        paint.textSize = 18f
        paint.isFakeBoldText = false
        canvas.drawText("Date: ${dateFormat.format(Date())}", 50f, 120f, paint)

        canvas.drawLine(50f, 140f, 545f, 140f, paint)

        // Draw Member Details
        paint.textSize = 20f
        canvas.drawText("Member Details:", 50f, 180f, paint)
        paint.textSize = 18f
        canvas.drawText("Name: ${member.name}", 50f, 210f, paint)
        canvas.drawText("Phone: ${member.phone}", 50f, 240f, paint)
        canvas.drawText("Address: ${member.address.ifEmpty { "N/A" }}", 50f, 270f, paint)

        // Draw Membership Details
        paint.textSize = 20f
        paint.isFakeBoldText = true
        canvas.drawText("Membership & Payment:", 50f, 320f, paint)
        paint.textSize = 18f
        paint.isFakeBoldText = false
        
        canvas.drawText("Start Date: ${dateFormat.format(Date(member.startDate))}", 50f, 350f, paint)
        canvas.drawText("End Date: ${dateFormat.format(Date(member.endDate))}", 50f, 380f, paint)
        
        paint.isFakeBoldText = true
        canvas.drawText("Amount Paid: ₹${member.amount}", 50f, 420f, paint)
        paint.isFakeBoldText = false
        canvas.drawText("Payment Method: ${member.paymentMethod}", 50f, 450f, paint)

        // Footer
        canvas.drawLine(50f, 500f, 545f, 500f, paint)
        paint.textSize = 16f
        canvas.drawText("Thank you for choosing A1 Gym!", 50f, 530f, paint)

        pdfDocument.finishPage(page)

        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "Receipt_${member.name.replace(" ", "_")}.pdf")
        
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
