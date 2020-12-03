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
            InterestItem(0x9CC0E7, "Trendy Green", R.drawable.ic__01_headphones,false),
            InterestItem(0xEEEEEE, "Navy Blue", R.drawable.ic__02_game_controller ,false),
            InterestItem(0xACC5E8, "Dark Blue", R.drawable.ic__03_chesspieces,false),
            InterestItem(0xFAEACB, "Blue", R.drawable.ic__04_fried_egg,false),
            InterestItem(0xF7DBD7, "Stratos", R.drawable.ic__05_playing_cards,false),
            InterestItem(0xF4EBE6, "Swamp", R.drawable.ic__06_dough,false),
            InterestItem(0xE1dED7, "Resolution Blue", R.drawable.ic__07_smartphone,false),
            InterestItem(0x95A7B1, "Deep Fir", R.drawable.ic__08_chat,false),
            InterestItem(0xF0D2D0, "Evening Sea", R.drawable.ic__09_cake,false),
            InterestItem(0xFFCBD0, "Bahama Blue", R.drawable.ic__10_online_shopping,false),
            InterestItem(0xFDEACA, "Black Bean", R.drawable.ic__11_watering_can,false),
            InterestItem(0xFDF6DC, "Deep Sapphire", R.drawable.ic__12_herbal_tea,false),
            InterestItem(0xFDF6DC, "El Paso", R.drawable.ic__13_cactus,false),
            InterestItem(0xF9E0E3, "Cello", R.drawable.ic__14_cactus,false),
            InterestItem(0xCCDAE5, "Te Papa Green", R.drawable.ic__15_rubik,false),
            InterestItem(0xACC5E8, "Dodger Blue", R.drawable.ic__16_basketball,false),
            InterestItem(0xEECECE, "Eastern Blue", R.drawable.ic__17_telescope,false),
            InterestItem(0xF3ECD9, "Night Rider", R.drawable.ic__18_3d_glasses,false),
            InterestItem(0xC5D272, "Java", R.drawable.ic__19_coffee_maker,false),
            InterestItem(0xD3DF87, "Green Kelp", R.drawable.ic__20_pet_food,false),
            InterestItem(0x9CC0E7, "Curious Blue", R.drawable.ic__21_fish_tank,false),
            InterestItem(0xEEEEEE, "Paua", R.drawable.ic__22_laundry,false),
            InterestItem(0xACC5E8, "Paris M", R.drawable.ic__23_virtual_reality_glasses,false),
            InterestItem(0xFAEACB, "Wood Bark", R.drawable.ic__24_skincare,false),
            InterestItem(0xF7DBD7, "Blackcurrant", R.drawable.ic__25_chat,false),
            InterestItem(0xF4EBE6, "Mine Shaft", R.drawable.ic__26_online_learning,false),
            InterestItem(0xE1dED7, "Stromboli", R.drawable.ic__27_piano,false),
            InterestItem(0x95A7B1, "Bilbao", R.drawable.ic__28_vlog,false),
            InterestItem(0xF0D2D0, "Astral", R.drawable.ic__29_ping_pong,false),
            InterestItem(0xFFCBD0, "Christalle", R.drawable.ic__30_wine,false),
            InterestItem(0xFDEACA, "Waiouru", R.drawable.ic__31_skateboard,false),
            InterestItem(0xFDF6DC, "Ming", R.drawable.ic__32_microphone,false),
            InterestItem(0xFDF6DC, "La Palma", R.drawable.ic__33_music,false),
            InterestItem(0xF9E0E3, "Limed Spruce", R.drawable.ic__34_beer_mug,false),
            InterestItem(0xCCDAE5, "Dell", R.drawable.ic__35_reading,false),
            InterestItem(0xACC5E8, "Toledo", R.drawable.ic__36_drink,false),
            InterestItem(0xEECECE, "Bright Gray", R.drawable.ic__37_dumbbell,false),
            InterestItem(0xF3ECD9, "Cape Cod", R.drawable.ic__38_stamp,false),
            InterestItem(0xC5D272, "Lunar Green", R.drawable.ic__39_makeup,false),
            InterestItem(0xD3DF87, "Bean  ", R.drawable.ic__40_camera,false),
            InterestItem(0x9CC0E7, "Bistre", R.drawable.ic__41_plant,false),
            InterestItem(0xEEEEEE, "Puerto Rico", R.drawable.ic__42_sleeping_mask,false),
            InterestItem(0xF3ECD9, "Harlequin", R.drawable.ic__43_smartphone,false),
            InterestItem(0xFAEACB, "Brown Bramble", R.drawable.ic__44_music_player,false),
            InterestItem(0xF7DBD7, "Congo Brown", R.drawable.ic__45_shower,false),
            InterestItem(0xF4EBE6, "Millbrook", R.drawable.ic__46_movie_player,false),
            InterestItem(0xE1dED7, "Waikawa Gray", R.drawable.ic__47_online_shopping,false),
            InterestItem(0xFAEACB, "Mid Gray", R.drawable.ic__48_canvas,false),
            InterestItem(0xF0D2D0, "Shuttle Gray", R.drawable.ic__49_instant_camera,false),
            InterestItem(0xFFCBD0, "Aqua Forest", R.drawable.ic__50_coffee_cup,false)
        )
    }
}
