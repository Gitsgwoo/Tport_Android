package com.example.tport.util

import android.content.Context
import com.example.tport.R
import com.example.tport.data.Path
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import kotlin.math.max


class ExtractData(private val context: Context) {

    fun extractDataFromXlsx(): List<Path> {
        // initialize asset manager
        val assetManager = context.assets
        // open excel file name as data.xlsx
        val myInput = assetManager.open("data_1.xlsx")
        // Create a workbook using the File System
        val workbook = XSSFWorkbook(myInput)
        // Get the first sheet from workbook
        val sheet: Sheet = workbook.getSheetAt(0)

        val dataList: MutableList<Path> = mutableListOf()

        for (rowIndex in 1 until sheet.physicalNumberOfRows) {
            val row: Row = sheet.getRow(rowIndex)
            val id = 0
            val origin = row.getCell(1).stringCellValue
            val destination = row.getCell(2).stringCellValue
            val searchTime = row.getCell(3).stringCellValue
            val fare = row.getCell(4).stringCellValue
            val naverTravelTime = row.getCell(5).numericCellValue.toInt()
            val tportTravelTime = row.getCell(6).numericCellValue.toInt()
            val naverArrivalTime = row.getCell(7).numericCellValue.toInt()
            val tportArrivalTime = row.getCell(8).numericCellValue.toInt()
            val method1 = row.getCell(9).stringCellValue
            val startPoint1 = row.getCell(10).stringCellValue
            val endPoint1 = row.getCell(11).stringCellValue
            val travelTime1 = row.getCell(12).stringCellValue
            val method2 = row.getCell(13).stringCellValue
            val startPoint2 = row.getCell(14).stringCellValue
            val endPoint2 = row.getCell(15).stringCellValue
            val travelTime2 = row.getCell(16).stringCellValue
            val method3 = row.getCell(17).stringCellValue
            val startPoint3 = row.getCell(18).stringCellValue
            val endPoint3 = row.getCell(19).stringCellValue
            val travelTime3 = row.getCell(20).stringCellValue
            val method4 = row.getCell(21).stringCellValue
            val startPoint4 = row.getCell(22).stringCellValue
            val endPoint4 = row.getCell(23).stringCellValue
            val travelTime4 = row.getCell(24).stringCellValue
            val method5 = row.getCell(25).stringCellValue
            val startPoint5 = row.getCell(26).stringCellValue
            val endPoint5 = row.getCell(27).stringCellValue
            val travelTime5 = row.getCell(28).stringCellValue
            val method6 = row.getCell(29).stringCellValue
            val startPoint6 = row.getCell(30).stringCellValue
            val endPoint6 = row.getCell(31).stringCellValue
            val travelTime6 = row.getCell(32).stringCellValue
            val waitingTime = row.getCell(33).numericCellValue.toInt()
            val waitingBus = "버스" + row.getCell(34).stringCellValue
            val emptyNum = row.getCell(35).numericCellValue.toInt()
            val waitingNum = row.getCell(36).numericCellValue.toInt()

            val path = Path(id = id, origin = origin, destination = destination, searchTime = searchTime, fare = fare, naverTravelTime = naverTravelTime,
            tportTravelTime = tportTravelTime, naverArrivalTime = naverArrivalTime, tportArrivalTime = tportArrivalTime, method1 = method1, method2 = method2,
            method3 = method3, method4 =  method4, method5 = method5, method6 = method6, startPoint1 = startPoint1, startPoint2 = startPoint2, startPoint3 = startPoint3,
            startPoint4 = startPoint4, startPoint5 = startPoint5, startPoint6 = startPoint6, endPoint1 = endPoint1, endPoint2 = endPoint2, endPoint3 = endPoint3,
            endPoint4 = endPoint4, endPoint5 = endPoint5, endPoint6 = endPoint6, travelTime1 = travelTime1, travelTime2 = travelTime2, travelTime3 = travelTime3,
            travelTime4 = travelTime4, travelTime5 = travelTime5, travelTime6 = travelTime6, waitingTime = waitingTime, waitingBus = waitingBus, emptyNum = emptyNum,
            waitingNum = waitingNum)

            dataList.add(path)
        }

        return dataList
    }

}
