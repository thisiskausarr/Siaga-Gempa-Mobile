package com.example.siaga.navigation

sealed class Screens (val screen: String){
    object Home : Screens("Home")
    object Register : Screens("Register")
    object Login : Screens("Login")
    object Forget_Password : Screens("Forget_Password")
    object Emergency_Call : Screens("Emergency_Call")
    object Alarm : Screens("Alarm")
    object History : Screens("History")
    object Education : Screens("Education")
    object Donation : Screens ("Donation")
    object Sosialization :Screens("Sosialization")
    object Register_Sosialization:Screens("Register_Sosialization")
    object Profil : Screens("Profil")
    object Edit_Profil : Screens("Edit_Profil")
    object Change_Password : Screens("Change_Password")
    object Help_Center : Screens("Help_Center")
    object Contanctus : Screens("Contactus")
    object Location :Screens("Location")
    object Chatbot :Screens("Chatbot")
    object Sos : Screens("Sos")
    object Sos_Detail :Screens("Sos_Detail")
    object Mitigation : Screens("Mitigation")
    object Hazard_Maps : Screens("HazardMaps")
    object History_Detail : Screens("Hsitroy_Detail")
    object Goggle : Screens("Goggle")
    object UrgentCall : Screens("UrgentCall")
    object TabsHistory : Screens("TabsHistory")
    object TabsEducation : Screens("TabsEducation")
    object Final_Sosialization : Screens("Final_Sosialization")
    object TabsDonation : Screens("TabsDonation")
    object Payment : Screens("Payment/{donationId}"){
        const val ARG_CONTENT_DONATION_ID = "donationId"

    }
    object ReportDonation : Screens("ReportDonation")
    object Notif : Screens("Notif")
}