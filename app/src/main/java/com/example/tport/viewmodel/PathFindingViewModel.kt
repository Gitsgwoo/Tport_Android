package com.example.tport.ui

import androidx.lifecycle.*
import com.example.tport.network.dto.previous.Path0
import com.example.tport.network.PathDao
import com.example.tport.network.dto.previous.MethodDTO
import kotlinx.coroutines.launch

class PathFindingViewModel(
    private val pathDao: PathDao,
): ViewModel() {

    val pathList: LiveData<List<Path0>> = pathDao.getPathList().asLiveData()
    lateinit var searchedPathList: LiveData<List<Path0>>
    lateinit var tportSearchedPathList: LiveData<List<Path0>>

    // path_table
    fun retrievePath(id: Int): LiveData<Path0>{
        return pathDao.getPath(id).asLiveData()
    }

    fun getSearchedPathList(origin: String, destination: String, searchTime: String){
        searchedPathList = pathDao.getSearchedPathList(origin, destination, searchTime).asLiveData()
    }

    fun getTportSearchedPathList(origin: String, destination: String, searchTime: String){
        tportSearchedPathList = pathDao.getTportSearchedPathList(origin, destination, searchTime).asLiveData()
    }

    fun getMethodList(path: Path0): List<MethodDTO>{
        val method1 = MethodDTO(1, path.method1, path.startPoint1, path.endPoint1, path.travelTime1)
        val method2 = MethodDTO(2, path.method2, path.startPoint2, path.endPoint2, path.travelTime2)
        val method3 = MethodDTO(3, path.method3, path.startPoint3, path.endPoint3, path.travelTime3)
        val method4 = MethodDTO(4, path.method4, path.startPoint4, path.endPoint4, path.travelTime4)
        val method5 = MethodDTO(5, path.method5, path.startPoint5, path.endPoint5, path.travelTime5)
        val method6 = MethodDTO(6, path.method6, path.startPoint6, path.endPoint6, path.travelTime6)

        val allMethodList: List<MethodDTO> = listOf(method1, method2, method3, method4, method5, method6)
        val methodList: MutableList<MethodDTO> = mutableListOf()
        for (i in allMethodList) {
            if (i.methodName != "NoneNone" && i.startPoint != ""){
                if(i.methodName == path.waitingBus){
                    i.waitingNum = path.waitingNum
                    i.waitingTime = path.waitingTime
                    i.emptyNum = path.emptyNum
                    i.reservedNum = path.reservedNum
                }
                methodList.add(i)
            }
        }
        return methodList
    }

    fun updateReservedNum(id: Int) {
        viewModelScope.launch {
            pathDao.updateReservedNum(id)
        }
    }
}

class PathFindingViewModelFactory(private val pathDao: PathDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PathFindingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PathFindingViewModel(pathDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}