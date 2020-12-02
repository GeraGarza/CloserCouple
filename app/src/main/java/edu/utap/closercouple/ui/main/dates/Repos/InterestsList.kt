package edu.utap.closercouple.ui.main.dates.Repos

import android.graphics.Color

object InterestsList {
    private var list: List<InterestItem>

    class InterestItem(i: Int, var name: String, selected: Boolean) {
        var color: Int = 0
        var selected: Boolean = false
        init {
            this.color = Color.rgb(i shr 16 and 0xFF, i shr 8 and 0xFF, i and 0xFF)
            this.selected = selected
        }
    }
    // These are all "static" methods because this is an object, not a class
    fun getAll() : List<InterestItem> { return list }
    fun size() : Int { return list.size }

    init {
        list = listOf (
            InterestItem(0x000000, "Black", false),
            InterestItem(0x000080, "Navy Blue", false),
            InterestItem(0x0000C8, "Dark Blue", false),
            InterestItem(0x0000FF, "Blue", false),
            InterestItem(0x000741, "Stratos", false),
            InterestItem(0x001B1C, "Swamp", false),
            InterestItem(0x002387, "Resolution Blue", false),
            InterestItem(0x002900, "Deep Fir", false),
            InterestItem(0x024E46, "Evening Sea",false),
            InterestItem(0x026395, "Bahama Blue",false),

            InterestItem(0x081910, "Black Bean",false),
            InterestItem(0x082567, "Deep Sapphire",false),
            InterestItem(0x1E1708, "El Paso",false),
            InterestItem(0x1E385B, "Cello",false),
            InterestItem(0x1E433C, "Te Papa Green",false),
            InterestItem(0x1E90FF, "Dodger Blue",false),
            InterestItem(0x1E9AB0, "Eastern Blue",false),
            InterestItem(0x1F120F, "Night Rider",false),
            InterestItem(0x1FC2C2, "Java",false),

            InterestItem(0x25311C, "Green Kelp",false),
            InterestItem(0x2596D1, "Curious Blue",false),
            InterestItem(0x260368, "Paua",false),
            InterestItem(0x26056A, "Paris M",false),
            InterestItem(0x261105, "Wood Bark",false),

            InterestItem(0x32293A, "Blackcurrant",false),
            InterestItem(0x323232, "Mine Shaft",false),
            InterestItem(0x325D52, "Stromboli",false),
            InterestItem(0x327C14, "Bilbao",false),
            InterestItem(0x327DA0, "Astral",false),
            InterestItem(0x33036B, "Christalle",false),

            InterestItem(0x363C0D, "Waiouru",false),
            InterestItem(0x36747D, "Ming",false),
            InterestItem(0x368716, "La Palma",false),

            InterestItem(0x394851, "Limed Spruce",false),
            InterestItem(0x396413, "Dell",false),
            InterestItem(0x3A0020, "Toledo",false),

            InterestItem(0x3C4151, "Bright Gray",false),
            InterestItem(0x3C4443, "Cape Cod",false),
            InterestItem(0x3C493A, "Lunar Green",false),
            InterestItem(0x3D0C02, "Bean  ",false),
            InterestItem(0x3D2B1F, "Bistre",false),

            InterestItem(0x3FC1AA, "Puerto Rico",false),
            InterestItem(0x3FFF00, "Harlequin",false),

            InterestItem(0x592804, "Brown Bramble",false),
            InterestItem(0x593737, "Congo Brown",false),
            InterestItem(0x594433, "Millbrook",false),
            InterestItem(0x5A6E9C, "Waikawa Gray",false),

            InterestItem(0x5F5F6E, "Mid Gray",false),
            InterestItem(0x5F6672, "Shuttle Gray",false),
            InterestItem(0x5FA777, "Aqua Forest",false),
            InterestItem(0x5FB3AC, "Tradewind",false),

            InterestItem(0x61845F, "Glade Green",false),
            InterestItem(0x622F30, "Buccaneer",false),
            InterestItem(0x623F2D, "Quincy",false),
            InterestItem(0x624E9A, "Butterfly Bush",false),
            InterestItem(0x625119, "West Coast",false),

            InterestItem(0x704F50, "Ferra",false),
            InterestItem(0x706555, "Coffee",false),
            InterestItem(0x749378, "Laurel",false),
            InterestItem(0x74C365, "Mantis",false),
            InterestItem(0x755A57, "Russett",false),
            InterestItem(0x7563A8, "Deluge",false),
            InterestItem(0x76395D, "Cosmic",false),
            InterestItem(0x7666C6, "Blue Marguerite",false),
            InterestItem(0x76BD17, "Lima",false),
            InterestItem(0x76D7EA, "Sky Blue",false),

            InterestItem(0x7A013A, "Siren",false),
            InterestItem(0x7A58C1, "Fuchsia Blue",false),
            InterestItem(0x7A7A7A, "Boulder",false),

            InterestItem(0x7C7B7A, "Concord",false),
            InterestItem(0x7C7B82, "Jumbo",false),
            InterestItem(0x7C881A, "Trendy Green",false),

            InterestItem(0x803790, "Vivid Violet",false),
            InterestItem(0x80461B, "Russet",false),
            InterestItem(0x807E79, "Friar Gray",false),
            InterestItem(0x808000, "Olive",false),
            InterestItem(0x808080, "Gray",false),
            InterestItem(0x80B3AE, "Gulf Stream",false),
            InterestItem(0x80B3C4, "Glacier",false),
            InterestItem(0x80CCEA, "Seagull",false),
            InterestItem(0x81422C, "Nutmeg",false),
            InterestItem(0x816E71, "Spicy Pink",false),

            InterestItem(0x8C5738, "Potters Clay",false),

            InterestItem(0x8D90A1, "Manatee",false),
            InterestItem(0x8DA8CC, "Polo Blue",false),

            InterestItem(0x926F5B, "Beaver",false),

            InterestItem(0x956387, "Strikemaster",false),
            InterestItem(0x959396, "Mountain Mist",false),
            InterestItem(0x960018, "Carmine",false),
            InterestItem(0x964B00, "Brown",false),

            InterestItem(0x9E5B40, "Sepia Skin",false),
            InterestItem(0x9EA587, "Sage",false),
            InterestItem(0x9EA91F, "Citron",false),

            InterestItem(0x9FDD8C, "Feijoa",false),
            InterestItem(0xA02712, "Tabasco",false),

            InterestItem(0xBB8983, "Brandy Rose",false),
            InterestItem(0xBBD009, "Rio Grande",false),

            InterestItem(0xBDBBD7, "Lavender Gray",false),
            InterestItem(0xBDBDC6, "French Gray",false),

            InterestItem(0xC1BECD, "Gray Suit",false),
            InterestItem(0xC1D7B0, "Sprout",false),

            InterestItem(0xC3CDE6, "Periwinkle Gray",false),
            InterestItem(0xC3D1D1, "Tiara",false),

            InterestItem(0xD4DFE2, "Geyser",false),
            InterestItem(0xD4E2FC, "Hawkes Blue",false),
            InterestItem(0xD54600, "Grenadier",false),

            InterestItem(0xD7837F, "New York Pink",false),

            InterestItem(0xDB9690, "Petite Orchid",false),
            InterestItem(0xDB995E, "Di Serria",false),
            InterestItem(0xDBDBDB, "Alto",false),
            InterestItem(0xDBFFF8, "Frosted Mint",false),
            InterestItem(0xDC143C, "Crimson",false),
            InterestItem(0xDC4333, "Punch",false),
            InterestItem(0xDCB20C, "Galliano",false),

            InterestItem(0xE1C0C8, "Pink Flare",false),
            InterestItem(0xE1E6D6, "Periglacial Blue",false),
            InterestItem(0xE1EAD4, "Kidnapper",false),

            InterestItem(0xE2EBED, "Mystic",false),
            InterestItem(0xE2F3EC, "Apple Green",false),
            InterestItem(0xE30B5C, "Razzmatazz",false),
            InterestItem(0xE32636, "Alizarin Crimson",false),
            InterestItem(0xE34234, "Cinnabar",false),
            InterestItem(0xE3BEBE, "Cavern Pink",false),

            InterestItem(0xED989E, "Sea Pink",false),
            InterestItem(0xEDB381, "Tacao",false),
            InterestItem(0xEDC9AF, "Desert Sand",false),
            InterestItem(0xEDCDAB, "Pancho",false),
            InterestItem(0xEDDCB1, "Chamois",false),
            InterestItem(0xEDEA99, "Primrose",false),
            InterestItem(0xEDF5DD, "Frost",false),
            InterestItem(0xEDF5F5, "Aqua Haze",false),
            InterestItem(0xEDF6FF, "Zumthor",false),

            InterestItem(0xF3E9E5, "Dawn Pink",false),
            InterestItem(0xF3EDCF, "Wheatfield",false),
            InterestItem(0xF3FB62, "Canary",false),
            InterestItem(0xF3FBD4, "Orinoco",false),

            InterestItem(0xF5C85C, "Cream Can",false),
            InterestItem(0xF5C999, "Manhattan",false),
            InterestItem(0xF5D5A0, "Maize",false),

            InterestItem(0xF8D9E9, "Cherub",false),
            InterestItem(0xF8DB9D, "Marzipan",false),

            InterestItem(0xFDE295, "Golden Glow",false),
            InterestItem(0xFDE910, "Lemon",false),
            InterestItem(0xFDF5E6, "Old Lace",false),
            InterestItem(0xFDF6D3, "Half Colonial White",false),
            InterestItem(0xFDF7AD, "Drover",false),
            InterestItem(0xFDFEB8, "Pale Prim",false),
            InterestItem(0xFDFFD5, "Cumulus",false),

            InterestItem(0xFEE5AC, "Cape Honey",false),

            InterestItem(0xFF6FFF, "Blush Pink",false),
            InterestItem(0xFF7034, "Burning Orange",false),
            InterestItem(0xFF7518, "Pumpkin",false),

            InterestItem(0xFFA6C9, "Carnation Pink",false),
            InterestItem(0xFFAB81, "Hit Pink",false),
            InterestItem(0xFFAE42, "Yellow Orange",false),
            InterestItem(0xFFB0AC, "Cornflower Lilac",false),
            InterestItem(0xFFB1B3, "Sundown",false),
            InterestItem(0xFFB31F, "My Sin",false),
            InterestItem(0xFFB555, "Texas Rose",false),
            InterestItem(0xFFB7D5, "Cotton Candy",false),
            InterestItem(0xFFB97B, "Macaroni and Cheese",false),
            InterestItem(0xFFBA00, "Selective Yellow",false),

            InterestItem(0xFFFFF0, "Ivory",false),
            InterestItem(0xFFFFFF, "White",false)
        )
    }
}
