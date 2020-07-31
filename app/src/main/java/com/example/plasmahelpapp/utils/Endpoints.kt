package com.example.plasmahelpapp.utils

class Endpoints {
    private val base_url:String="https://studybus.000webhostapp.com/"

    public  var register_url:String=base_url+"register.php"


    public  var login_url:String=base_url+"login.php"

    public var donor_url:String=base_url+"gettingDonors.php"

    public var patient_url:String=base_url+"gettingPatients.php"
    public var search_donors_url:String=base_url+"search_donors.php"
    public var recent_url:String=base_url+"today.php"
}