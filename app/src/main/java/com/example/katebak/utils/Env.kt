package com.example.katebak.utils

import com.example.katebak.data.models.ProfileModel
import com.example.plantsapp.utils.PreDefs

object Env {
    val apiBaseUrl = "http://services.naboapp.ir/k/"
    var userProblem = ""
    var userPhoneNumber = ""

    var userProfile : ProfileModel? = null
    var store: PreDefs = PreDefs(App.context)
    var selectedChipColor = 0
}