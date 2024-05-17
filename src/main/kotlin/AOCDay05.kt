import kotlin.io.path.Path
import kotlin.io.path.readLines

class AOCDay05(val input: List<String>) {

    var seeds: MutableList<Long> = mutableListOf()
    var agroMappings: MutableList<MutableList<AgroMapping>> = mutableListOf()
    var seedRanges: MutableList<MutableList<Long>> = mutableListOf()

    fun parseAgroInfo(){
        this.seeds = this.input[0].replace("seeds: ", "").split(" ").map {
            it: String -> it.toLong()
        }.toMutableList()

        for (i in 0..< this.seeds.size step 2){
            this.seedRanges.add(mutableListOf(this.seeds[i], this.seeds[i] + this.seeds[i + 1] - 1))
        }

        var index = 3
        var tempList: MutableList<AgroMapping> = mutableListOf()
        while (index < this.input.size) {
            if (this.input[index].contains("map")) {
                tempList.sortBy { it.sourceStart }
                this.agroMappings.add(tempList)
                tempList = mutableListOf()
            } else if (this.input[index] != "") {
                var ranges = this.input[index].split(" ").map { it: String -> it.trim().toLong() }
                tempList.add(AgroMapping(ranges[1], ranges[0], ranges[2]))
            }
            if (index == this.input.size - 1) {
                tempList.sortBy { it.sourceStart }
                this.agroMappings.add(tempList)
            }
            index += 1
        }
    }

    fun convertSeedRangesForAgroMap(seedRanges: MutableList<MutableList<Long>>, agroMap: MutableList<AgroMapping>): MutableList<MutableList<Long>> {
        val convertedSeedRange: MutableList<MutableList<Long>> = mutableListOf()
        var seeds = seedRanges.toMutableList()
        var mapCount = 0
        for (map in agroMap) {
            var tempSeedRanges: MutableList<MutableList<Long>> = mutableListOf()
            for (seedRange in seeds) {
                val rangeSource = seedRange[0]
                val rangeEnd = if (seedRange.size == 1) seedRange[0] else seedRange[1]
                if (rangeSource >= map.sourceStart && rangeEnd <= map.sourceEnd) {
                    val destinationStart = rangeSource - map.sourceStart + map.destinationStart
                    val destinationEnd = rangeEnd - map.sourceStart + map.destinationStart
                    val converted =
                        if (destinationStart == destinationEnd) mutableListOf(destinationStart) else mutableListOf(
                            destinationStart,
                            destinationEnd
                        )
                    convertedSeedRange.add(converted)
                } else if (rangeSource < map.sourceStart && rangeEnd <= map.sourceEnd && rangeEnd >= map.sourceStart) {
                    val converted =
                        if (rangeSource == map.sourceStart - 1) mutableListOf(rangeSource) else mutableListOf(
                            rangeSource, map.sourceStart - 1
                        )
                    convertedSeedRange.add(converted)
                    val destinationEnd = rangeEnd - map.sourceStart + map.destinationStart
                    convertedSeedRange.add(mutableListOf(map.destinationStart, destinationEnd))
                } else if (rangeSource <= map.sourceEnd && rangeSource >= map.sourceStart && rangeEnd > map.sourceEnd) {
                    val destinationStart = rangeSource - map.sourceStart + map.destinationStart
                    val destinationEnd = map.destinationEnd
                    val converted =
                        if (destinationStart == destinationEnd) mutableListOf(destinationStart) else mutableListOf(
                            destinationStart,
                            destinationEnd
                        )
                    convertedSeedRange.add(converted)
                    tempSeedRanges.add(mutableListOf(map.sourceEnd + 1, rangeEnd))
                } else if (rangeSource < map.sourceStart && rangeEnd > map.sourceEnd) {
                    convertedSeedRange.add(mutableListOf(rangeSource, map.sourceStart - 1))
                    convertedSeedRange.add(mutableListOf(map.destinationStart, map.destinationEnd))
                    tempSeedRanges.add(mutableListOf(map.sourceEnd + 1, rangeEnd))
                } else if (rangeSource > map.sourceStart && rangeEnd > map.sourceEnd && mapCount == agroMap.size - 1) {
                    convertedSeedRange.add(seedRanges[0])
                } else if (rangeSource < map.sourceStart && rangeEnd < map.sourceEnd && mapCount == 0) {
                    convertedSeedRange.add(seedRanges[0])
                } else {
                    tempSeedRanges.add(seedRange)
                }
            }
            seeds = tempSeedRanges
            mapCount += 1
        }
        convertedSeedRange.addAll(seeds)
        return convertedSeedRange
    }

    fun convertThroughAllAgroMappings(): MutableList<MutableList<Long>> {
        var convertedSeedRanges = this.seedRanges.toMutableList()
        for (agroMap in this.agroMappings) {
            convertedSeedRanges = convertSeedRangesForAgroMap(convertedSeedRanges, agroMap)
        }
        return convertedSeedRanges
    }

    fun getMinimumLocationAfterConversion(): Long {
        var starts = convertThroughAllAgroMappings().map { it -> it[0] }
        return starts.sorted()[0]
    }

    fun getSeedLocation(seed: Long): Long {
        var trace: Long = seed
        for (map in this.agroMappings) {
            var range = 0
            var found = false
            while (range < map.size && !found) {
                if (trace >= map[range].sourceStart && trace <= map[range].sourceEnd) {
                    trace = map[range].destinationStart + (trace - map[range].sourceStart)
                    found = true
                }
                range += 1
            }
        }
        return trace
    }

    fun getMinimumSeedLocation(): Long {
        var minLoc = Long.MAX_VALUE
        for (seed in this.seeds) {
            var loc = this.getSeedLocation(seed)
            if (loc < minLoc)
                minLoc = loc
        }
        return minLoc
    }

}

fun main() {
    val input = Path("src/main/resources/inputDay05-2.txt").readLines()
    var day05 = AOCDay05(input)
    day05.parseAgroInfo()
    println(day05.getMinimumSeedLocation())
    println(day05.getMinimumLocationAfterConversion())
}

/*
seeds: 79 14 55 13

seed-to-soil map: [79, 92] [55, 67]
50 98 2 [98, 99]
52 50 48 [50, 97] => [81, 93]

soil-to-fertilizer map: [55, 67], [81, 95]
0 15 37 [15, 51]
37 52 2 [52, 53]
39 0 15 [0, 14]

fertilizer-to-water map: [55, 67], [81, 95]
49 53 8 [53, 60] => [55, 60], [61, 67] => [51, 56], [61, 67]
0 11 42 [11, 52]
42 0 7 [0, 6]
57 7 4 [7, 10]

water-to-light map: [81, 95], [61, 67], [51, 56]
88 18 7 [18, 24]
18 25 70 [25, 94]

light-to-temperature map: [74, 88], [54, 60], [44, 49]
45 77 23 [77, 99]
81 45 19 [45, 63]
68 64 13 [64, 76]
[45, 63] => [44], [45, 49], [54-60] => [44], [81, 85]  => if within range, convert and add to next step list, if less, add to next step list, if over keep in range =>
[64, 76] => [74, 76] => [78, 80]
[77, 99] => [77, 88] => [45, 56]

temperature-to-humidity map: [44], [81, 85], [90, 96], [78, 80], [45, 56]
0 69 1 [69] ->[0]
1 0 69 [0, 68] -> [1,68]

humidity-to-location map: [45], [81, 85], [90,96], [78, 80], [46, 57]
60 56 37 [56, 92]
56 93 46 [93, 134]
*/
