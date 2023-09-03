package com.example.projecteyebrow

interface EyeBrowDestination { val route: String }

object HomePage: EyeBrowDestination { override val route: String = "HomePage" }

object CommunityPage: EyeBrowDestination { override val route: String = "CommunityPage" }

object WriteContentPage: EyeBrowDestination { override val route: String = "WriteContentPage" }

object TempContentListPage: EyeBrowDestination { override val route: String = "TempContentListPage" }

object LogInPage: EyeBrowDestination { override val route: String = "LogInPage" }

object SignInPage: EyeBrowDestination { override val route: String = "SignInPage" }

object FindPWDPage: EyeBrowDestination { override val route: String = "FindPWDPage" }