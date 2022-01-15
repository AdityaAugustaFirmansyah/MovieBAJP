package com.aditya.moviebajp.utils

import com.aditya.moviebajp.data.MovieState
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.vo.Status
import com.aditya.moviebajp.data.source.remote.response.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MovieDummy {
    fun generateMovies(): List<MovieEntity> {
        return generateMoviesResponse().results.map {
            MovieEntity(
                it.posterPath,
                it.backdropPath,
                it.overview,
                it.releaseDate,
                it.id,
                it.originalTitle,
                it.title,
                it.originalLanguage,
                it.popularity,
                false,
                it.voteCount,
                it.adult,
                it.voteAverage
            )
        }
    }

    fun generateTv(): List<TvEntity> {
        return generateTvResponse().results.map {
            TvEntity(
                it.posterPath,
                it.backdropPath,
                it.overview,
                it.firstAirDate,
                it.id,
                it.originalName,
                it.name,
                it.originalLanguage,
                false,
                it.popularity,
                it.voteCount,
                it.voteAverage
            )
        }
    }

    fun getDetailMovie(): MovieEntity {
        val movie = generateDetailMovieResponse()
        return MovieEntity(
            movie.posterPath,
            movie.backdropPath,
            movie.overview,
            movie.releaseDate,
            movie.id,
            movie.originalTitle,
            movie.title,
            movie.originalLanguage,
            movie.popularity,
            false,
            movie.voteCount,
            movie.adult,
            movie.voteAverage
        )
    }

    fun getDetailTv(): TvEntity {
        val tv = generateDetailTvResponse()
        return TvEntity(
            tv.posterPath,
            tv.backdropPath,
            tv.overview,
            tv.firstAirDate,
            tv.id,
            tv.originalName,
            tv.name,
            tv.originalLanguage,
            false,
            tv.popularity,
            tv.voteCount,tv.voteAverage
        )
    }

    fun generateMoviesResponse(): MovieResponse {
        val results = Gson().fromJson<List<MovieData>>(
            DATA_MOVIE, object : TypeToken<List<MovieData>>() {}.type)
        return MovieResponse(results, "")
    }

    fun generateTvResponse(): TvResponse {
        val results = Gson().fromJson<List<DataTv>>(
            DATA_TV, object : TypeToken<List<DataTv>>() {}.type)
        return TvResponse(results, "")
    }

    fun generateDetailTvResponse(): DetailTvResponse {
        val tv = generateTv()[0]
        return DetailTvResponse(
            tv.posterPath,
            tv.backdropPath,
            tv.overview,
            tv.firstAirDate,
            tv.id,
            tv.originalName,
            tv.name,
            tv.originalLanguage,
            tv.popularity,
            tv.voteCount,
            tv.voteAverage
        )
    }

    fun generateDetailMovieResponse(): DetailMovieResponse {
        val movie = generateMovies()[0]
        return DetailMovieResponse(
            movie.posterPath,
            movie.backdropPath,
            movie.overview,
            movie.releaseDate,
            movie.id,
            movie.originalTitle,
            movie.title,
            movie.originalLanguage,
            movie.popularity,
            movie.voteCount,
            movie.adult,
            movie.voteAverage
        )
    }

    fun generateMoviesState(): MovieState {
        return MovieState(Status.SUCCESS, generateMovies(),"")
    }

    private const val DATA_MOVIE = "[{\n" +
            "  \"poster_path\": \"/xFw9RXKZDvevAGocgBK0zteto4U.jpg\",\n" +
            "  \"adult\": false,\n" +
            "  \"overview\": \"From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.\",\n" +
            "  \"release_date\": \"2016-08-03\",\n" +
            "  \"genre_ids\": [\n" +
            "    14,\n" +
            "    28,\n" +
            "    80\n" +
            "  ],\n" +
            "  \"genre_name\": [\"fantasy\",\"Action\",\"Crime\"],\n" +
            "  \"id\": 297761,\n" +
            "  \"original_title\": \"Suicide Squad\",\n" +
            "  \"original_language\": \"en\",\n" +
            "  \"title\": \"Suicide Squad\",\n" +
            "  \"backdrop_path\": \"/sMRwI5trKI6qhxYcjPgGghmPBef.jpg\",\n" +
            "  \"popularity\": 48.261451,\n" +
            "  \"vote_count\": 1466,\n" +
            "  \"video\": false,\n" +
            "  \"vote_average\": 5.91\n" +
            "},\n" +
            "  {\n" +
            "    \"poster_path\": \"/xA7N41glw17MBQtcWSm2eBlBRuG.jpg\",\n" +
            "    \"adult\": false,\n" +
            "    \"overview\": \"The most dangerous former operative of the CIA is drawn out of hiding to uncover hidden truths about his past.\",\n" +
            "    \"release_date\": \"2016-07-27\",\n" +
            "    \"genre_ids\": [\n" +
            "      28,\n" +
            "      53\n" +
            "    ],\n" +
            "    \"genre_name\": [\"Action\",\"Thriller\"],\n" +
            "    \"id\": 324668,\n" +
            "    \"original_title\": \"Jason Bourne\",\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"title\": \"Jason Bourne\",\n" +
            "    \"backdrop_path\": \"/7mHeyU0a538bgguOeF57I8ZroSk.jpg\",\n" +
            "    \"popularity\": 30.690177,\n" +
            "    \"vote_count\": 649,\n" +
            "    \"video\": false,\n" +
            "    \"vote_average\": 5.25\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/A81kDB6a1K86YLlcOtZB27jriJh.jpg\",\n" +
            "    \"adult\": false,\n" +
            "    \"overview\": \"One year after outwitting the FBI and winning the public’s adulation with their mind-bending spectacles, the Four Horsemen resurface only to find themselves face to face with a new enemy who enlists them to pull off their most dangerous heist yet.\",\n" +
            "    \"release_date\": \"2016-06-02\",\n" +
            "    \"genre_ids\": [\n" +
            "      28,\n" +
            "      12,\n" +
            "      35,\n" +
            "      80,\n" +
            "      9648,\n" +
            "      53\n" +
            "    ],\n" +
            "    \"genre_name\": [\"Action\",\"Adventure\",\"Comedy\",\"Crime\",\"Mystery\",\"Thriller\"],\n" +
            "    \"id\": 291805,\n" +
            "    \"original_title\": \"Now You See Me 2\",\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"title\": \"Now You See Me 2\",\n" +
            "    \"backdrop_path\": \"/1sWDuBwQcETRrgSV7V16qtqFfnB.jpg\",\n" +
            "    \"popularity\": 29.737342,\n" +
            "    \"vote_count\": 684,\n" +
            "    \"video\": false,\n" +
            "    \"vote_average\": 6.64\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/tM0hpWw3GONam6TKcMMciecHjhT.jpg\",\n" +
            "    \"adult\": false,\n" +
            "    \"overview\": \"A recently cheated on married woman falls for a younger man who has moved in next door, but their torrid affair soon takes a dangerous turn.\",\n" +
            "    \"release_date\": \"2015-01-23\",\n" +
            "    \"genre_ids\": [\n" +
            "      53\n" +
            "    ],\n" +
            "    \"genre_name\": [\"Thriller\"],\n" +
            "    \"id\": 241251,\n" +
            "    \"original_title\": \"The Boy Next Door\",\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"title\": \"The Boy Next Door\",\n" +
            "    \"backdrop_path\": \"/3jJzHDCosJQc2dzXJadYnOPdHAc.jpg\",\n" +
            "    \"popularity\": 22.279864,\n" +
            "    \"vote_count\": 628,\n" +
            "    \"video\": false,\n" +
            "    \"vote_average\": 4.13\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/tOEOlmLP71IojeJ91dyJ9Nsb8O8.jpg\",\n" +
            "    \"adult\": false,\n" +
            "    \"overview\": \"An orphan boy is raised in the Jungle with the help of a pack of wolves, a bear and a black panther.\",\n" +
            "    \"release_date\": \"2016-04-07\",\n" +
            "    \"genre_ids\": [\n" +
            "      12,\n" +
            "      18,\n" +
            "      14\n" +
            "    ],\n" +
            "    \"genre_name\": [\"Adventure\",\"Drama\",\"Fantasy\"],\n" +
            "    \"id\": 278927,\n" +
            "    \"original_title\": \"The Jungle Book\",\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"title\": \"The Jungle Book\",\n" +
            "    \"backdrop_path\": \"/tB2w4m0rW62MTufTjRj0gFLMVBP.jpg\",\n" +
            "    \"popularity\": 21.104822,\n" +
            "    \"vote_count\": 1085,\n" +
            "    \"video\": false,\n" +
            "    \"vote_average\": 6.42\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/3FUJT82YKY1EJ1dmunQhW5PUZAT.jpg\",\n" +
            "    \"adult\": false,\n" +
            "    \"overview\": \"Arthur Bishop thought he had put his murderous past behind him when his most formidable foe kidnaps the love of his life. Now he is forced to travel the globe to complete three impossible assassinations, and do what he does best, make them look like accidents.\",\n" +
            "    \"release_date\": \"2016-08-25\",\n" +
            "    \"genre_ids\": [\n" +
            "      80,\n" +
            "      28,\n" +
            "      53\n" +
            "    ],\n" +
            "    \"genre_name\": [\"Crime\",\"Action\",\"Thriller\"],\n" +
            "    \"id\": 278924,\n" +
            "    \"original_title\": \"Mechanic: Resurrection\",\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"title\": \"Mechanic: Resurrection\",\n" +
            "    \"backdrop_path\": \"/4CCvDWKtmFms4tSTXeawA19sheU.jpg\",\n" +
            "    \"popularity\": 20.375179,\n" +
            "    \"vote_count\": 119,\n" +
            "    \"video\": false,\n" +
            "    \"vote_average\": 4.59\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/5UsK3grJvtQrtzEgqNlDljJW96w.jpg\",\n" +
            "    \"adult\": false,\n" +
            "    \"overview\": \"Fearing the actions of a god-like Super Hero left unchecked, Gotham City’s own formidable, forceful vigilante takes on Metropolis’s most revered, modern-day savior, while the world wrestles with what sort of hero it really needs. And with Batman and Superman at war with one another, a new threat quickly arises, putting mankind in greater danger than it’s ever known before.\",\n" +
            "    \"release_date\": \"2016-03-23\",\n" +
            "    \"genre_ids\": [\n" +
            "      28,\n" +
            "      12,\n" +
            "      14\n" +
            "    ],\n" +
            "    \"genre_name\": [\"Action\",\"Adventure\",\"Fantasy\"],\n" +
            "    \"id\": 209112,\n" +
            "    \"original_title\": \"Batman v Superman: Dawn of Justice\",\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"title\": \"Batman v Superman: Dawn of Justice\",\n" +
            "    \"backdrop_path\": \"/dasEWdFNSobYvY8xWcfz3z02kTu.jpg\",\n" +
            "    \"popularity\": 19.413721,\n" +
            "    \"vote_count\": 3486,\n" +
            "    \"video\": false,\n" +
            "    \"vote_average\": 5.52\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/8tZYtuWezp8JbcsvHYO0O46tFbo.jpg\",\n" +
            "    \"adult\": false,\n" +
            "    \"overview\": \"An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and most everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.\",\n" +
            "    \"release_date\": \"2015-05-13\",\n" +
            "    \"genre_ids\": [\n" +
            "      28,\n" +
            "      12,\n" +
            "      878,\n" +
            "      53\n" +
            "    ],\n" +
            "    \"genre_name\": [\"action\",\"Adventure\",\"Science Fiction\",\"Thriller\"],\n" +
            "    \"id\": 76341,\n" +
            "    \"original_title\": \"Mad Max: Fury Road\",\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"title\": \"Mad Max: Fury Road\",\n" +
            "    \"backdrop_path\": \"/nlCHUWjY9XWbuEUQauCBgnY8ymF.jpg\",\n" +
            "    \"popularity\": 18.797187,\n" +
            "    \"vote_count\": 5236,\n" +
            "    \"video\": false,\n" +
            "    \"vote_average\": 7.26\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/rAGiXaUfPzY7CDEyNKUofk3Kw2e.jpg\",\n" +
            "    \"adult\": false,\n" +
            "    \"overview\": \"Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies.\",\n" +
            "    \"release_date\": \"2016-04-27\",\n" +
            "    \"genre_ids\": [\n" +
            "      28,\n" +
            "      53,\n" +
            "      878\n" +
            "    ],\n" +
            "    \"genre_name\": [\"action\",\"Thriller\",\"Science Fiction\"],\n" +
            "    \"id\": 271110,\n" +
            "    \"original_title\": \"Captain America: Civil War\",\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"title\": \"Captain America: Civil War\",\n" +
            "    \"backdrop_path\": \"/7FWlcZq3r6525LWOcvO9kNWurN1.jpg\",\n" +
            "    \"popularity\": 16.733457,\n" +
            "    \"vote_count\": 2570,\n" +
            "    \"video\": false,\n" +
            "    \"vote_average\": 6.93\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/rhr4y79GpxQF9IsfJItRXVaoGs4.jpg\",\n" +
            "    \"adult\": false,\n" +
            "    \"overview\": \"Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.\",\n" +
            "    \"release_date\": \"2015-06-09\",\n" +
            "    \"genre_ids\": [\n" +
            "      28,\n" +
            "      12,\n" +
            "      878,\n" +
            "      53\n" +
            "    ],\n" +
            "    \"genre_name\": [\"action\",\"Adventure\",\"Science Fiction\",\"Thriller\"],\n" +
            "    \"id\": 135397,\n" +
            "    \"original_title\": \"Jurassic World\",\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"title\": \"Jurassic World\",\n" +
            "    \"backdrop_path\": \"/yOCRqvrRrxbs5FYq2pX1KtLJwmR.jpg\",\n" +
            "    \"popularity\": 15.930056,\n" +
            "    \"vote_count\": 4934,\n" +
            "    \"video\": false,\n" +
            "    \"vote_average\": 6.59\n" +
            "  }]"

    private const val DATA_TV = "[\n" +
            "  {\n" +
            "    \"poster_path\": \"/aUPbHiLS3hCHKjtLsncFa9g0viV.jpg\",\n" +
            "    \"popularity\": 47.432451,\n" +
            "    \"id\": 31917,\n" +
            "    \"backdrop_path\": \"/ypLoTftyF5EpGBxJas4PThIdiU4.jpg\",\n" +
            "    \"vote_average\": 5.04,\n" +
            "    \"overview\": \"Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \\\"A\\\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.\",\n" +
            "    \"first_air_date\": \"2010-06-08\",\n" +
            "    \"origin_country\": [\n" +
            "      \"US\"\n" +
            "    ],\n" +
            "    \"genre_ids\": [\n" +
            "      18,\n" +
            "      9648\n" +
            "    ],\n" +
            "    \"genre_name\": [\n" +
            "      \"Drama\",\n" +
            "      \"Mystery\"\n" +
            "    ],\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"vote_count\": 133,\n" +
            "    \"name\": \"Pretty Little Liars\",\n" +
            "    \"original_name\": \"Pretty Little Liars\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/oKIBhzZzDX07SoE2bOLhq2EE8rf.jpg\",\n" +
            "    \"popularity\": 37.882356,\n" +
            "    \"id\": 62560,\n" +
            "    \"backdrop_path\": \"/1SEVAgbaah9wE5xoLt4qWPMBdpL.jpg\",\n" +
            "    \"vote_average\": 7.5,\n" +
            "    \"overview\": \"A contemporary and culturally resonant drama about a young programmer, Elliot, who suffers from a debilitating anti-social disorder and decides that he can only connect to people by hacking them. He wields his skills as a weapon to protect the people that he cares about. Elliot will find himself in the intersection between a cybersecurity firm he works for and the underworld organizations that are recruiting him to bring down corporate America.\",\n" +
            "    \"first_air_date\": \"2015-05-27\",\n" +
            "    \"origin_country\": [\n" +
            "      \"US\"\n" +
            "    ],\n" +
            "    \"genre_ids\": [\n" +
            "      80,\n" +
            "      18\n" +
            "    ],\n" +
            "    \"genre_name\": [\n" +
            "      \"Crime\",\n" +
            "      \"Drama\"\n" +
            "    ],\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"vote_count\": 287,\n" +
            "    \"name\": \"Mr. Robot\",\n" +
            "    \"original_name\": \"Mr. Robot\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/vQiryp6LioFxQThywxbC6TuoDjy.jpg\",\n" +
            "    \"popularity\": 34.376914,\n" +
            "    \"id\": 37680,\n" +
            "    \"backdrop_path\": \"/eVV2A3PYJLmHdXpiveiU6Lk7jbV.jpg\",\n" +
            "    \"vote_average\": 6.94,\n" +
            "    \"overview\": \"While running from a drug deal gone bad, Mike Ross, a brilliant young college-dropout, slips into a job interview with one of New York City's best legal closers, Harvey Specter. Tired of cookie-cutter law school grads, Harvey takes a gamble by hiring Mike on the spot after he recognizes his raw talent and photographic memory. Mike and Harvey are a winning team. Even though Mike is a genius, he still has a lot to learn about law. And while Harvey may seem like an emotionless, cold-blooded shark, Mike's sympathy and concern for their cases and clients will help remind Harvey why he went into law in the first place. Mike's other allies in the office include the firm's best paralegal Rachel and Harvey's no-nonsense assistant Donna to help him serve justice. Proving to be an irrepressible duo and invaluable to the practice, Mike and Harvey must keep their secret from everyone including managing partner Jessica and Harvey's arch nemesis Louis, who seems intent on making Mike's life as difficult as possible.\",\n" +
            "    \"first_air_date\": \"2011-06-23\",\n" +
            "    \"origin_country\": [\n" +
            "      \"US\"\n" +
            "    ],\n" +
            "    \"genre_ids\": [\n" +
            "      18\n" +
            "    ],\n" +
            "    \"genre_name\": [\n" +
            "      \"Drama\"\n" +
            "    ],\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"vote_count\": 161,\n" +
            "    \"name\": \"Suits\",\n" +
            "    \"original_name\": \"Suits\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg\",\n" +
            "    \"popularity\": 29.780826,\n" +
            "    \"id\": 1399,\n" +
            "    \"backdrop_path\": \"/suopoADq0k8YZr4dQXcU6pToj6s.jpg\",\n" +
            "    \"vote_average\": 7.91,\n" +
            "    \"overview\": \"Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.\",\n" +
            "    \"first_air_date\": \"2011-04-17\",\n" +
            "    \"origin_country\": [\n" +
            "      \"US\"\n" +
            "    ],\n" +
            "    \"genre_ids\": [\n" +
            "      10765,\n" +
            "      10759,\n" +
            "      18\n" +
            "    ],\n" +
            "    \"genre_name\": [\n" +
            "      \"Sci-Fi & Fantasy\",\n" +
            "      \"Action & Adventure\",\n" +
            "      \"Drama\"\n" +
            "    ],\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"vote_count\": 1172,\n" +
            "    \"name\": \"Game of Thrones\",\n" +
            "    \"original_name\": \"Game of Thrones\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/w21lgYIi9GeUH5dO8l3B9ARZbCB.jpg\",\n" +
            "    \"popularity\": 25.172397,\n" +
            "    \"id\": 1402,\n" +
            "    \"backdrop_path\": \"/eUMwG5vXg4ovEUvXLAFgrr4bQvp.jpg\",\n" +
            "    \"vote_average\": 7.38,\n" +
            "    \"overview\": \"Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.\",\n" +
            "    \"first_air_date\": \"2010-10-31\",\n" +
            "    \"origin_country\": [\n" +
            "      \"US\"\n" +
            "    ],\n" +
            "    \"genre_ids\": [\n" +
            "      10759,\n" +
            "      18\n" +
            "    ],\n" +
            "    \"genre_name\": [\n" +
            "      \"Action & Adventure\",\n" +
            "      \"Drama\",\n" +
            "      \"Sci-Fi & Fantasy\"\n" +
            "    ],\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"vote_count\": 599,\n" +
            "    \"name\": \"The Walking Dead\",\n" +
            "    \"original_name\": \"The Walking Dead\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/ooBGRQBdbGzBxAVfExiO8r7kloA.jpg\",\n" +
            "    \"popularity\": 24.933765,\n" +
            "    \"id\": 1418,\n" +
            "    \"backdrop_path\": \"/x4DO7mY7usT8BwLiHTUsYT7EKbc.jpg\",\n" +
            "    \"vote_average\": 7.21,\n" +
            "    \"overview\": \"The Big Bang Theory is centered on five characters living in Pasadena, California: roommates Leonard Hofstadter and Sheldon Cooper; Penny, a waitress and aspiring actress who lives across the hall; and Leonard and Sheldon's equally geeky and socially awkward friends and co-workers, mechanical engineer Howard Wolowitz and astrophysicist Raj Koothrappali. The geekiness and intellect of the four guys is contrasted for comic effect with Penny's social skills and common sense.\",\n" +
            "    \"first_air_date\": \"2007-09-24\",\n" +
            "    \"origin_country\": [\n" +
            "      \"US\"\n" +
            "    ],\n" +
            "    \"genre_ids\": [\n" +
            "      35\n" +
            "    ],\n" +
            "    \"genre_name\": [\n" +
            "      \"Comedy\"\n" +
            "    ],\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"vote_count\": 597,\n" +
            "    \"name\": \"The Big Bang Theory\",\n" +
            "    \"original_name\": \"The Big Bang Theory\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/sz4zF5z9zyFh8Z6g5IQPNq91cI7.jpg\",\n" +
            "    \"popularity\": 22.509632,\n" +
            "    \"id\": 57243,\n" +
            "    \"backdrop_path\": \"/sRfl6vyzGWutgG0cmXmbChC4iN6.jpg\",\n" +
            "    \"vote_average\": 7.16,\n" +
            "    \"overview\": \"The Doctor looks and seems human. He's handsome, witty, and could be mistaken for just another man in the street. But he is a Time Lord: a 900 year old alien with 2 hearts, part of a gifted civilization who mastered time travel. The Doctor saves planets for a living – more of a hobby actually, and he's very, very good at it. He's saved us from alien menaces and evil from before time began – but just who is he?\",\n" +
            "    \"first_air_date\": \"2005-03-26\",\n" +
            "    \"origin_country\": [\n" +
            "      \"GB\"\n" +
            "    ],\n" +
            "    \"genre_ids\": [\n" +
            "      28,\n" +
            "      12,\n" +
            "      18,\n" +
            "      878\n" +
            "    ],\n" +
            "    \"genre_name\": [\n" +
            "      \"Action & Adventure\",\n" +
            "      \"Drama\",\n" +
            "      \"Sci-Fi & Fantasy\"\n" +
            "    ],\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"vote_count\": 251,\n" +
            "    \"name\": \"Doctor Who\",\n" +
            "    \"original_name\": \"Doctor Who\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/upmd11A2ZaZEG1j6wRGdD4B5cxs.jpg\",\n" +
            "    \"popularity\": 21.734193,\n" +
            "    \"id\": 62286,\n" +
            "    \"backdrop_path\": \"/zrMCOQnzIgGyBtGCdMPom9bOw0r.jpg\",\n" +
            "    \"vote_average\": 6.23,\n" +
            "    \"overview\": \"What did the world look like as it was transforming into the horrifying apocalypse depicted in \\\"The Walking Dead\\\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.\",\n" +
            "    \"first_air_date\": \"2015-08-23\",\n" +
            "    \"origin_country\": [\n" +
            "      \"US\"\n" +
            "    ],\n" +
            "    \"genre_ids\": [\n" +
            "      18,\n" +
            "      27\n" +
            "    ],\n" +
            "    \"genre_name\": [\n" +
            "      \"Action & Adventure\",\n" +
            "      \"Drama\"\n" +
            "    ],\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"vote_count\": 160,\n" +
            "    \"name\": \"Fear the Walking Dead\",\n" +
            "    \"original_name\": \"Fear the Walking Dead\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/ggFHVNu6YYI5L9pCfOacjizRGt.jpg\",\n" +
            "    \"popularity\": 21.173765,\n" +
            "    \"id\": 1396,\n" +
            "    \"backdrop_path\": \"/84XPpjGvxNyExjSuLQe0SzioErt.jpg\",\n" +
            "    \"vote_average\": 8.1,\n" +
            "    \"overview\": \"Breaking Bad is an American crime drama television series created and produced by Vince Gilligan. Set and produced in Albuquerque, New Mexico, Breaking Bad is the story of Walter White, a struggling high school chemistry teacher who is diagnosed with inoperable lung cancer at the beginning of the series. He turns to a life of crime, producing and selling methamphetamine, in order to secure his family's financial future before he dies, teaming with his former student, Jesse Pinkman. Heavily serialized, the series is known for positioning its characters in seemingly inextricable corners and has been labeled a contemporary western by its creator.\",\n" +
            "    \"first_air_date\": \"2008-01-19\",\n" +
            "    \"origin_country\": [\n" +
            "      \"US\"\n" +
            "    ],\n" +
            "    \"genre_ids\": [\n" +
            "      18\n" +
            "    ],\n" +
            "    \"genre_name\": [\n" +
            "      \"Drama\"\n" +
            "    ],\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"vote_count\": 690,\n" +
            "    \"name\": \"Breaking Bad\",\n" +
            "    \"original_name\": \"Breaking Bad\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"poster_path\": \"/iiCY2QIGSnmtVkIdjkKAfwDs0KF.jpg\",\n" +
            "    \"popularity\": 19.140976,\n" +
            "    \"id\": 2190,\n" +
            "    \"backdrop_path\": \"/41neXsH222hV2zrsTyw8h7javon.jpg\",\n" +
            "    \"vote_average\": 7.63,\n" +
            "    \"overview\": \"Follows the misadventures of four irreverent grade-schoolers in the quiet, dysfunctional town of South Park, Colorado.\",\n" +
            "    \"first_air_date\": \"1997-08-13\",\n" +
            "    \"origin_country\": [\n" +
            "      \"US\"\n" +
            "    ],\n" +
            "    \"genre_ids\": [\n" +
            "      35,\n" +
            "      16\n" +
            "    ],\n" +
            "    \"genre_name\": [\n" +
            "      \"Animation\",\n" +
            "      \"Comedy\"\n" +
            "    ],\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"vote_count\": 153,\n" +
            "    \"name\": \"South Park\",\n" +
            "    \"original_name\": \"South Park\"\n" +
            "  }\n" +
            "]"
}