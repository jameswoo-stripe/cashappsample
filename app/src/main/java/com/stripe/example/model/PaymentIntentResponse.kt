package com.stripe.example.model

import com.google.gson.annotations.SerializedName

data class PaymentIntentResponse(
    @SerializedName("intent") val intent: String,
    @SerializedName("secret") val secret: String,
    @SerializedName("status") val status: String
)