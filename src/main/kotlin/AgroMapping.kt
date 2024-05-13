class AgroMapping(val source: Long, val destination: Long, val count: Long) {

    val sourceStart: Long = source
    val sourceEnd: Long = sourceStart + count - 1
    val destinationStart: Long = destination
    val destinationEnd: Long = destinationStart + count - 1

    override fun toString(): String {
        return "AgroMapping with source [$sourceStart, $sourceEnd] and destination [$destinationStart, $destinationEnd]"
    }
}