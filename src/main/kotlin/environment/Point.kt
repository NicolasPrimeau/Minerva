package environment

class Point (vararg val dimensions : Int){

    override operator fun equals(other: Any?) : Boolean{
        if (other == null) {
            return false;
        } else if (other !is Point) {
            return false
        }
        for ((index, value) in this.dimensions.withIndex()){
            if (value != other.dimensions[index]) {
                return false
            }
        }
        return true
    }

    fun deepCopy(): Point {
        return Point(*dimensions)
    }

    override fun hashCode() : Int {
        return this.dimensions.sum()
    }

    override fun toString() : String {
        return this.dimensions.joinToString(",")
    }

}