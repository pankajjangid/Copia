package com.pankajjangid.copia

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.pankajjangid.copia.model.Receipts
import com.pankajjangid.copia.model.Result
import com.pankajjangid.copia.model.Transactions

class MainActivity : AppCompatActivity() {
    private val TAG = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val finalList = ArrayList<Result>()

        val transactions = getTransactions()
        val receipts = getReceipts()

        for (i in receipts.indices){
            for (j in transactions.indices){
                var amt=0
                if (receipts[i].amount!=0 && transactions[j].amount!=0){
                    if (receipts[i].amount>=transactions[j].amount){
                        amt = receipts[i].amount - transactions[j].amount
                        receipts[i].amount = amt
                        finalList.add(Result(receipt = receipts[i].receipt, refrence = transactions[j].refrence, amount = transactions[j].amount))
                        transactions[j].amount=0
                    }else{
                     amt = transactions[j].amount-receipts[i].amount
                        transactions[j].amount=amt
                        finalList.add(Result(receipt = receipts[i].receipt, refrence = transactions[j].refrence, amount = receipts[i].amount))
                        receipts[i].amount=0

                    }
                }


            }
        }
        finalList.forEach {
            Log.d("FINAL RESULT","${it.receipt} ${it.refrence} ${it.amount} ")
        }
    }


    fun getTransactions(): ArrayList<Transactions> {
        val receipts = ArrayList<Transactions>()
        receipts.add(Transactions("MG001", 100))
        receipts.add(Transactions("MG002", 200))
        receipts.add(Transactions("MG003", 300))
        receipts.add(Transactions("MG004", 250))
        return receipts
    }
  fun getReceipts(): ArrayList<Receipts> {
        val receipts = ArrayList<Receipts>()
        receipts.add(Receipts("R001", 100))
        receipts.add(Receipts("R002", 400))
        receipts.add(Receipts("R003", 350))
        return receipts
    }
}