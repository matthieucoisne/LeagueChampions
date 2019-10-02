package com.leaguechampions.core.utils

//fun loadChampionImage(ivChampion: ImageView, championDetails: ChampionDetailsUiModel?) {
//    championDetails?.let {
//        loadChampionImage(ivChampion, it.id, it.version)
//    }
//}
//
//fun loadChampionImage(ivChampion: ImageView, champion: ChampionUiModel?) {
//    champion?.let {
//        loadChampionImage(ivChampion, it.id, it.version)
//    }
//}
//
//private fun loadChampionImage(ivChampion: ImageView, championId: String, championVersion: String) {
//    val context = ivChampion.context
//    val urlImage = UrlUtils.getImageUrl(context, championId, championVersion)
//    if (Const.isGlide) {
//        Glide.with(context)
//                .load(urlImage)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(ivChampion)
//    } else {
//        Picasso.with(context)
//                .load(urlImage)
//                .into(ivChampion)
//    }
//}
