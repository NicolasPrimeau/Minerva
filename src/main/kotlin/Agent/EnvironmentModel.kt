package main.kotlin.Agent

interface EnvironmentModel {

    override fun hashCode() : Int
    override operator fun equals(other: Any?) : Boolean
}