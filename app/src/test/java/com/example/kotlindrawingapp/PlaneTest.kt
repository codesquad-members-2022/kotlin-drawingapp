package com.example.kotlindrawingapp

import com.example.kotlindrawingapp.domain.figure.plane.Plane
import com.example.kotlindrawingapp.domain.figure.*
import com.example.kotlindrawingapp.domain.figure.square.Square
import org.junit.Test

import org.junit.Assert.*

class PlaneTest {
    @Test
    fun 플레인_추가_및_개수세기() {
        val plane = Plane.of(listOf())
        val square = Square(ID.generateID(), Point(1F, 1F), Size(), RGB(2, 3, 4), Alpha(5))
        val square1 = Square(ID.generateID(), Point(2F, 2F), Size(), RGB(5, 7, 2), Alpha(8))
        with(plane) {
            addFigure(square)
            addFigure(square1)
        }

        assertEquals(2, plane.count())
    }

    @Test
    fun 플레인_동일좌표_확인하기() {
        val plane = Plane.of(listOf())
        plane.addFigure(
            Square(ID.generateID(), Point(2F, 2F), Size(), RGB(1, 2, 3), Alpha(4))
        )
        val square = Square(ID.generateID(), Point(2F, 2F), Size(), RGB(1, 2, 3), Alpha(4))
        val square1 = Square(ID.generateID(), Point(2F, 3F), Size(), RGB(1, 2, 3), Alpha(4))

        assertEquals(true, plane.touchPoint(square.point))
        assertEquals(true, plane.touchPoint(square1.point))
    }

    @Test
    fun 사각형_접근하기() {
        val plane = Plane.of(listOf())
        val square = Square(ID.generateID(), Point(1F, 1F), Size(), RGB(2, 3, 4), Alpha(5))
        val square1 = Square(ID.generateID(), Point(2F, 2F), Size(), RGB(5, 7, 2), Alpha(8))
        with(plane) {
            addFigure(square)
            addFigure(square1)
        }

        assertEquals(square, plane.findByIndex(0))
        assertEquals(square1, plane.findByIndex(1))
    }
}