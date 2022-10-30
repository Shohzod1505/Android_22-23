package ru.itis.kpfu.homework.model

import ru.itis.kpfu.homework.R

object FilmRepository {
    val films = arrayListOf(
        Film(
            id = 0,
            title = "Joker",
            year = 2019,
            genre = "thriller, crime, drama",
            rate = 9.1,
            cover = "https://st.kp.yandex.net/im/poster/3/4/5/kinopoisk.ru-Joker-3456851.jpg"
        ),
        Film(
            id = 1,
            title = "Spider-man 3 The Battle Within",
            year = 2007,
            genre = "action, science fiction, drama",
            rate = 8.6,
            cover = "https://st.kp.yandex.net/im/poster/2/4/2/kinopoisk.ru-Spider-Man-3-2422746.jpg"
        ),
        Film(
            id = 2,
            title = "Titanic",
            year = 1997,
            genre = "drama, romance",
            rate = 6.9,
            cover = "https://www.film.ru/sites/default/files/movies/posters/1630138-1427427.jpg"
        ),
        Film(
            id = 3,
            title = "The Notebook",
            year = 2004,
            genre = "drama, romance",
            rate = 9.5,
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1946459/4c5bd47f-995e-4047-aceb-9db8e480cda4/800x800"
        ),
        Film(
            id = 4,
            title = "Fast & Furious",
            year = 2001,
            genre = "action, crime, comedy",
            rate = 8.3,
            cover = "https://th.bing.com/th/id/OIP.qbVffTnS4-9RZzGnDawt2wHaLH?pid=ImgDet&rs=1"
        ),
        Film(
            id = 5,
            title = "Joker",
            year = 2019,
            genre = "thriller, crime, drama",
            rate = 9.1,
            cover = "https://st.kp.yandex.net/im/poster/3/4/5/kinopoisk.ru-Joker-3456851.jpg"
        ),
        Film(
            id = 6,
            title = "Spider-man 3 The Battle Within",
            year = 2007,
            genre = "action, science fiction, drama",
            rate = 8.6,
            cover = "https://st.kp.yandex.net/im/poster/2/4/2/kinopoisk.ru-Spider-Man-3-2422746.jpg"
        ),
        Film(
            id = 7,
            title = "Titanic",
            year = 1997,
            genre = "drama, romance",
            rate = 6.9,
            cover = "https://www.film.ru/sites/default/files/movies/posters/1630138-1427427.jpg"
        ),
        Film(
            id = 8,
            title = "The Notebook",
            year = 2004,
            genre = "drama, romance",
            rate = 9.5,
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1946459/4c5bd47f-995e-4047-aceb-9db8e480cda4/800x800"
        ),
        Film(
            id = 9,
            title = "Fast & Furious",
            year = 2001,
            genre = "action, crime, comedy",
            rate = 8.3,
            cover = "https://th.bing.com/th/id/OIP.qbVffTnS4-9RZzGnDawt2wHaLH?pid=ImgDet&rs=1"
        ),
        Film(
            id = 10,
            title = "Joker",
            year = 2019,
            genre = "thriller, crime, drama",
            rate = 9.1,
            cover = "https://st.kp.yandex.net/im/poster/3/4/5/kinopoisk.ru-Joker-3456851.jpg"
        ),
        Film(
            id = 11,
            title = "Spider-man 3 The Battle Within",
            year = 2007,
            genre = "action, science fiction, drama",
            rate = 8.6,
            cover = "https://st.kp.yandex.net/im/poster/2/4/2/kinopoisk.ru-Spider-Man-3-2422746.jpg"
        ),
        Film(
            id = 12,
            title = "Titanic",
            year = 1997,
            genre = "drama, romance",
            rate = 6.9,
            cover = "https://www.film.ru/sites/default/files/movies/posters/1630138-1427427.jpg"
        ),
        Film(
            id = 13,
            title = "The Notebook",
            year = 2004,
            genre = "drama, romance",
            rate = 9.5,
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1946459/4c5bd47f-995e-4047-aceb-9db8e480cda4/800x800"
        ),
        Film(
            id = 14,
            title = "Fast & Furious",
            year = 2001,
            genre = "action, crime, comedy",
            rate = 8.3,
            cover = "https://th.bing.com/th/id/OIP.qbVffTnS4-9RZzGnDawt2wHaLH?pid=ImgDet&rs=1"
        ),
        Film(
            id = 15,
            title = "Joker",
            year = 2019,
            genre = "thriller, crime, drama",
            rate = 9.1,
            cover = "https://st.kp.yandex.net/im/poster/3/4/5/kinopoisk.ru-Joker-3456851.jpg"
        ),
        Film(
            id = 16,
            title = "Spider-man 3 The Battle Within",
            year = 2007,
            genre = "action, science fiction, drama",
            rate = 8.6,
            cover = "https://st.kp.yandex.net/im/poster/2/4/2/kinopoisk.ru-Spider-Man-3-2422746.jpg"
        ),
        Film(
            id = 17,
            title = "Titanic",
            year = 1997,
            genre = "drama, romance",
            rate = 6.9,
            cover = "https://www.film.ru/sites/default/files/movies/posters/1630138-1427427.jpg"
        ),
        Film(
            id = 18,
            title = "The Notebook",
            year = 2004,
            genre = "drama, romance",
            rate = 9.5,
            cover = "https://avatars.mds.yandex.net/get-kinopoisk-image/1946459/4c5bd47f-995e-4047-aceb-9db8e480cda4/800x800"
        ),
        Film(
            id = 19,
            title = "Fast & Furious",
            year = 2001,
            genre = "action, crime, comedy",
            rate = 8.3,
            cover = "https://th.bing.com/th/id/OIP.qbVffTnS4-9RZzGnDawt2wHaLH?pid=ImgDet&rs=1"
        ),
        )

    private val filmsUI: MutableList<MainItem.FilmUiModel>
    get() = films.map {

        MainItem.FilmUiModel(
            title = it.title,
            year = it.year,
            genre = it.genre,
            rate = it.rate,
            cover = it.cover,
        )
    }.toMutableList()

    val mainItems = arrayListOf<MainItem>().apply {
        var kostil = 0
        for (index in 0 until filmsUI.size) {
            if (index % 6 == 0) {
                add(MainItem.Ads("Здесь могла быть ваша реклама"))
                kostil++
            } else {
                add(filmsUI[index - kostil])
            }
        }
    }

}