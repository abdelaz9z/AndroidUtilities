package com.casecode.androidutils

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("bindItemQty")
fun TextView.bindItemQty(qty: Double) {
    this.text = "Qty:${qty}"
}

@BindingAdapter("bindItemSku")
fun TextView.bindItemSku(sku: String) {
    this.text = "Sku $sku"
}

