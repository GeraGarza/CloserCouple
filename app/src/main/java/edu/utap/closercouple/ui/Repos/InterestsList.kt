package edu.utap.closercouple.ui.main.dates.Repos

import android.graphics.Color
import edu.utap.closercouple.R

object InterestsList {
    private var list: List<InterestItem>

    class InterestItem(i: Int, var name: String, icon: Int, selected: Boolean) {
        var color: Int = 0
        var selected: Boolean = false
        var icon = R.drawable.ic_call_24dp
        init {
            this.color = Color.rgb(i shr 16 and 0xFF, i shr 8 and 0xFF, i and 0xFF)
            this.selected = selected
            this.icon = icon
        }
    }
    // These are all "static" methods because this is an object, not a class
    fun getAll() : List<InterestItem> { return list }
    fun size() : Int { return list.size }

    init {
        list = listOf (
            InterestItem(0x9CC0E7, "Music", R.drawable.ic__01_headphones,false),
            InterestItem(0xEEEEEE, "Games", R.drawable.ic__02_game_controller ,false),
            InterestItem(0xACC5E8, "Strategic", R.drawable.ic__03_chesspieces,false),
            InterestItem(0xFAEACB, "Cook", R.drawable.ic__04_fried_egg,false),
            InterestItem(0xF7DBD7, "Cards", R.drawable.ic__05_playing_cards,false),
            InterestItem(0xF4EBE6, "Explore", R.drawable.ic__06_dough,false),
            InterestItem(0xE1dED7, "Tech", R.drawable.ic__07_smartphone,false),
            InterestItem(0x95A7B1, "Chat", R.drawable.ic__08_chat,false),
            InterestItem(0xF0D2D0, "Bake", R.drawable.ic__09_cake,false),
            InterestItem(0xFFCBD0, "Shop", R.drawable.ic__10_online_shopping,false),
            InterestItem(0xFDEACA, "Grow", R.drawable.ic__11_watering_can,false),
            InterestItem(0xFDF6DC, "Vegan", R.drawable.ic__12_herbal_tea,false),
            InterestItem(0xFDF6DC, "Nature", R.drawable.ic__13_cactus,false),
            InterestItem(0xF9E0E3, "Outside", R.drawable.ic__14_cactus,false),
            InterestItem(0xCCDAE5, "Puzzle", R.drawable.ic__15_rubik,false),
            InterestItem(0xACC5E8, "Physical", R.drawable.ic__16_basketball,false),
            InterestItem(0xEECECE, "Science", R.drawable.ic__17_telescope,false),
            InterestItem(0xF3ECD9, "Cool", R.drawable.ic__18_3d_glasses,false),
            InterestItem(0xC5D272, "Camper", R.drawable.ic__19_coffee_maker,false),
            InterestItem(0xD3DF87, "Animals", R.drawable.ic__20_pet_food,false),
            InterestItem(0x9CC0E7, "Pets", R.drawable.ic__21_fish_tank,false),
            InterestItem(0xEEEEEE, "Clean", R.drawable.ic__22_laundry,false),
            InterestItem(0xACC5E8, "New", R.drawable.ic__23_virtual_reality_glasses,false),
            InterestItem(0xFAEACB, "Beauty", R.drawable.ic__24_skincare,false),
            InterestItem(0xF7DBD7, "Communicate", R.drawable.ic__25_chat,false),
            InterestItem(0xF4EBE6, "Learning", R.drawable.ic__26_online_learning,false),
            InterestItem(0xE1dED7, "Instrument", R.drawable.ic__27_piano,false),
            InterestItem(0x95A7B1, "vlog", R.drawable.ic__28_vlog,false),
            InterestItem(0xF0D2D0, "Simple Game", R.drawable.ic__29_ping_pong,false),
            InterestItem(0xFFCBD0, "Classy", R.drawable.ic__30_wine,false),
            InterestItem(0xFDEACA, "Adventure", R.drawable.ic__31_skateboard,false),
            InterestItem(0xFDF6DC, "Extrovert", R.drawable.ic__32_microphone,false),
            InterestItem(0xFDF6DC, "Art", R.drawable.ic__33_music,false),
            InterestItem(0xF9E0E3, "Drink", R.drawable.ic__34_beer_mug,false),
            InterestItem(0xCCDAE5, "Read", R.drawable.ic__35_reading,false),
            InterestItem(0xACC5E8, "More Drinks", R.drawable.ic__36_drink,false),
            InterestItem(0xEECECE, "Workout", R.drawable.ic__37_dumbbell,false),
            InterestItem(0xF3ECD9, "Museum", R.drawable.ic__38_stamp,false),
            InterestItem(0xC5D272, "Make Over", R.drawable.ic__39_makeup,false),
            InterestItem(0xD3DF87, "Photography", R.drawable.ic__40_camera,false),
            InterestItem(0x9CC0E7, "Garden", R.drawable.ic__41_plant,false),
            InterestItem(0xEEEEEE, "Nap", R.drawable.ic__42_sleeping_mask,false),
            InterestItem(0xF3ECD9, "Chill", R.drawable.ic__43_smartphone,false),
            InterestItem(0xFAEACB, "Retro", R.drawable.ic__44_music_player,false),
            InterestItem(0xF7DBD7, "Bathe", R.drawable.ic__45_shower,false),
            InterestItem(0xF4EBE6, "Movie", R.drawable.ic__46_movie_player,false),
            InterestItem(0xE1dED7, "Shop More", R.drawable.ic__47_online_shopping,false),
            InterestItem(0xFAEACB, "Paint", R.drawable.ic__48_canvas,false),
            InterestItem(0xF0D2D0, "Film", R.drawable.ic__49_instant_camera,false),
            InterestItem(0xFFCBD0, "Coffee", R.drawable.ic__50_coffee_cup,false)
        )
    }
}
