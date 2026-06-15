package de.seuhd.ktfuzzer.mode.mutational

import kotlin.random.Random

/**
 * Character-level mutators from The Fuzzing Book's MutationFuzzer
 * (<https://www.fuzzingbook.org/html/MutationFuzzer.html>), plus [repeatRandomCharacter]. Each
 * function applies one small edit to an input string and returns the result.
 */
internal object Mutators {
    /** Deletes one randomly chosen character. */
    fun deleteRandomCharacter(input: String, random: Random): String {
        if (input.isEmpty()) return ""
        val index = random.nextInt(input.length)
        return input.removeRange(index, index + 1)
    }

    /** Inserts one character drawn uniformly from [alphabet] at a random position. */
    fun insertRandomCharacter(input: String, alphabet: List<Char>, random: Random): String {
        val index = random.nextInt(input.length + 1)
        val char = alphabet[random.nextInt(alphabet.size)]
        return StringBuilder(input).insert(index, char).toString()
    }

    /** Flips one randomly chosen low bit of one randomly chosen character. */
    fun flipRandomCharacter(input: String, random: Random): String {
        if (input.isEmpty()) return ""
        val charIndex = random.nextInt(input.length)
        val bitIndex = random.nextInt(7)
        val char = input[charIndex]
        val flippedChar = (char.code xor (1 shl bitIndex)).toChar()
        val builder = StringBuilder(input)
        builder.setCharAt(charIndex, flippedChar)
        return builder.toString()
    }

    /** Repeats one randomly chosen character a random number of times in place. */
    fun repeatRandomCharacter(input: String, random: Random): String {
        if (input.isEmpty()) return ""
        val charIndex = random.nextInt(input.length)
        val char = input[charIndex]
        val count = random.nextInt(2, 11)
        val repeated = char.toString().repeat(count)
        return input.substring(0, charIndex) + repeated + input.substring(charIndex + 1)
    }
}
