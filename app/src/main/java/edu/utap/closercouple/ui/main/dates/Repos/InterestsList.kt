package edu.utap.closercouple.ui.main.dates.Repos

import android.graphics.Color

object InterestsList {
    private var list: List<InterestItem>

    class InterestItem(i: Int, var name: String) {
        var color: Int = 0
        init {
            this.color = Color.rgb(i shr 16 and 0xFF, i shr 8 and 0xFF, i and 0xFF)
        }
    }
    // These are all "static" methods because this is an object, not a class
    fun getAll() : List<InterestItem> { return list }
    fun size() : Int { return list.size }

    init {
        list = listOf (
            InterestItem(0x000000, "Black"),
            InterestItem(0x000080, "Navy Blue"),
            InterestItem(0x0000C8, "Dark Blue"),
            InterestItem(0x0000FF, "Blue"),
            InterestItem(0x000741, "Stratos"),
            InterestItem(0x001B1C, "Swamp"),
            InterestItem(0x002387, "Resolution Blue"),
            InterestItem(0x002900, "Deep Fir"),

            InterestItem(0x024E46, "Evening Sea"),
            InterestItem(0x026395, "Bahama Blue"),

            InterestItem(0x081910, "Black Bean"),
            InterestItem(0x082567, "Deep Sapphire"),
            InterestItem(0x1E1708, "El Paso"),
            InterestItem(0x1E385B, "Cello"),
            InterestItem(0x1E433C, "Te Papa Green"),
            InterestItem(0x1E90FF, "Dodger Blue"),
            InterestItem(0x1E9AB0, "Eastern Blue"),
            InterestItem(0x1F120F, "Night Rider"),
            InterestItem(0x1FC2C2, "Java"),

            InterestItem(0x25311C, "Green Kelp"),
            InterestItem(0x2596D1, "Curious Blue"),
            InterestItem(0x260368, "Paua"),
            InterestItem(0x26056A, "Paris M"),
            InterestItem(0x261105, "Wood Bark"),

            InterestItem(0x32293A, "Blackcurrant"),
            InterestItem(0x323232, "Mine Shaft"),
            InterestItem(0x325D52, "Stromboli"),
            InterestItem(0x327C14, "Bilbao"),
            InterestItem(0x327DA0, "Astral"),
            InterestItem(0x33036B, "Christalle"),

            InterestItem(0x363C0D, "Waiouru"),
            InterestItem(0x36747D, "Ming"),
            InterestItem(0x368716, "La Palma"),

            InterestItem(0x394851, "Limed Spruce"),
            InterestItem(0x396413, "Dell"),
            InterestItem(0x3A0020, "Toledo"),

            InterestItem(0x3C4151, "Bright Gray"),
            InterestItem(0x3C4443, "Cape Cod"),
            InterestItem(0x3C493A, "Lunar Green"),
            InterestItem(0x3D0C02, "Bean  "),
            InterestItem(0x3D2B1F, "Bistre"),

            InterestItem(0x3FC1AA, "Puerto Rico"),
            InterestItem(0x3FFF00, "Harlequin"),

            InterestItem(0x592804, "Brown Bramble"),
            InterestItem(0x593737, "Congo Brown"),
            InterestItem(0x594433, "Millbrook"),
            InterestItem(0x5A6E9C, "Waikawa Gray"),

            InterestItem(0x5F5F6E, "Mid Gray"),
            InterestItem(0x5F6672, "Shuttle Gray"),
            InterestItem(0x5FA777, "Aqua Forest"),
            InterestItem(0x5FB3AC, "Tradewind"),

            InterestItem(0x61845F, "Glade Green"),
            InterestItem(0x622F30, "Buccaneer"),
            InterestItem(0x623F2D, "Quincy"),
            InterestItem(0x624E9A, "Butterfly Bush"),
            InterestItem(0x625119, "West Coast"),

            InterestItem(0x704F50, "Ferra"),
            InterestItem(0x706555, "Coffee"),
            InterestItem(0x749378, "Laurel"),
            InterestItem(0x74C365, "Mantis"),
            InterestItem(0x755A57, "Russett"),
            InterestItem(0x7563A8, "Deluge"),
            InterestItem(0x76395D, "Cosmic"),
            InterestItem(0x7666C6, "Blue Marguerite"),
            InterestItem(0x76BD17, "Lima"),
            InterestItem(0x76D7EA, "Sky Blue"),

            InterestItem(0x7A013A, "Siren"),
            InterestItem(0x7A58C1, "Fuchsia Blue"),
            InterestItem(0x7A7A7A, "Boulder"),

            InterestItem(0x7C7B7A, "Concord"),
            InterestItem(0x7C7B82, "Jumbo"),
            InterestItem(0x7C881A, "Trendy Green"),

            InterestItem(0x803790, "Vivid Violet"),
            InterestItem(0x80461B, "Russet"),
            InterestItem(0x807E79, "Friar Gray"),
            InterestItem(0x808000, "Olive"),
            InterestItem(0x808080, "Gray"),
            InterestItem(0x80B3AE, "Gulf Stream"),
            InterestItem(0x80B3C4, "Glacier"),
            InterestItem(0x80CCEA, "Seagull"),
            InterestItem(0x81422C, "Nutmeg"),
            InterestItem(0x816E71, "Spicy Pink"),

            InterestItem(0x8C5738, "Potters Clay"),

            InterestItem(0x8D90A1, "Manatee"),
            InterestItem(0x8DA8CC, "Polo Blue"),

            InterestItem(0x926F5B, "Beaver"),

            InterestItem(0x956387, "Strikemaster"),
            InterestItem(0x959396, "Mountain Mist"),
            InterestItem(0x960018, "Carmine"),
            InterestItem(0x964B00, "Brown"),

            InterestItem(0x9E5B40, "Sepia Skin"),
            InterestItem(0x9EA587, "Sage"),
            InterestItem(0x9EA91F, "Citron"),

            InterestItem(0x9FDD8C, "Feijoa"),
            InterestItem(0xA02712, "Tabasco"),

            InterestItem(0xBB8983, "Brandy Rose"),
            InterestItem(0xBBD009, "Rio Grande"),

            InterestItem(0xBDBBD7, "Lavender Gray"),
            InterestItem(0xBDBDC6, "French Gray"),

            InterestItem(0xC1BECD, "Gray Suit"),
            InterestItem(0xC1D7B0, "Sprout"),

            InterestItem(0xC3CDE6, "Periwinkle Gray"),
            InterestItem(0xC3D1D1, "Tiara"),

            InterestItem(0xD4DFE2, "Geyser"),
            InterestItem(0xD4E2FC, "Hawkes Blue"),
            InterestItem(0xD54600, "Grenadier"),

            InterestItem(0xD7837F, "New York Pink"),

            InterestItem(0xDB9690, "Petite Orchid"),
            InterestItem(0xDB995E, "Di Serria"),
            InterestItem(0xDBDBDB, "Alto"),
            InterestItem(0xDBFFF8, "Frosted Mint"),
            InterestItem(0xDC143C, "Crimson"),
            InterestItem(0xDC4333, "Punch"),
            InterestItem(0xDCB20C, "Galliano"),

            InterestItem(0xE1C0C8, "Pink Flare"),
            InterestItem(0xE1E6D6, "Periglacial Blue"),
            InterestItem(0xE1EAD4, "Kidnapper"),

            InterestItem(0xE2EBED, "Mystic"),
            InterestItem(0xE2F3EC, "Apple Green"),
            InterestItem(0xE30B5C, "Razzmatazz"),
            InterestItem(0xE32636, "Alizarin Crimson"),
            InterestItem(0xE34234, "Cinnabar"),
            InterestItem(0xE3BEBE, "Cavern Pink"),

            InterestItem(0xED989E, "Sea Pink"),
            InterestItem(0xEDB381, "Tacao"),
            InterestItem(0xEDC9AF, "Desert Sand"),
            InterestItem(0xEDCDAB, "Pancho"),
            InterestItem(0xEDDCB1, "Chamois"),
            InterestItem(0xEDEA99, "Primrose"),
            InterestItem(0xEDF5DD, "Frost"),
            InterestItem(0xEDF5F5, "Aqua Haze"),
            InterestItem(0xEDF6FF, "Zumthor"),

            InterestItem(0xF3E9E5, "Dawn Pink"),
            InterestItem(0xF3EDCF, "Wheatfield"),
            InterestItem(0xF3FB62, "Canary"),
            InterestItem(0xF3FBD4, "Orinoco"),

            InterestItem(0xF5C85C, "Cream Can"),
            InterestItem(0xF5C999, "Manhattan"),
            InterestItem(0xF5D5A0, "Maize"),

            InterestItem(0xF8D9E9, "Cherub"),
            InterestItem(0xF8DB9D, "Marzipan"),

            InterestItem(0xFDE295, "Golden Glow"),
            InterestItem(0xFDE910, "Lemon"),
            InterestItem(0xFDF5E6, "Old Lace"),
            InterestItem(0xFDF6D3, "Half Colonial White"),
            InterestItem(0xFDF7AD, "Drover"),
            InterestItem(0xFDFEB8, "Pale Prim"),
            InterestItem(0xFDFFD5, "Cumulus"),

            InterestItem(0xFEE5AC, "Cape Honey"),

            InterestItem(0xFF6FFF, "Blush Pink"),
            InterestItem(0xFF7034, "Burning Orange"),
            InterestItem(0xFF7518, "Pumpkin"),

            InterestItem(0xFFA6C9, "Carnation Pink"),
            InterestItem(0xFFAB81, "Hit Pink"),
            InterestItem(0xFFAE42, "Yellow Orange"),
            InterestItem(0xFFB0AC, "Cornflower Lilac"),
            InterestItem(0xFFB1B3, "Sundown"),
            InterestItem(0xFFB31F, "My Sin"),
            InterestItem(0xFFB555, "Texas Rose"),
            InterestItem(0xFFB7D5, "Cotton Candy"),
            InterestItem(0xFFB97B, "Macaroni and Cheese"),
            InterestItem(0xFFBA00, "Selective Yellow"),

            InterestItem(0xFFFFF0, "Ivory"),
            InterestItem(0xFFFFFF, "White")
        )
    }
}
