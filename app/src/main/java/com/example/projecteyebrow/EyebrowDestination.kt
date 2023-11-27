package com.example.projecteyebrow

interface EyeBrowDestination { val route: String }

object Home: EyeBrowDestination { override val route: String = "HomePage" }

object Collection: EyeBrowDestination { override val route: String = "CollectionPage" }

object Community: EyeBrowDestination { override val route: String = "CommunityPage" }

object WriteContent: EyeBrowDestination { override val route: String = "WriteContentPage" }

object TempContent: EyeBrowDestination { override val route: String = "TempContentListPage" }

object DetailedItem: EyeBrowDestination { override val route: String = "DetailedItemPage" }

object TattooistDetailed: EyeBrowDestination { override val route: String = "TattooistDetailedPage" }

object SignIn: EyeBrowDestination { override val route: String = "SignInPage" }

object FindPWD: EyeBrowDestination { override val route: String = "FindPWDPage" }

object Profile: EyeBrowDestination { override val route: String = "Profile" }