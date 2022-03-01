package com.example.kotlindrawingapp

import com.example.kotlindrawingapp.plane.Plane
import com.example.kotlindrawingapp.square.*
import org.junit.Test

import org.junit.Assert.*

class PlaneTest {
    @Test
    fun 플레인_추가_및_개수세기() {
        val plane = Plane.of(listOf())
        val square = Square(ID(ID.generateID()), Point(1, 1), Size(), RGB(2, 3, 4), Alpha(5))
        val square1 = Square(ID(ID.generateID()), Point(2, 2), Size(), RGB(5, 7, 2), Alpha(8))
        with(plane) {
            addSquare(square)
            addSquare(square1)
        }

        assertEquals(2, plane.count())
    }

    @Test
    fun 플레인_동일좌표_확인하기() {
        val plane = Plane.of(listOf())
        plane.addSquare(
            Square(ID(ID.generateID()), Point(2, 2), Size(), RGB(1, 2, 3), Alpha(4))
        )
        val square = Square(ID(ID.generateID()), Point(2, 2), Size(), RGB(1, 2, 3), Alpha(4))
        val square1 = Square(ID(ID.generateID()), Point(2, 3), Size(), RGB(1, 2, 3), Alpha(4))

        assertEquals(true, plane.contain(square))
        assertEquals(true, plane.contain(square1))
    }

    @Test
    fun 사각형_접근하기() {
        val plane = Plane.of(listOf())
        val square = Square(ID(ID.generateID()), Point(1, 1), Size(), RGB(2, 3, 4), Alpha(5))
        val square1 = Square(ID(ID.generateID()), Point(2, 2), Size(), RGB(5, 7, 2), Alpha(8))
        with(plane) {
            addSquare(square)
            addSquare(square1)
        }

        assertEquals(square, plane.findSquare(0))
        assertEquals(square1, plane.findSquare(1))
    }
}