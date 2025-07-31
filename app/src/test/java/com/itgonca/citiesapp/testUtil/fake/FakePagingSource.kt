package com.itgonca.citiesapp.testUtil.fake

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itgonca.citiesapp.data.local.db.entity.CityEntity

class FakePagingSource(private val data: List<CityEntity>) : PagingSource<Int, CityEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CityEntity> {
        return LoadResult.Page(data = data, prevKey = null, nextKey = null)
    }

    override fun getRefreshKey(state: PagingState<Int, CityEntity>): Int? = 0
}