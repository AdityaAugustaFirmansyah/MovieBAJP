package com.aditya.moviebajp.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.aditya.moviebajp.data.source.MovieDataSource
import com.aditya.moviebajp.data.source.local.LocalDataSource
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.data.source.remote.ApiResponse
import com.aditya.moviebajp.data.source.remote.NetworkBoundResource
import com.aditya.moviebajp.data.source.remote.RemoteDataSource
import com.aditya.moviebajp.data.source.remote.response.DetailMovieResponse
import com.aditya.moviebajp.data.source.remote.response.DetailTvResponse
import com.aditya.moviebajp.data.source.remote.response.MovieResponse
import com.aditya.moviebajp.data.source.remote.response.TvResponse
import com.aditya.moviebajp.utils.AppExecutors
import com.aditya.moviebajp.utils.SortUtils
import com.aditya.moviebajp.vo.Resource

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource,private val localDataSource: LocalDataSource,private val appExecutors: AppExecutors) :
    MovieDataSource {

    override fun getAllMovie(sort:String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, MovieResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAllMovie(SortUtils.getSortedQueryMovie(sort)),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                return remoteDataSource.getAllMovie()
            }

            override fun saveCallResult(data: MovieResponse) {
                val movieList = mutableListOf<MovieEntity>()
                for (i in data.results) {
                    val movie = MovieEntity(
                        i.posterPath,
                        i.backdropPath,
                        i.overview,
                        i.releaseDate,
                        i.id,
                        i.originalTitle,
                        i.title,
                        i.originalLanguage,
                        i.popularity,
                        false,
                        i.voteCount,
                        i.adult,
                        i.voteAverage
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getAllTv(sort: String): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, TvResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(4)
                    .setInitialLoadSizeHint(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAllTv(
                        SortUtils.getSortedQueryTv(
                            sort
                        )
                    ), config
                ).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<TvResponse>> {
                return remoteDataSource.getAllTv()
            }

            override fun saveCallResult(data: TvResponse) {
                val tvs = mutableListOf<TvEntity>()
                for (i in data.results) {
                    val tv = TvEntity(
                        i.posterPath,
                        i.backdropPath,
                        i.overview,
                        i.firstAirDate,
                        i.id,
                        i.originalName,
                        i.name,
                        i.originalLanguage,
                        false,
                        i.popularity,
                        i.voteCount,
                        i.voteAverage
                    )
                    tvs.add(tv)
                }
                localDataSource.insertTvs(tvs)
            }

        }.asLiveData()
    }

    override fun getMovieById(id: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<MovieEntity> {
                return localDataSource.getMovie(id.toInt())
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> {
                return remoteDataSource.getMovieById(id)
            }

            override fun saveCallResult(data: DetailMovieResponse) {
                localDataSource.insertMovies(
                    mutableListOf(
                        MovieEntity(
                            data.posterPath,
                            data.backdropPath,
                            data.overview,
                            data.releaseDate,
                            data.id,
                            data.originalTitle,
                            data.title,
                            data.originalLanguage,
                            data.popularity,
                            false,
                            data.voteCount,
                            data.adult,
                            data.voteAverage
                        )
                    )
                )
            }

        }.asLiveData()
    }

    override fun getTvById(id: String): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, DetailTvResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<TvEntity> {
                return localDataSource.getTv(id.toInt())
            }

            override fun shouldFetch(data: TvEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailTvResponse>> {
                return remoteDataSource.getTvById(id)
            }

            override fun saveCallResult(data: DetailTvResponse) {
                localDataSource.insertTvs(
                    mutableListOf(
                        TvEntity(
                            data.posterPath,
                            data.backdropPath,
                            data.overview,
                            data.firstAirDate,
                            data.id,
                            data.originalName,
                            data.name,
                            data.originalLanguage,
                            false,
                            data.popularity,
                            data.voteCount,
                            data.voteAverage
                        )
                    )
                )
            }

        }.asLiveData()
    }

    override fun updateMovie(movieEntity: MovieEntity) {
        appExecutors.diskIO().execute {
            localDataSource.updateMovie(movieEntity)
        }
    }

    override fun updateTv(tvEntity: TvEntity) {
        appExecutors.diskIO().execute {
            localDataSource.updateTv(tvEntity)
        }
    }

    override fun getAllMovieFavourite(): LiveData<List<MovieEntity>> {
        return localDataSource.getAllMovieFavourite()
    }

    override fun getAllTvFavourite(): LiveData<List<TvEntity>> {
        return localDataSource.getAllTvFavourite()
    }
}