package ru.itis.kpfu.homework.model

import ru.itis.kpfu.homework.R

object SongRepository {

    val songList: List<Song> = arrayListOf(
        Song(0, "Akhi Anta Hurrun", "My brother, you are free",
            R.drawable.cover1,
            R.raw.akhi_anta_hurrun
        ),
        Song(1, "Mawlay", "Mohammed Obaid", R.drawable.cover2, R.raw.mawlay),
        Song(2, "Baraka Allahu Lakuma", "Maher Zain", R.drawable.cover3, R.raw.baraka_allahu_lakuma),
        Song(3, "El Azhar - El Djihad", "Oculus Club Music",
            R.drawable.cover4,
            R.raw.el_azhar_el_djihad
        ),
        Song(4, "Untitled", "Alafasy", R.drawable.cover5, R.raw.untitled),
        Song(5, "Najwa Farouk Mawjou3 Galbi Cover", "Unknown",
            R.drawable.cover6,
            R.raw.najwa_farouk_mawjou3_galbi_cover
        ),
    )

}